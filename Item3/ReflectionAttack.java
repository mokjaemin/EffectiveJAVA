package Item3;

import java.lang.reflect.Constructor;

public class ReflectionAttack {
    
    // @SneakyThrows
    // Lombok 설정해야 함.
    // public static <T> T getNewInstance(Class<?> clz){
    //     Constructor<?> declaredConstructor = clz.getDeclaredConstructor();
    //     // 생성자를 접근 가능하게 변경
    //     declaredConstructor.setAccessible(true);
    //     T newInstance = (T) declaredConstructor.newInstance();

    //     return newInstance;
    // }

    public static void doTest(){
        // Person2 instance = Person2.getInstance();
        // Person2 newInstance = getNewInstance(Person2.class);

        // 인스턴스가 변경됨 확인
        // System.out.println(instance);
        // System.out.println(newInstance);
    }
}
