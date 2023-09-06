package Item3;

import java.util.function.Supplier;

public class SingleTonTest {
    
    public void start(Supplier<Person2> supplier){
        Person2 person2 = supplier.get();
        person2.send("Hello World");
    }
}
