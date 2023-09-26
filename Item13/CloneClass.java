package Item13;

public class CloneClass implements Cloneable{

    private String name;
    private int age;

    public CloneClass(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public CloneClass clone(){
        try{
            return (CloneClass) super.clone();
        }
        catch (CloneNotSupportedException e){
            // Cloneable을 구현했다면 일어나지 않음
            throw new AssertionError();
        }
    }

    @Override
    public String toString(){
        return "name : " + name + " age : " + String.valueOf(age);
    }
}
