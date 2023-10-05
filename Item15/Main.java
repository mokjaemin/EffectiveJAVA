package Item15;


// 4장. 클래스와 인터페이스

import Item15Test.PublicClass;

import java.util.*;

// Item 15 : 클래스와 멤버의 접근 권한을 최소화하라.
public class Main {
    public static void main(String[] args){

        // 1. 잘 설계된 컴포넌트란?
        // - 컴포넌트 : 클래스와 객체
        // - 클래스의 내부 데이터와 내부 구현 정보를 외부 컴포넌트로부터 잘 숨긴 구조
        // - 오직 API를 통해서만 다른 컴포넌트와 교류하는 구조
        // - 정보 은닉(캡슐화)의 근간


        // 2. 정보은닉의 장점
        // 공통 : 시스템을 구성하는 컴포넌트들을 독립시켜 개발, 테스트, 최적화, 적용, 분석, 수정 등을 개별적으로 가능하게 만든다.
        // 1. 시스템 개발 속도를 높인다.
        // 2. 시스템 관리 비용을 낮춘다.
        // 3. 성능 최적화에 도움을 준다.
        // 4. 소프트웨어 재사용성을 높인다.
        // 5. 큰 시스템을 제작하는 난이도를 낮춰준다.


        // 3. 자바의 정보 은닉
        // - 접근 제어 매커니즘 : 클래스, 인터페이스, 멤버의 접근성을 접근 제어자로 명시


        // 4. 정보 은닉의 기본 원칙
        // - "모든 클래스와 멤버의 접근성을 가능한 한 좁혀야 한다."
        // - "소프트웨어가 올바르게 동작하는 한 항상 가장 낮은 접근 수준을 부여해야 한다."


        // 5. 관련 용어 정리
        // 톱레벨 클래스 : 중첩 클래스가 아닌 그냥 우리가 생각하는 기본 클래스를 의미
        // 중첩 클래스 : 클래스 내부의 정의된 클래스
        // package-private : 접근 제어자를 지정하지 않은 경우
        // (단, 인터페이스의 멤버는 기본적으로 접근 제어자를 지정안하는 경우 public으로 설정 됨.)
        // 모듈 : 패키지들의 묶음


        // 6. 톱레벨 클래스와 인터페이스를 은닉화하는 기본적인 방법
        // - 패키지 외부에서 쓸일이 없다면 package-private 사용
        // (다음 버전(릴리스)에서 수정, 보완 작업이 가능하다.)
        // - public 선언시 API가 되어 영원히 관리해야 한다.
        // 예시 : PublicClass test1 = new PublicClass(1);
        // 예시 : PackagePrivateClass test2 = new PackagePrivateClass(1);
        // - 한 클래스에서만 사용되는 package-private 톱레벨 클래스나 인터페이스는
        // 클래스안에 private-static으로 중첩 클래스(클래스 내부의 정의된 클래스)로 설정하자.
        // - public일 필요가 없는 클래스의 접근 수준을 package-private 톱레벨로 설정


        // 7. 클래스 멤버의 부여할 수 있는 접근 수준
        // private : 멤버를 선언한 톱레벨 클래스에서만 접근 가능
        // package-private : 멤버가 소속된 패키지 안의 모든 클래스에서 접근 가능
        // protected : package-private를 포함하여 해당 클래스의 하위 클래스도 접근 가능
        // public : 모든 곳에서 접근 가능하다.


        // 8. 클래스 멤버의 설계 방법
        // 1. 클래스의 공개 API를 세심히 설계
        // 2. 모든 멤버는 private로 설정
        // 3. 오직 패키지의 다른 클래스가 접근 가능해야 하는 멤버는 package-private로 설정
        // (+) 상속해서 사용하면 되기에 public 클래스의 protected 멤버는 공개 API가 되어 버림
        InheritancePublicClass test3 = new InheritancePublicClass(1);
        System.out.println(test3.getValue());


        // 9. 멤버 접근성을 좁히지 못하는 경우
        // - 상위 클래스의 메서드를 재정의할 때, 그 접근 수준을 상위 클래스보다 좁게 설정하지 못한다.
        // - 리스코프 치환 원칙(상위 클래스의 인스턴스는 하위 클래스의 인스턴스로 대체 될 수 있다.) 때문에
        // (해당 규칙을 어기면 컴파일 오류라서 동작 안함)
        test3.sing(); // 해당 메서드는 상위 클래스의 메서드를 재정의하였는데, 범위가 좁아지면 오류 발생


        // 10. 추가 사항

        // 1. 테스트 시에 private 멤버를 package-private 까지 푸는 것은 괜찮다.

        // 2. public 클래스의 인스턴스 필드는 되도록 public이 아니여야 한다.
        // - 해당 필드가 가변객체를 참조하거나 final이 아닌 경우 : 값을 제한하지 못한다.
        // - 필드가 수정될 때, 다른 작업을 못하므로 스레드 안전하지 못한다.
        // - 추상 개념을 완성하는 꼭 필요한 구성요소로써의 상수라면, public static final 사용 가능
        // (-> 대문자와 _를 일반적으로 사용, 이런 경우 반드시 기본 타입 값이나 불변객체를 참조해야 함을 잊지 말자)
        // (-> 불변 객체가 아니라면 문제 발생 : 다른 객체를 참조하지는 못하지만 참조된 객체 자체는 수정 가능하기 때문이다)
        // (-> 따라서, 이러한 경우에는 public static final 사용이나 이 필드를 반환하는 메서드 사용 금지)
        // 예시 : 객체는 변경 가능하지만 해당 객체를 public static final로 해둔 경우
        TestClass test4 = new TestClass();
        System.out.println(test4.INSTANCE.getValue());
        test4.INSTANCE.setValue(2);
        System.out.println(test4.INSTANCE.getValue());
        // 해결책
        // - 1. private로 변경 후 해당 멤버를 불변, public으로 변경한 멤버 새로 생성
        // - 예시 : public static final List<Thing> VALUES
        // = Collections.unmodifiableList(Arrays.asList(ORIGINAL_VALUES));
        // - 2. private로 만들고 이 멤버를 복사해서 반환해주는 메서드 생성 (->방어적 복사)

        // 3. 모듈에서 공개하지 않기로 한 패키지는 공개되지 않는다.
        // (해당 모듈의 JAR 파일을 애플리케이션의 classpath에 넣지 않는 한)



        // 결론 :
        // 클래스는 접근성은 최소화, 필요한 것만 public
        // 필드는 상수용 public static final 이외 public 불가능
        // public static final 사용시 불변인지 확인




    }
}
