package Item1;

public class main {
    public static void main(String[] args) {

        // Item1. 생성자보다 정적 팩터리 메서드를 고려하자.

        // Static을 활용하여 인스턴스를 제공하는 Boolean 클래스
        // 미리 인스턴스를 만들어 반환하는 구조
        Boolean testTrue = Boolean.valueOf(true);
        System.out.print("Boolean true test : ");
        System.out.println(testTrue);
        Boolean testFalse = Boolean.valueOf(false);
        System.out.print("Boolean false test : ");
        System.out.println(testFalse);


        // 장점
        // 1. 이름을 가질 수 있어 용도에 대한 이해도가 높아진다.
        car car1 = car.numOnly(0);

        // 2. 인스턴스 낭비를 막는다.
        car car2 = car.getExample();
        car car3 = car.getExample();
        System.out.print("인스턴스가 같은가? : ");
        System.out.print(System.identityHashCode(car2));
        System.out.print(" ");
        System.out.println(System.identityHashCode(car3));
        
        // 3. 하위 클래스도 반환가능
        car truck = car.getTruck();
        System.out.print("종류? : ");
        System.out.println(truck.getCarKind());

        // 4. 매개변수로 객체 생성 가능
        car truck1 = carFactory.getByName("truck");
        car sportscar1 = carFactory.getByName("sportscar");
        System.out.print("트럭 종류? : ");
        System.out.println(truck1.getCarKind());
        System.out.print("스포츠카 종류? : ");
        System.out.println(sportscar1.getCarKind());

        // 5. 클래스명으로 객체 생성 가능
        // 하지만 deprecated 되어 있음.
        car truck2 = carFactory.getByClass("Item1.truck");
        System.out.println(truck2.getClass());


        // 단점
        // 1. 정적 팩터리 메서드만을 제공하는 클래스의 경우 하위 클래스를 만들 수 없다.
        
        // 아래 쿨래스와 같이 정적 메서드만을 위한 클래스 사용시 private 생성자를 명시해
        // 생성을 못함을 명시하는 경우가 많은데, 이러한 경우 하위 클래스를 만들 수 없다.
        // (하위 클래스를 만들려면 protected 또는 public 생성자가 필요하기 때문에 하위 클래스 생성 불가)
        util.convertIntToString();
        util.convertStringToInt();

        // 2. 이름이 많아지고 길어질수록 복잡해져 용도를 이해하기 어려워진다.



    }
}
