package Item18;


import java.util.*;

// Item 18 : 상속보다는 컴포지션을 사용하라.
public class Main {
    public static void main(String[] args){

        // 주제 : "다른 패키지의 구체 클래스를 상속하는 것은 위험하다."

        // 문제점 : 메서드 호출과 달리 상속은 캡슐화를 깨뜨린다.
        // = 상위 클래스가 어떻게 구현되는냐에 따라 하위 클래스의 동작에 이상이 생길 수 있다.
        // = 상위 클래스는 릴리스마다 내부 구현이 변경될 수 있고 이에 따라 하위 클래스가 동작하지 않을 수 있다.

        // 상속을 잘못 사용하는 예시
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("틱", "택"));
        System.out.println(s.getAddCount());
        // 문제점 : 결과로 2가 아닌 4가 나온다.
        // 이유 : HashSet의 addAll 메서드는 add 메서드를 통해 이루어진다
        // 따라서, addAll 메서드 내부에서 count값을 두번 증가 시키고 addAll 호출시 add메서드가 호출되고
        // 값을 두번 다시 호출 시킨다.
        // 이러한 내용을 문서로 적혀있지 않다.


        // 상위 클래스의 메서드를 재정의하는 것은 내부 모든 동작을 파악하기도 어렵고 문서에 적혀있지 않은 경우도 존재한다
        // 또한, 새로운 릴리스에서 내부 변경시 이에 맞추어 모두 수정해야 하기에 유연하지 못한다.


        // 해결책 : 컴포지션
        // 방법
        // 1. 기존의 클래스를 확장하는 대신, 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조하게 하자
        // 2. 새 클래스의 메서드들은 기존 클래스 내의 대응하는 메서드를 호출해 그 결과를 반영한다.
        // -> 이 방식을 전달(forwarding)이라고 하며 새 클래스의 메서드들을 전달 메서드라고 한다.
        // 장점 : 기존 클래스의 내부 구현 방식의 영향에서 벗어나며, 새로운 메서드가 추가되더라도 영향을 받지 않는다.


        // 컴포지션 예시
        // Set은 인터페이스 HashSet, ForwardingSet(컴포지션 구현)은 Set의 구현체
        // ForwardingSet : 전달 메서드로만 이루어진 전달 클래스
        // InstrumentedSet : 집합 클래스, 래퍼클래스, 데코레이터 패턴
        // -> 래퍼 클래스 : 다른 Set 인터페이스를 구현한 클래스의 인스턴스(전달 클래스의 인스턴스)를 감싼다는 의미


        // 예시 속 전달 클래스 특징
        // 1. HashSet의 모든 기능이 정의되어 있는 Set 인터페이스를 활용해 설계되어 견고하고 아주 유연하다.
        // 2. 방식을 보면 Set의 인터페이스를 구현하여 Set의 모든 메서드를 사용하고 Set의 인스턴스를 인수로 받는 생성자를 제공한다.

        // 장점
        // 상속방식은 구체 클래스 각각을 따로 확장해야 하고, 지원하고 싶은 상위 클래스의 생성자 각각에 대응하는 생성자를 별도로 작성해야한다.
        // 컴포지션 방식은 한번 구현해두면 어떠한 Set의 구현체라도 계측할 수 있으며, 기존 생성자들과 함께 사용할 수 있다.
        // - 어떠한 Set 구현체라도 계측
        InstrumentedSet set1 = new InstrumentedSet(new HashSet());
        InstrumentedSet set2 = new InstrumentedSet(new TreeSet());
        // - 기존 생성자들과 함께 사용 가능
        Set set3 = new InstrumentedSet(new HashSet(10));


        // 전달 클래스의 작성이 지루하겠지만, 재사용할 수 있다는 특징을 가진 전달 클래스를 인터페이스당 하나만 만들어두면
        // 원하는 기능을 덧씌우는 래퍼클래스들을 아주 손쉽게 구현할 수 있다.


        // 결론
        // 상속은 강력하지만 캡슐화를 해친다는 문제가 있다.
        // 상속은 상위 클래스와 하위 클래스가 is-a관계일때만 사용해야한다.
        // is-a라고 안심할 수 없는게, 하위 클래스의 패키지가 상위 클래스의 패키지와 다르고,
        // 상위 클래스가 상속을 위해 설계된게 아니라면 문제가 될 수 있다.
        // 상속의 문제를 피하려면 컴포지션과 전달을 사용하자
        // 특히, 래퍼 클래스로 구현할 적당한 인터페이스가 있다면 더욱 그렇다.
        // 래퍼 클래스는 하위 클래스보다 견고하고 강력하다.



    }
}