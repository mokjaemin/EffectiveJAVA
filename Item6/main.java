package Item6;

import java.time.LocalDateTime;
import java.util.*;

// Item 6 : 불필요한 객체 생성을 피하라
public class main {
    public static void main(String[] args){
        
        // 아이디어 : 매번 객체를 생성하는 것보단 미리 만들어놓은 객체를 생성하는 것이 좋다.
        // - 재사용은 빠르고 세련되다
        // - 불변 객체는 언제든 재사용이 가능하다.

        // 1. 객체를 계속 생성하는 안좋은 예시
        String badThing1 = new String("hello world");
        String badThing2 = new String("hello world");
        System.out.println(System.identityHashCode(badThing1));
        System.out.println(System.identityHashCode(badThing2));

        // 2. 만들어놓은 객체를 그대로 사용하는 예시
        // - 같은 가상머신내에서 똑같은 문자열 리터럴을 사용하는 모든 코드가 같은 객체를 사용한다.
        String goodThing1 = "hello world";
        String goodThing2 = "hello world";
        System.out.println(System.identityHashCode(goodThing1));
        System.out.println(System.identityHashCode(goodThing2));


        // 3. 불변 클래스
        // - 생성자 대신 정적 팩터리 메서드를 제공하는 불변클래스
        // - 정적 팩터리 메서드를 통해 불필요한 객체 생성을 막는 경우에 해당 됨.
        
        // - 아래 경우에서는 심지어 deprecated 됨.
        // - 생성자를 사용할경우 새로운 객체를 계속 생성하지만 정적 팩터리 메서드를 사용하는 경우는 그렇지 않다.
        Boolean value1 = new Boolean("true");
        Boolean value2 = new Boolean("true");
        System.out.println(System.identityHashCode(value1));
        System.out.println(System.identityHashCode(value2));

        Boolean value3 = Boolean.valueOf("true");
        Boolean value4 = Boolean.valueOf("true");
        System.out.println(System.identityHashCode(value3));
        // System.out.println(System.identityHashCode(value4));

        // 4. 불변객체가 아닌 가변 객체라 하더라도 사용중에 변경되지 않을 것이라는 것을 안다면 재사용 가능하다.
        // - 생성비용이 굉장히 비싼 객체도 존재하는데 이러한 객체가 반복적으로 필요한 경우에는 캐싱해서 재사용하는 것이 권장됨.

        // 예시
        // 문자열이 유효한 로마숫자인지 확인해주는 메서드
        // 해당 메서드는 matches 메서드를 통해 정규표현식과 비교해 올바른 문자열인지 체크를 해주는데
        // 이는 성능이 중요한 상황에서 반복적으로 사용하기에 적합하지 않다.
        // -> 이유 : 매번 정규 표현식 Pattern 인스턴스가 한번 사용되고 버려지고 곧바로 가비지 컬렉션 대상이 되기 때문에
        // -> 또한 Pattern은 인스턴스 생성 비용이 크다.
        System.out.println(checkString.isRomanNumeral("I"));


        // 해결
        // Pattern 인스턴스를 클래스 초기화 과정에 직접 생성해 캐싱해두고 나중에 메서드 호출시마다
        // 해당 인스턴스를 재사용한다.
        System.out.println(checkStringNew.isRomanNumeral("I"));

        // 장점
        // 속도가 향상된다 -> 1.1us에서 0.17us로 향상됨을 저자는 설명함.
        // 코드가 명확해진다. -> 개선전에는 Pattern 인스턴스의 존재조차 확인하기 어려웠다.
        // 개선 후, static final 필드로 끄집어내고 이름도 지어주어 존재가 명확히 표현된다.

        // 단점
        // static 으로 인스턴스를 미리 만들기 때문에 만약, 해당 메서드가 안쓰인다면 낭비인 셈이다.
        // Lazy Initialization으로 메서드 처음 호출시 인스턴스를 생성할 수 있지만 저자는 권하지 않는다고 설명한다.
        // 코드는 복잡해지는데 성능이 개선되지는 않을 때가 많기 때문이다.
        // 방법 : 인스턴스를 선언만해두고 메서드 실행시 만약 null이라면 인스턴스 생성하는 방식

        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);


        // 어댑터
        // 객체가 불변이라면 재사용해도 안전함이 명백하지만 가변인 경우에도 인스턴스를 하나만 두고 사용하는 경우가 존재한다.
        // 대표적인 예가 어댑터인것이다.
        // 어댑터란 실제 작업은 뒷단 객체에 위임하고 자신은 제 2의 인터페이스 역할을 해주는 객체이다.
        // 즉, 뒷단 객체 외에는 관리할 상태가 없으므로 뒷단 객체 하나당 어댑터 하나만 만들어주면 충분하다.
        
        // 예시 : Map의 keySet()
        // 해당 메서드로 불러온 Set은 항상 같은 인스턴스
        // Set은 일반적으로 가변성을 갖는데 인스턴스 하나만 사용하는 대표적인 예시인 것이다.
        // 즉, 하나 수정시 모두 수정된다.
        // 따라서, KeySet을 여러개 만들어도 되지만 의미는 없다. 모두 같기 때문에
        Set<String> key1 = map.keySet();
        System.out.println(key1);
        map.put("c", 3);
        Set<String> key2 = map.keySet();
        System.out.println(key1);
        System.out.println(key2);
        System.out.println(System.identityHashCode(key1));
        System.out.println(System.identityHashCode(key2));

        // 오토박싱
        // 불필요한 객체를 만들어내는 다른 예시
        // 오토박싱이란 프로그래머가 기본 타입과 박싱된 기본 타입을 섞어 쓸때, 자동으로 상호 변환해주는 기술을 의미
        // "오토박싱은 기본 타입과 그에 대응하는 박싱된 기본타입의 구분을 흐려주지만 완전히 없애주는 것은 아니다."

        List<Integer> list = new ArrayList<>();
        list.add(1);
        for(int i : list){
            System.out.println(i);
        }

        // 차이 비교
        // 1번
        Long sum1 = 0L;
        for(long i=0; i<=Integer.MAX_VALUE; i++){
            sum1 += i;
        }
        System.out.println(sum1);
        // 1번의 경우 Long 타입인 sum1에 long i를 더할때마다 새로운 Long 인스턴스가 생성됨
        // String에 +와 같은 개념, Long도 마찬가지로 불변 클래스이다.
        // 따라서 엄청난 Long 타입의 인스턴스가 새로 생성되기 때문에 해당 코드는 시간이 오래 걸린다.

        // 2번
        long sum2 = 0L;
        for(long i=0; i<=Integer.MAX_VALUE; i++){
            sum2 += i;
        }
        System.out.println(sum2);
        
        // 2번이 훨씬 빠름을 알 수 있음


        // 결론적으로 "객체의 생성은 비싸니 피해라"라는 의미는 아니다.
        // JVM은 기본적으로 객체를 생성하고 회수하는 일에 큰 부담을 갖진 않는다.
        // 명확성, 간결성, 기능성을 위해 객체를 생성하는 일은 좋은 일이다.
        // JVM의 가비지 컬렉터는 잘 최적화되어서 가벼운 객체를 다루기 위해 개인적으로 풀을 만들 필요가 없다.
        // 다만, 데이터 베이스 연결과 같이 생성 비용이 큰 경우, 객체 풀을 만들어 재사용하는것이 낫다.


        // 결론 : "기존 객체를 재사용해야 한다면 새로운 객체를 만들지 마라"

    }
}
