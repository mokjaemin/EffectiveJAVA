package Item20;

public interface Person {

    void touch();
    void smell();
    void see();
    void taste();
    void listen();

    default void sayHi(){
        System.out.println("hi");
    }


}
