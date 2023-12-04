package JAVABasic;

public class Person {
    private String name;
    private int age;
    private Sex sex;
    public Person(String name, int age, Sex sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public Sex getSex(){
        return this.sex;
    }
    @Override
    public boolean equals(Object o){
        if(o == null | o.getClass() != this.getClass()){
            return false;
        }
        Person new_o = (Person) o;
        if(this.age == new_o.age && this.name == new_o.name && this.sex == new_o.sex){
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        result = 31 * result + this.age;
        result = 31 * result + this.sex.hashCode();

        return result;
    }
}
