package ThreadTest;

public class Member {
    private int id;
    private String name;
    private static int count = 1;
    public Member(String name){
        this.name = name;
        this.id = count;
        count += 1;
    }
    @Override
    public String toString(){
        return String.valueOf(id) + name;
    }
}
