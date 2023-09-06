package Item3;

import java.io.*;

import javax.management.RuntimeErrorException;

public class SerializeTest {
    

    public static void serialize(Object object, String fileName){
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deSerialize(String fileName){
        try {
            ObjectInput in = new ObjectInputStream(new FileInputStream(fileName));
            return in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doTest(){
        String fileName = "fileName";
        Person2 person2 = Person2.getInstance();
        serialize(person2, fileName);
        System.out.println(person2);

        Person2 result = (Person2) deSerialize(fileName);
        System.out.println(result);
        
    }
}
