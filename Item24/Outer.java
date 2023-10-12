package Item24;

public class Outer {

    private int outerField = 10;
    private static final int outerField2 = 11;

    public void doSomething1() {

        Inner inner = new Inner() {
            @Override
            public void print() {
                System.out.println("Inner field: " + outerField);
            }
        };

        inner.print();
    }

    public static void doSomething2() {

        Inner inner2 = new Inner() {
            @Override
            public void print() {
                System.out.println("Inner field: " + outerField2);
            }
        };

        inner2.print();
    }

    // 중첩 클래스 Inner
    private abstract static class Inner {
        public abstract void print();
    }

}
