package Item13;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack implements Cloneable{

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }

    public int getSize(){
        return size;
    }

    public Object get(int index){
        if(elements[index] == null){
            throw new NullPointerException();
        }
        return elements[index];
    }

    public Object pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        // 기존
//        return elements[--size];

        // 메모리 누수 문제 해결
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity(){
        if(elements.length == size){
            elements = Arrays.copyOf(elements, 2*size+1);
        }
    }

    @Override
    public Stack clone(){
        try{
            Stack result = (Stack) super.clone();
            result.elements = elements.clone();
            return result;
        }
        catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
