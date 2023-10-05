package Item16;


// Item 16 : public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라.
public class Main {
    public static void main(String[] args){

        // 1. public 클래스라면 private 멤버와 각 멤버의 접근자(getter/setter)를 제공하는 것이 맞음
        // 2. package-private 클래스나 private 중첩클래스(클래스 내부 클래스)는 데이터 필드 public 가능
        // - 다만, 그 클래스가 표현하려고 하는 추상 개념은 올바르게 표현해야 함.
        // - 클래스 선언, 클라이언트 코드 등 훨씬 깔끔하다.
        // - "package 내부에서만 사용하는 클래스라면 웬만하면 package-private로 사용하자"
        // 3. 클래스의 public 필드가 불변이라도 public 선언은 좋은 방법은 아니다.

        // 결론 : public 클래스는 절대 가변 필드 노출 x, 불변 필드라면 노출을 생각해볼 수 있지만 안전하진 않다.
        // package-private이나 private 중첩 클래스는 노출하는게 나을 때도 있다.

    }
}
