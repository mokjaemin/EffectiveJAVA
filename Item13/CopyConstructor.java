package Item13;

public class CopyConstructor {

    private int value;

    public CopyConstructor(int value){
        this.value = value;
    }

    public CopyConstructor(CopyConstructor other){
        this.value = other.value;
    }

    public int getValue(){
        return this.value;
    }
}
