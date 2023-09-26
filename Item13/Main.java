package Item13;

// Item 13 : clone 재정의는 주의해서 진행하라
public class Main {
    public static void main(String[] args){

        // 시작
        // Cloneable는 복제해도 되는 클래스임을 명시하는 인터페이스
        // clone메서드는 해당 인터페이스가 아닌 Object에 protected로 선언되어 있음.
        // 따라서, Cloneable을 구현하는 것만으로는 clone메서드를 호출할 수 없다.


        // "실무에서 Cloneable을 구현한 클래스는 clone메서드를 public으로 제공하며,
        // 사용자는 당연히 복제가 제대로 이루어지리라 기대한다."



        // 1. Cloneable 인터페이스의 역할
        // - 기본적으로 해당 인터페이스는 Object의 protected 메서드인 clone의 동작 방식을 결정한다.
        // - 해당 인터페이스를 구현하고 object의 clone메서드를 재정의해 호출시 해당 클래스의 필드를 복사한 객체 반환
        // - 해당 인터페으스를 구현안하고 clone 메서드 사용시 CloneNotSupportedException 발생


        // 2. 기본적인 구현 방법
        // - Cloneable 인터페이스 구현
        // - Override로 clone 메서드 구현
        // -> super.clone() : object의 clone메서드로 해당 클래스 복사 후 casting 실행
        // - try-catch로 오류 처리
        String name = "mok";
        int age = 27;
        CloneClass instance1 = new CloneClass(name, age);
        CloneClass instance2 = instance1.clone();
        System.out.println(instance1);
        System.out.println(instance2);


        // 3. 클래스가 가변 객체를 참조하는 경우
        // - 예시 : Stack
        // - elements 필드는 위의 방법으로 복사시 기존 인스턴스와 똑같은 인스턴스를 참조하게 되어
        // 하나 수정시 둘다 수정되어 복사의 개념에 위배된다.

        // 해결책
        // - clone메서드는 사실상 생성자와 같은 효과를 낸다.
        // - 즉, clone은 원본 객체의 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.
        // -> elements 배열의 clone을 재귀적으로 호출한다.
        // - 해당 필드가 final로 선언되어있다면 동작하지 않는다.
        Stack stack1 = new Stack();
        stack1.push(name);
        Stack stack2 = stack1.clone();
        stack2.push(name);
        System.out.println(stack1.getSize());
        System.out.println(stack2.getSize());


        // 4. clone을 재귀적으로 호출하는 것만으로 충분하지 않는 경우
        // - 예시 : HashTable
        // 해시테이블 내부는 버킷들의 배열이고 각 버킷들은 키-값 쌍을 담는 연결리스트의 첫번째 엔트리를 참조한다.
        // 복제본은 자신만의 버킷배열을 갖지만 이 배열은 원본과 같은 연결리스트를 참조하여 문제 야기
        // -> 즉, Entry 각각이 원본과 같은 인스턴스를 참조하게 된다.

        // 해결책
        // - 각 버킷을 구성하는 연결리스트를 복사한다.
        // -> 즉, Entry 클래스에 deepCopy를 선언해 Entry 자체를 새로 생성해 복사하는 메서드를 만들고
        // 이 후, 하나하나 넣는 방식으로 복사를 진행한다.

        // 하지만 deepcopy시에 자신이 가리키는 연결리스트에 대해서 재귀적으로 deepcopy를 호출하므로
        // 스택 오버플로를 일으킬 문제가 발생한다.


        // 해결책
        // - deepCopy를 재귀 호출 대신 반복자를 사용하여 순회하는 방향으로 수정해야 한다.


        // 5. clone의 문제점
        // - 생성자를 쓰지 않아 인스턴스를 생성하는 과정에서 오류가 발생할 수 있다.
        // - clone의 규약이 정확하지 않다.
        // - final 필드를 제대로 사용할 수 없다.
        // - 불필요한 예외, 형변환이 많다.


        // 6. 결론
        // -> 복사 생성자(변환 생성자)와 복사 팩터리(변환 팩터리)라는 더 나은 객체 복사 방식을 사용하자.
        // 복사 생성자 : 자신과 같은 클래스의 인스턴스를 인수로 받는 생성자


    }
}
