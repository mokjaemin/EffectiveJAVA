package Item14;

public class BasicInfo implements Comparable<BasicInfo>{

    private String name;


    public BasicInfo(String name){
        this.name = name;
    }

    public int compareTo(BasicInfo other){
        return this.name.compareTo(other.name);
    }

}
