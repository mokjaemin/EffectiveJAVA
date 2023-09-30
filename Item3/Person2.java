package Item3;

import java.io.Serializable;

public class Person2 implements ISingleTon, Serializable{

    private static final Person2 instancePerson = new Person2();
    private static boolean isCreated;

    private Person2(){
        if(isCreated){
            throw new UnsupportedOperationException("Already Exists");
        }
        else{
            isCreated = true;
        }
    }

    public static Person2 getInstance(){
        return instancePerson;
    }

    @Override
    public boolean send(String message){
        System.out.println(message);
        return true;
    }

    private Object readResolve(){
        return instancePerson;
    }
}
