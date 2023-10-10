package Item22;

import java.util.*;

// Item 22 : 인터페이스는 타입을 정의하는 용도로만 사용하라
public class Main {
    public static void main(String[] args){

        // 인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다.
        // -> 인스턴스의 역할을 클라이언트에게 알려주는 역할을 한다.
        // -> 인터페이스는 오직 이 역할만을 수행해야 한다.

        // 상수 인터페이스가 존재하는데 사용하지 말자
        // 상수 인터페이스 안티패턴 : static final 필드로만 가득찬 인터페이스
        System.out.println(Number.one);
        // -> 인터페이스에서 구현한 상수는 외부 인터페이스가 아니라 내부 구현에 해당한다.
        System.out.println(Test.one);
        // -> 클래스가 어떤 인터페이스를 사용하는지 사용자에게 아무 의미가 없으며, 오히려 혼란을 준다.
        // -> 클라이언트의 코드가 내부 구현에 해당하는 이 상수들에 종속되게 된다.
        // -> 다음 릴리스에서 이 상수들을 더는 쓰지 않더라도 바이너리 호환성을 위해 여전히 상수 인터페이스를 구현해야한다.
        // -> final 이 아닌 클래스가 상수 인터페이스를 구현한다면 모든 하위 클래스의 이름공간이 그 인터페이스가 정의된
        // 상수들로 오염된다.
        System.out.println(Test2.one);

        // 해결책
        // 1. 해당 상수를 클래스 자체에 추가해 사용하자.
        // 2. 열거타입으로 가능하다면 열거타입도 괜찮다.
        // 3. 인스턴스화가 불가능한 유틸리티 클래스로 만들자.
        // (해당 클래스의 상수를 자주 사용한다면 import static 으로 클래스명 생략 가능하다.)



    }
}
