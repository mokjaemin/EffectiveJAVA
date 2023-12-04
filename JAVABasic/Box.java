package JAVABasic;

import java.util.*;

public class Box <T>{
    private List<T> list = new ArrayList<>();

    public void add(T t){
        list.add(t);
    }

    public void print(){
        System.out.println(list);
    }
}
