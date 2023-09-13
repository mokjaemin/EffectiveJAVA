package Item8;



// Item8 : finalizer와 cleaner 사용을 피하라
public class main {
    public static void main(String[] args){


        // 자바의 두가지 객체 소멸자 - finalizer, cleaner
        // 결론 : 자바에서는 객체회수(가비지컬렉터가 담당), 비메모리 자원을 회수(try-with-resources 등)이 담당하므로
        // 여러가지 문제점이 있는 finlaizer와 cleaner로 객체 회수나 비메모리 자원을 회수하는 일을 굳이 담당할 필요가 없다.

        // finalizer
        // - 예측할 수 없고 상황에 따라 위험할 수 있어 일반적으로 불필요하다.
        // - 오작동, 낮은 성능, 이식성 문제의 원인이 됨.

        // cleaner
        // - finalizer보다 덜 위험하지만 여전히 에측할 수 없고, 느리고, 일반적으로 불필요하다.


        // 자바에서는 접근할 수 없게 된 객체를 회수하는 역할을 가비지 컬렉터가 담당한다.
        // 자바에서는 비메모리 자원을 회수를 하는 일은 try-with-resources 또는 try-finally가 담당한다.




        // 문제점

        // 문제점 1. finalizer와 cleaner는 즉시 실행된다는 보장이 없다.
        // 실행되기까지 얼마나 걸릴지 예측이 불가능하고 이 때문에, 제때 실행되어야 하는 작업을 할 수 없다.
        // 예를 들어, 파일 닫기를 이에 맡기면 시스템이 동시에 열 수 있는 파일의 수가 제한적이기 때문에 오류 발생

        // 문제점 2. finalizer와 cleaner는 수행 여부 또한 보장되지 않는다.
        // 이는 접근할 수 없는 일부 객체에 딸린 작업을 전혀 수행하지 못한채 프로그램이 중단될 수 있음을 의미.
        // 상태를 영구적으로 수정하는 작업에서는 절대 이를 사용하면 안된다.
        // 예를 들어, 데이터베이스와 같은 공유자원에 영구 락(lock) 해제를 맡기면 큰 문제가 발생될 것이다.
        // 추가적으로, System.gc나 System.runFinalization 과 같은 메서드는 실행될 가능성을 높여주지만 보장하진 않는다.

        // 문제점 3 : finalizer와 cleaner는 심각한 성능 문제도 동반한다.
        // try-with-resources 사용해 객체 생성하고 가비지 컬렉터가 수거하는데 12ns 소요
        // finalizer 사용하여 객체 생성 및 파괴가 550ns 소요

        // 문제점 4 : finalizer를 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 문제를 일으킬 수 있다.
        // finalizer의 공격 원리 : 생성자나 직렬화 과정에서 예외 발생시 이 생성되다 만 객체에서 악의적인 하위 클래스의 finalizer가 수행될 수 있다.
        

    }
}
