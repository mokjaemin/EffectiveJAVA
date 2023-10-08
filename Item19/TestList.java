package Item19;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TestList<E> extends AbstractList {

    private static List<Integer> data = new ArrayList<>();

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Object get(int index) {
        return data.get(index);
    }

    @Override
    public boolean add(Object o){
        return data.add((Integer) o);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        for (int i=fromIndex; i<toIndex; i++) {
            data.remove(fromIndex);
        }
        System.out.println("hello");
    }
}
