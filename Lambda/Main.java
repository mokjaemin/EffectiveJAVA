package Lambda;

import java.util.function.*;
import java.util.*;
import java.util.jar.Attributes;

public class Main {
    public static void main(String[] args){
        Predicate<Integer> option1 = arr -> arr % 2 == 0;
        Test.Test1(arr -> arr % 2 == 0);
        Test.Test1(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if(integer % 2 == 0){
                    return true;
                }
                else{
                   return false;
                }
            }
        });

        Consumer<Integer> option2 = arr -> {if(arr % 2 == 0){System.out.println(arr);} };
        Test.Test2(option2);
        Test.Test2(arr -> {if(arr % 2 == 0){System.out.println(arr);} });

        Test.Test2(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                if(integer % 2 == 0){
                    System.out.println(integer);
                }
            }
        });

        Function<Integer, Boolean> option3 = arr -> arr % 2 == 0;
        Test.Test3(option3);
        Test.Test3(arr -> arr % 2 == 0);

        Test.Test3(new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                if(integer % 2 == 0){
                    return true;
                }
                else{
                    return false;
                }
            }
        });

        Supplier<List<Integer>> option4 = () -> Arrays.asList(0, 1, 2, 3);
        Test.Test4(option4);
        Test.Test4(() -> Arrays.asList(0, 1, 2, 3));

        Test.Test4(new Supplier<List<Integer>>() {
            List<Integer> list = Arrays.asList(0, 1, 2, 3);
            @Override
            public List<Integer> get() {
                return list;
            }
        });

    }
}
