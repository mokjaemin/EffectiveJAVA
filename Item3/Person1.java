package Item3;

public class Person1 {
    
    public static final Person1 instancePerson = new Person1();

    // instance 생성시 딱 한번만 호출됨
    // public이나 protected 생성자가 없으므로 Person1의 인스턴스는 전체 시스템에 오직 하나만 존재
    private Person1(){

    }


    public void moving(){
        System.out.println("Moving Well");
    }
}
