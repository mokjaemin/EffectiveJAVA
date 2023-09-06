package Item3;

public class MockPerson2 implements ISingleTon{

    private static final MockPerson2 instance = new MockPerson2();

    private MockPerson2(){

    }

    public static MockPerson2 getInstance(){
        return instance;
    }

    @Override
    public boolean send(String message){
        System.out.println(message);
        return true;
    }
}
