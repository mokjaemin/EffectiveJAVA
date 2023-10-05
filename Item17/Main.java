package Item17;


import java.math.BigInteger;

// Item 17 : 변경 가능성을 최소화하라.
public class Main {
    public static void main(String[] args){

        // 1. 불변 클래스
        // - 정의 : 그 인스턴스의 내부 값을 수정할 수 없는 클래스
        // - 객체가 파괴되는 순간까지 내부 정보는 절대 변경되지 않는다.
        // - 예시 : String, 기본 타입의 박싱된 클래스들, BigInteger, BigDecimal
        // - 불변클래스로 만드는 이유 : 설계, 구현, 사용이 쉬우며 오류가 생길 여지도 적고 안전하다.


        // 2. 불변 클래스를 만드는 5가지 규칙
        // - 1. 객체의 상태를 변경하는 메서드(변경자, setter)를 제공하지 않는다.
        // - 2. 클래스를 확장할 수 없도록 한다.
        // -> 하위클래스에서 악의적으로 변경을 막고 상속을 막는 방법은 final로 선언하는 방법이 있다.
        // - 3. 모든 필드를 final로 설정한다.
        // - 4. 모든 필드를 private로 설정한다.
        // -> 가변 객체의 변경을 막는다.
        // -> 불변 객체에 대해 public final로 선언해도 불변이지만 다음 릴리스에서 내부 표현을 바꾸지 못하는 문제
        // - 5. 자신 외에는 자신 내부의 가변 컴포넌트에 대해 접근을 막는다.
        // -> 클라이언트가 해당 컴포턴트의 참조를 얻을 수 없게 막자, 접근자 메서드에 방어적 복사를 사용


        // 3. 함수형 프로그래밍
        // - 피연산자에 함수를 적용해 그 결과를 반환하지만 피연산자는 불변함.
        // - 메서드 이름을 plus와 같은 이름을 사용 (전치사 사용)
        // - 예시 : Complex 두개는 그대로고 결과는 새로운 객체
        Complex a = new Complex(2, 2);
        Complex b = new Complex(1, 1);
        Complex c = a.plus(b);
        Complex d = a.minus(b);
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(c));
        System.out.println(System.identityHashCode(d));


        // 4. 절차형(명령형) 프로그래밍
        // - 위와 달리, 자신의 값이 바뀜
        // - 메서드 이름을 add와 같은 이름 사용 (동사 사용)



        // 5. 불변 클래스의 장점
        // - 1. 근본적으로 스레드 안전하여 따로 동기화 필요가 없다
        // -> 여러 스레드가 동시 사용해도 훼손이 없다.
        // -> 따라서 여러 스레드 간 공유가 가능하다.
        // -> 이를 활용해, 재활용해 사용하자. (자주 쓰이는 값들을 상수로 제공)
        Complex test1 = Complex.zero;
        Complex test2 = Complex.zero;
        System.out.println(System.identityHashCode(test1));
        System.out.println(System.identityHashCode(test2));
        // -> 자주 사용하는 인스턴스를 캐싱한 후 정적 팩터리를 이용해 이를 제공하면 메모리 효율성이 커진다.
        // - 2. 불변 객체는 자유롭게 공유 가능하며 불변 객체끼리는 내부 데이터를 공유할 수 있다.
        // -> 예시 : BigInteger의 negate 메서드는 값을 원본과 공유한다.
        BigInteger test10 = new BigInteger("10000");
        BigInteger test11 = test10.negate();
        // - 3. 객체를 만들 때, 다른 불변 객체들을 구성요소로 사용하면 이점이 많다.
        // -> 예시로 불변 객체는 Map, Set의 원소로 사용하기 좋다.
        // - 4. 불변 객체는 그 자체로 실패 원자성을 제공한다.


        // 6. 불변 클래스의 단점
        // - 값이 다르면 반드시 독립된 객체로 만들어야 한다.
        // - 예를들어, 비트가 엄청 큰 BigDecimal 에서 하나의 비트를 변경하려 해도 새로 생성해야 함.
        // - 해결책은 다단계 연산을 예측하여 기본 기능으로 제공, 가변 동반 클래스 제공
        // - 가변 동반 클래스의 예시로는 (String-StringBuilder, StringBuffer),
        // (BigInteger-BitSet) 가 있다.


        // 7. 불변 클래스를 만드는 다른 방법
        // - 1. 상속을 막는 방법
        // -> 1. final 사용
        // -> 2. 모든 생성자를 private, package-private로 설정 후, public 정적 팩터리 제공
        // --> public, protected 생성자가 없으니 클라이언트 입장에서는 확장이 불가능해짐.
        Complex test5 = Complex.valueOf(1, 2);
        // - 2. BigInteger, BigDecimal는 상속에 대한 고민이 부족한채로 만들어졌기에 진짜 클래스인지 살펴사용하자.
        // -> 악의적인 개발자가 새로 생성한 클래스일 가능성이 존재
        // - 3. 모든 필드는 final이고 수정가능한 메서드가 없어야한다는 조금 과하다
        // -> '어떤 메서드도 객체의 필드 중 외부에 비치는 값을 변경할 수 없어야 한다.' 로 변경
        // -> 변경 시, 외부에 비치지 않는 필드에 한하여 final을 제거하고 캐싱, Lazy 로 성능을 올릴 수 있다.
        // -> 예를 들어, 불변 클래스 PhoneNumber의 Hashcode는 처음 불렸을 때, 캐싱해두고 사용한다.


        // 8. 정리
        // - 1. 게터가 있다고 해서 세터를 무조건 구현하지는 말자
        // - 2. 클래스는 꼭 필요한 것이 아니라면 불변이여야 한다.
        // - 3. 불변 클래스는 장점이 많고 단점은 잠재적 성능 저하 뿐이다.
        // - 4. 성능상 어쩔 수 없다면, 차라리 가변 동반 클래스를 Public으로 제공하자.
        // - 5. 불변으로 만들 수 없는 클래스라면 변경할 수 있는 부분을 최소화하자.
        // - 6. 다른 합당한 이유가 없다면, 필드는 private final 이어야 한다.
        // - 7. 생성자는 불변식 설정이 모두 완료된, 초기화가 완벽히 끝난 상태의 객체를 생성해야 한다.
        // - 8. 확실한 이유가 없다면, 생성자와 정적 팩터리 외의 어떤 초기화 메서드도 public으로 제공해서는 안된다.






        // 적용
        // - 스프링 프로젝트 클래스들 웬만하면 불변으로 만들자, 안된다면 최소한의 부분만 수정가능하게 하자.








    }
}
