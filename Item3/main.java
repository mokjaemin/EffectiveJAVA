package Item3;

import java.util.function.Supplier;

// Item3. private 생성자나 열거타입으로 싱글턴임을 보증하라.
public class main {
    public static void main(String[] args) {

        // 싱글턴을 만드는 방식 - 2가지
        // 공통점
        // 1. 모두 생성자는 private으로 감춘다.
        // 2. 인스턴스에 접근할 수 있는 유일한 수단으로 private static 멤버를 만들어 둔다.


        // 방법 1. private static final 사용
        // 미리 생성해놓은 인스턴스를 받을 수 있고 private생성자만 존재하기에 더이상 인스턴스 생성 불가능
        Person1 person1 = Person1.instance;
        Person1 person2 = Person1.instance;
        System.out.print("두 인스턴스가 같은가? : ");
        System.out.println(person1 == person2);
        System.out.println(person1);
        System.out.println(person2);

        // 1번 방법 정리
        // 장점
        // 1. 스레드 safe 하다
        // 2. 구현이 간결, 싱글턴임이 명확히 들어난다.

        // 단점
        // 1. 싱글턴을 사용하는 클라이언트가 테스트하기 어려워진다.
        // 2. 리플렉션으로 Private 생성자를 호출할 수 있다.
        // 3. 역직렬화시 새로운 인스턴스가 생성될 수 있다.




        // 방법 2. 정적 팩터리 메서드를 public static 멤버로 제공
        // 인스턴스 생성시 private로 설정하고 publicstatic 메서드를 통해 해당 인스턴스를 반환하는 구조
        Person2 person3 = Person2.getInstance();
        Person2 person4 = Person2.getInstance();
        System.out.print("두 인스턴스가 같은가? : ");
        System.out.println(person3 == person4);
        System.out.println(person3);
        System.out.println(person4);

        // 2번 방법 정리
        // 장점
        // 1. API를 바꾸지 않고도 싱글턴이 아니게 변경 가능하다. 
        // 2. 정적 팩토리 메서드를 제네릭 싱글턴 팩토리로 만들 수 있다.
        // 3. 정적 팩토리의 메서드 참조를 공급자로 사용할 수 있다.

        // 단점
        // 1. 싱글턴을 사용하는 클라이언트가 테스트하기 어려워진다.
        // 2. 리플렉션으로 Private 생성자를 호출할 수 있다.
        // 3. 역직렬화시 새로운 인스턴스가 생성될 수 있다.




        // 장점 정리
        // 장점 1 : API를 바꾸지 않고도 싱글턴이 아니게 변경 가능하다. 
        // -> 스레드 별로 다른 인스턴스 반환 가능
        // -> 예를들어, 코드에서 기존의 만든 instance가 아닌 new 생성자를 통해 매번 다른 인스턴스 반환 가능하다.
        // -> 이때, 사용자 API 즉, main 코드는 변경하지 않고 클래스 수정만으로도 가능하다.

        // 장점 2 : 정적 팩토리 메서드를 제네릭 싱글턴 팩토리로 만들 수 있다.
        Person2Generic<String> person5 = Person2Generic.getInstance();
        person5.send("hello");
        Person2Generic<Integer> person6 = Person2Generic.getInstance();
        person6.send(123);
        System.out.print("두 인스턴스가 같은가? : ");
        System.out.println(person5.equals(person6));
        System.out.println(person5);
        System.out.println(person6);

        // 장점 3 : 정적 팩토리의 메서드 참조를 공급자로 사용할 수 있다.
        // 1번, 2번, 3번 중 하나를 사용해서 공급자 전달
        System.out.println("장점3 확인");
        SingleTonTest test = new SingleTonTest();
        // 1번
        test.start(new Supplier<Person2>() {
            @Override
            public Person2 get(){
                return Person2.getInstance();
            }
        });
        // 2번
        test.start(() -> Person2.getInstance());
        // 3번
        test.start(Person2::getInstance);






        // 단점 정리
        // 단점 1 : 싱글턴을 사용하는 클라이언트가 테스트하기 어려워진다.
        // 만약, 아래의 send 메서드가 비용이 발생하는 코드라면 클라이언트는 매번 비용이 발생하기에 테스팅에 어려움을 겪는다.
        Person2 test1 = Person2.getInstance();
        test1.send("cost 발생");
        // 해결 방법
        // 인터페이스를 만들어 구현하고 해당 인터페이스를 구현하는 Mock 클래스를 만들어 테스팅
        ISingleTon mockObject = MockPerson2.getInstance();
        mockObject.send("cost 발생 안함");


        // 단점 2 : 리플렉션으로 Private 생성자를 호출할 수 있다.
        // ReflectionAttack 클래스 참조
        // 해결책 : private 생성자안에 오류 처리, Person2 참조


        // 단점 3 : 역직렬화시 새로운 인스턴스가 생성될 수 있다.
        // 인스턴스가 바뀜
        SerializeTest.doTest();
        // 해결책 : Person2 안에 readResolve 메서드 정의시 해결





        // 방법 3. 열거타입을 사용
        // 위의 방법 모든 단점을 보완한 구조임
        // 하지만 위에서 갖는 장점을 갖지를 못한다.
        enumPerson instance1 = enumPerson.instanceName;
        enumPerson instance2 = enumPerson.instanceName;
        enumPerson instance3 = enumPerson.instanceName;
        System.out.println(instance1);
        System.out.println(System.identityHashCode(instance1));
        System.out.println(instance2);
        System.out.println(System.identityHashCode(instance2));
        System.out.println(instance3); 
        System.out.println(System.identityHashCode(instance3));

    }
}
