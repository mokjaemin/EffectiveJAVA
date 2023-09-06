package Item3;

public class Person2Generic<T> {
    private static final Person2Generic<?> instance = new Person2Generic<>();

    private Person2Generic(){

    }

    public static <T> Person2Generic<T> getInstance(){
        return (Person2Generic<T>) instance;
    }


    public boolean send(T message){
        System.out.println(message);
        return true;
    }

}
