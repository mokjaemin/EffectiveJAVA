package Lambda;

import java.util.*;
import java.util.function.*;

public class Test {

    public static List<Integer> list = Arrays.asList(0, 1, 2, 3);


    public static void Test1(Predicate<Integer> pr){
        for(int i : list){
            if(pr.test(i)){
                System.out.println(i);
            }
        }
    }

    public static void Test2(Consumer<Integer> co){
        for(int i : list){
            co.accept(i);
        }
    }

    public static void Test3(Function<Integer, Boolean> fu){
        for(int i : list){
            if(fu.apply(i)){
                System.out.println(i);
            }
        }
    }

    public static void Test4(Supplier<List<Integer>> su){
        List<Integer> supplies = su.get();
        for(int i : supplies){
            if(i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}
