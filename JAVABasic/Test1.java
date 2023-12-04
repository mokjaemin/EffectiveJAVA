package JAVABasic;


import java.util.HashSet;

// ==, equals, hashcode
// Abstract
public class Test1 {
    public static void main(String[] args){
        Person person1 = new Person("목재민", 27, Sex.MAN);
        Person person2 = new Person("목재민", 27, Sex.MAN);
        HashSet<Person> set = new HashSet<>();
        set.add(person1);
        set.add(person2);
        System.out.println(set.size());

        Cat cat1 = new Cat();
        System.out.println(cat1.getType());
        System.out.println(cat1.canUTalk());
    }
}
