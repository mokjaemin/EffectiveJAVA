package Item12;

import java.util.*;

// Item 12 : toString을 항상 재정의하라.
public class Main {
    public static void main(String[] args){

        // toString의 일반 규약
        // 1. "간결하면서도 사람이 읽기 쉬운 형태의 유익한 정보"
        // 2. "모든 하위클래스에서 해당 메서드를 재정의하라"

        // "toString을 잘 구현한 클래스는 사용하기에 훨씬 즐겁고, 그 클래스를 사용한 시스템은 디버깅이 쉽다"


        // 자동 호출되는 경우
        // 1. println, printf
        // - 아래의 경우에서 인스턴스 출력시 자동으로 toString이 호출됨을 확인할 수 있다.
        PhoneNumber number1 = new PhoneNumber(111, 222, 333);
        System.out.println(number1);
        // 2. 문자열 연결 연산자(+)
        String now  = "hello";
        System.out.println(now + " world");
        System.out.println(now.toString() + " world");
        // 3. assert구문에 넘길 때
        // 4. 디버거가 객체를 출력할 때
        // 5. 객체를 참조하는 컴포넌트가 오류메시지를 로깅할 때
        // - toString을 재정의하지 않으면 쓸모없는 로그만 남을 것이다.
        System.out.println("전화번호 "+number1+"가 잘 생성되었습니다.");


        // 참고 사항
        // 1. 좋은 toString은 해당 인스턴스를 포함하는 컬렉션에서 유용하게 쓰인다.
        // - toString 적용시 그냥 출력시에도 toString으로 변환한 결과가 출력된다.
        List<PhoneNumber> book = new ArrayList<>();
        PhoneNumber number2 = new PhoneNumber(111, 222, 333);
        book.add(number1);
        book.add(number2);
        System.out.println(book);

        // 2. 실전에서 toString은 그 객체가 가진 중요한 정보를 모두 반환하는게 좋다.
        // - 객체의 상태가 문자열로 표현하기 적합하지 않다면 요약한 결과를 반환하자.

        // 3. 반환값의 포맷을 문서화할지 정하자.
        // - 전화번호나 행렬같은 클래스라면 문서화를 권함.
        // - 값 그대로 출력하거나 csv와 같은 파일작성도 가능하다.
        // - 포맷을 명시하기로 했다면, 문자열<->객체 상호 변환가능하도록 해주는 정적팩터리 매서드나 생성자를 함께 만들자.
        // - BigInteger, BigDecimal가 위의 예시
        // - 하지만 단점으로 포맷을 명시하면 그 포맷의 얽매이게 된다.

        // 4. 포맷을 명시하든 아니든 제작 의도는 명확히 밝혀야 한다.
        // -> 설명을 적어주자, 그래야 변경시 피해 방지

        // 5. 포맷 명시 여부와 toString이 반환한 값에 포함된 정보를 얻어올 수 있는 API를 제공하자.
        // -> 필드 값들을 반환할 수 있는 API가 없다면(getter, setter) toString의 반환값들을 파싱할 수 밖에 없다.
        // 이는 성능 문제를 야기시킨다.

        // 6. 정적 유틸리티(아이템 4)는 toString을 제공할 필요가 없다.

        // 7. 열거타입 또한 자바에서 이미 완벽한 toString을 제공하니 재정의 필요가 없다.

        // 8. 하위 클래스들이 공유할 문자열 표현이 있는 추상 클래스라면 구현해야 한다.
        // -> 대부분의 컬렉션 구현체는 추상 컬렉션 클래스들의 toString 메서드를 상속해서 사용







    }
}
