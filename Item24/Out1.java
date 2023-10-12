package Item24;

public class Out1 {

    private int value;

    public Out1(int value){
        this.value = value;
    }

    public void print(){
        System.out.println(value);
    }

    public void printInValue(){
        in test = new in();
        System.out.println(test.valueIn);
    }

    private class in{
        private int valueIn = 0;
        private void printIn(){
            Out1.this.print();
        }
    }
}
