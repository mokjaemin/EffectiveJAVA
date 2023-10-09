package Item20;

public interface TestInterface {

    default void defaultMethod(){
        System.out.println("Default Method");
    }

    void AbstractMethod();

}
