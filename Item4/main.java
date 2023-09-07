package Item4;

import java.util.*;


// Item 4 : 인스턴스화를 막으려거든 Private 생성자를 사용하라.
public class main {
    public static void main(String[] args) {
        

        // 정적 필드와 정적 메서드만 모아둔 경우

        // 1. 기본 값이나 배열 관련 메서드를 모아놓은 경우
        // - java.lang.Math나 Java.utils.Array
        // - 아래 함수 확인시 정적필드와 정적 메서드만을 모아놓음
        int result = (int)Math.pow(10, 2);

        // 2. 특정 인터페이스를 구현한 객체를 생성해주는 정적 메서드를 모아놓은 경우
        // - java.util.Collections
        // - 인터페이스 List를 구현하는 ArrayList 객체를 생성해주는 메서드들을 모아놓음
        // - 자바 8부터는 위와 같은 메서드를 인터페이스 안에 구현 가능
        List<Integer> arr = new ArrayList<>();
        Job police = Job.makePolice();
        police.work();

        // 3. final 클래스(상속이 불가능한 클래스)와 관련된 메서드를 모아놓고 사용하는 경우


        // 이러한 클래스들은 인스턴스화가 불가능해야 함.
        // 추상 클래스를 생성하여 인스턴스화를 막는 것은 방법이 아니다.
        // - 추상 클래스 : 추상 메서드를 선언해 놓고 상속을 통해 자식 클래스에서 메서드를 완성하도록 유도하는 클래스
        // - 하위 클래스를 만들어 추상 클래스를 상속하고 하위 클래스를 인스턴스화 하면 그만이기 때문에
        
        // Singer singer = new Siger(); // 불가능
        Singer singer = new Iu(); 
        singer.sing(); // 인스턴스화 못막음





        // 인스턴스화를 막는 법 : private 생성자를 만들자.
        // 추가적으로 throw new AssertionError()를 던져 클래스 내부에서 생성자를 사용하는 경우를 막아두면 좋다.

        // 유틸리티 클래스
        // - 정적 메서드와 정적 상수들로 이루어진 클래스, ex)Math
        // - 프로젝트에서 특정 메서드가 계속 사용되는 경우 만들어서 사용

    }
}
