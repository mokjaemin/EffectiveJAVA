package Item11;


import java.util.HashMap;

// Item11 : equals를 재정의하려거든 hashcode도 재정의하라.
public class Main {
    public static void main(String[] args) throws Exception{

        // 기본 아이디어 : "equals를 재정의한 클래스 모두에서 hashcode도 재정의해야한다.
        // - 하지 않으면 HashMap이나 HashSet에서 문제를 일으킨다.


        // hashcode 규약
        // 1. equals 비교에 사용되는 정보가 변경되지 않았다면 애플리케이션이 실행되는 동안 그 객체의 hashcode는 항상 같아야 한다.
        // (애플리케이션 종료 후 재실행시 변경될 수 있다.)
        // 2. equals가 두 객체가 같다고 판단했다면, 두 객체의 hashcode도 같아야 한다.
        // 3. equals가 두 객체가 다르다고 판단했더라도, hashcode는 같을 수 있다. 하지만 달라야 성능이 좋은 해시테이블이다.

        // - hashcode 재정의를 잘못했을 때, 크게 문제되는 조항은 두번째인데, 논리적으로 같은 객체는 같은 hashcode를 가져야 한다.
        // - Object의 기본 hashcode는 물리적으로 두 객체가 다르면(인스턴스가 다르면) 다른 값 반환.


        // hashCode를 구현하지 않았을 때 예시
        // - 아래에서 값이 같더라도 null 반환
        // - 논리적으로 같더라도 물리적으로 다르기에 다른 해시값을 갖기 때문이다.
        HashMap<PhoneNumber, String> book = new HashMap<>();
        PhoneNumber number1 = new PhoneNumber(10, 5370, 1670);
        PhoneNumber number2 = new PhoneNumber(10, 5370, 1670);

        book.put(number1, "목재민");
        System.out.println(book.get(number2));


        // 해시코드를 작성하는 요령
        // 1. int변수 result를 선언한 후 c로 초기화 한다.
        // (이 때, c는 해당 객체의 첫번째 핵심 필드를 단계 2-1방식으로 계산한 해시코드이다.)
        // (핵심 필드란, equals에서 비교에 사용되는 필드를 의미한다.)
        // 2. 해당 객체의 나머지 핵심 필드 f에 대해 아래의 작업들을 수행한다.
        // 2-1. 해당 필드의 해시코드 c를 계산한다.
        // 2-1-1. 기본 타입 필드라면, Type.hashcode(f)를 수행한다.
        // (ex. Integer.hashcode(int i))
        // 2-1-2. 참조 타입 필드이면서 해당 클래스의 equals 메서드가 참조 필드의 equals를 재귀적으로 호출해 비교한다면
        // hashCode도 재귀적으로 호출될 것이다.
        // - 계산이 복잡해질 것 같으면 해당 필드의 표준형을 만들어 그 표준형의 hashCode를 호출하고, 만약 해당 필드가 null이라면 0을 사용한다.
        // 2-1-3. 배열 타입 필드라면([] 타입), 핵심 원소 각각을 별도 필드처럼 다룬다.
        // - 각 핵심 원소의 해시값을 계산하고 2-2 방법을 적용한다.
        // - 만약, 핵심원소가 없다면? 단순히 상수 사용
        // - 만약, 모두 핵심원소라면? Arrays.hashCode사용
        // 2-2. 위에서 계산한 c로 result를 갱신한다.
        // (result = 31*result+c)
        // 3. result를 반환한다.


        // (+) 다 작성 후 확인 사항
        // - 동치인 인스턴스에 대해 똑같은 해시코드를 반환하는지 확인
        // - 단위 테스트 작성
        // - 다른 해시코드를 반환한다면 원인 파악 및 해결
        // - result에는 홀수를 곱해야 한다.(오버플로우 방지), 그냥 31을 곱하자.
        // (31*i는 실제 컴퓨터상에서는 (i<<5)-1이고 시프트 연산이 짝수를 곱하면 오버플로우 발생해 데이터 손실)


        // (+) 추가사항
        // - 파생필드는 해시코드 계산에서 제외 가능하다.
        // - 즉, 다른 필드로 부터 계산해 낼 수 있는 필드는 모두 무시할 수 있다.
        // - equals에 사용되지 않는 필드는 반드시 해시코드 계산에서 제외한다.
        // (어길시 equals에서 같다고 판단한 객체에 대해서 다른 해시코드가 나올 수 있다.)

        // hashCode 구현시 제대로 동작
        System.out.println(book.get(number2));

        // 성능이 아쉽지만 짧은 코드로 hashCode를 작성하는 방법
        // - 기본 타입이면 박싱, 언박싱을 자동으로 하기때문에 성능이 저하
        // - return Objects.hash(lineNum, prefix, areaCode);



        // 추가 고려사항
        // - 클래스가 불변이고, 해시코드를 계산하는 비용이 크다면 매번 새로 계산하는 방법보단 캐싱을 하자.
        // - 지연 초기화를 통해 이를 구현 (주의 사항 : 스레드 안전하게 만들도록 신경써야 함. (Item 83))
        // - 성능을 높이기 위해 해시코드를 계산할 때, 핵심필드를 생략해서는 안된다.
        // (속도는 빨라질 수 있지만 심각한 성능 저하를 불러일으킨다.)
        // - 해시코드가 반환하는 값의 생성 규칙을 API 사용자에게 자세히 공표하지 말자.
        // (그래야 클라이언트가 이 값에 의지하지 않고 나중에 변경 가능하다.)


    }
}