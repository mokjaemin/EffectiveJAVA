package Item20;

import java.util.Objects;

public abstract class AbstractPerson{

    private int age;
    private String name;
    private int height;
    private int weight;

    // 인터페이스의 메서드
//    @Override
//    public void listen() {
//        System.out.println("listening");
//    }
//
//    @Override
//    public void see() {
//        System.out.println("seeing");
//    }
//
//    @Override
//    public void smell() {
//        System.out.println("smelling");
//    }
//
//    @Override
//    public void taste() {
//        System.out.println("tasting");
//    }
//
//    @Override
//    public void touch() {
//        System.out.println("touching");
//    }

    // Object의 메서드
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPerson that = (AbstractPerson) o;
        return age == that.age && height == that.height && weight == that.weight && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, height, weight);
    }


    // 추가적으로 필요한 getter/setter
    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
