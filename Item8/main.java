package Item8;


import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;


// Item8 : finalizer와 cleaner 사용을 피하라
public class main {
    public static void main(String[] args) throws Exception{


        // 자바의 두가지 객체 소멸자 - finalizer, cleaner
        // 결론 : 자바에서는 객체회수(가비지컬렉터가 담당), 비메모리 자원을 회수(try-with-resources 등)이 담당하므로
        // 여러가지 문제점이 있는 finlaizer와 cleaner로 객체 회수나 비메모리 자원을 회수하는 일을 굳이 담당할 필요가 없다.

        // finalizer
        // - 예측할 수 없고 상황에 따라 위험할 수 있어 일반적으로 불필요하다.
        // - 오작동, 낮은 성능, 이식성 문제의 원인이 됨.
        // - 모든 클래스는 Object 클래스를 상속받는데 Object 클래스는 finlaizer를 가지고 있음
        Object a = new Object();
        // - 별도의 스레드풀을 가지고 동작
        // - Deprecated됨.

        CountDownLatch countDownLatch = new CountDownLatch(1);
        finalizerTest sample = new finalizerTest(countDownLatch);
        System.out.println(sample);

        // 참조 해제
        sample = null;

        // 가비지 컬렉터 수행
        // 만약, finalizer가 정의되어 있다면 이를 통해 객체 수거해감.
        System.gc();
        System.out.println("end");
        System.out.println(sample);
        countDownLatch.await();


        // (+) CountDownLatch 클래스
        // 동시성 및 병렬 프로그래밍을 지원하는 클래스
        // 한 스레드가 다른 스레드 작업이 완료될 때까지 기다려야 하는 상황에 용이
        // - 병렬 처리 작업, 스레드풀 관리, 다단계 작업 조정
        // 생성자(카운트 수) : 객체 초기화 후 카운트 설정, 카운트는 카운트 다운이 시작되는 작업의 수를 의미
        // await 메서드 : 현재 스레드를 대기 상태로 만들고 카운트가 0이될때까지 기다린다.
        // countDown : 카운트를 1씩 감소




        // cleaner
        // - finalizer보다 덜 위험하지만 여전히 에측할 수 없고, 느리고, 일반적으로 불필요하다.

        final int MAX_OBJ_CNT = 5;
        CountDownLatch countDownLatch1 = new CountDownLatch(MAX_OBJ_CNT);
        cleanerTest[] arr = new cleanerTest[MAX_OBJ_CNT];
        // 샘플 생성
        for(int i=0; i<MAX_OBJ_CNT; i++){
            arr[i] = new cleanerTest(i, countDownLatch1);
        }
        // 참조 해제
        for(int i=0; i<MAX_OBJ_CNT; i++){
            arr[i] = null;
        }
        // 가비지 컬렉터
        System.gc();
        while(true){
            if(countDownLatch1.getCount() == 0){
                break;
            }
        }
        System.out.println("end");


        // 자바에서는 접근할 수 없게 된 객체를 회수하는 역할을 가비지 컬렉터가 담당한다.
        // 자바에서는 비메모리 자원을 회수를 하는 일은 try-with-resources 또는 try-finally가 담당한다.




        // 문제점

        // 문제점 1. finalizer와 cleaner는 즉시 실행된다는 보장이 없다.
        // 실행되기까지 얼마나 걸릴지 예측이 불가능하고 이 때문에, 제때 실행되어야 하는 작업을 할 수 없다.
        // 예를 들어, 파일 닫기를 이에 맡기면 시스템이 동시에 열 수 있는 파일의 수가 제한적이기 때문에 오류 발생
        // System.gc나 System.runFinalization 과 같은 메서드는 실행될 가능성을 높여주지만 보장하진 않는다.
        // System.runFinalizersOnExit, Runtime.runFinalizersOnExit가 둘의 실행을 보장하지만 메서드 자체가 결함이 많다.

        // 문제점 2. finalizer와 cleaner는 수행 여부 또한 보장되지 않는다.
        // 이는 접근할 수 없는 일부 객체에 딸린 작업을 전혀 수행하지 못한채 프로그램이 중단될 수 있음을 의미.
        // 상태를 영구적으로 수정하는 작업에서는 절대 이를 사용하면 안된다.
        // 예를 들어, 데이터베이스와 같은 공유자원에 영구 락(lock) 해제를 맡기면 큰 문제가 발생될 것이다.

        // 문제점 3. 예외 처리를 안해준다.
        // 메서드 안에서 예외가 발생하더라도 개발자에게 알리지 않는다.
        // cleaner는 자신을 처리할 스레드를 통제하기 때문에 해당 문제는 발생하지 않는다.

        // 문제점 4 : finalizer와 cleaner는 심각한 성능 문제도 동반한다.
        // 기본적으로 GC과정에서 일어나는데 GC 자체가 비용이 큼.
        // try-with-resources 사용해 객체 생성하고 가비지 컬렉터가 수거하는데 12ns 소요
        // finalizer 사용하여 객체 생성 및 파괴가 550ns 소요

        // 문제점 5 : finalizer를 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 문제를 일으킬 수 있다.
        // finalizer의 공격 원리 : 생성자나 직렬화 과정에서 예외 발생시 이 생성되다 만 객체에서 악의적인 하위 클래스의 finalizer가 수행될 수 있다.
        // 객체 생성을 막으려면 생성자에 예외를 던지는 것만으로도 충분하지만 finalizer 사용시 그렇지 않다.
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        Zombie zombie = new Zombie(countDownLatch2);
        System.out.println(zombie);
        zombie = null;
        System.gc();
        countDownLatch2.await();
        System.out.println(zombie);
        // zombie가 회수되지 않음
        // 메모리 누수 발생
        System.out.println(Zombie.zombie);

        // final이 아닌 클래스를 finlaizer 공격으로부터 방어하려면 아무일도 하지 않는 finalizer메서드를 만들고 final로 선언하자.
        // - final이 아닌 클래스를 상속받은 클래스가 있다고 가정할 때, 부모 클래스의 finalizer메서드를 final로 처리해 자식 클래스가 해당 메서드를 사용못하게 처리로 보안


        // 파일이나 스레드 등 종료해야할 자원을 담고있는 객체의 클래스에서 객체 소멸 방법
        // - 클래스에 AutoCloseable을 구현
        // - 클라이언트에서 인스턴스를 다 쓰고 close 메서드 사용
        // - 예외가 발생해도 제대로 종료되도록 try-with-resources 사용
        // - 각 인스턴스는 자신이 종료되었는지 추적하는 것이 좋다 따라서, 해당 객체가 더이상 유효하지 않음을 필드에 기록
        // 다른 메서드에서 해당 필드를 검사해서 이미 객체가 삭제 후 호출됐다면 IllegalStateException 발생


        // 사용하는 경우
        // 안전망
        // - 클라이언트가 close 메서드를 호출하지 않는 것에 대한 안전망
        // - FileInputStream, FileOutputStream, ThreadPoolExecutor 등에서 사용

        // 1. close 사용
        CountDownLatch countDownLatch3 = new CountDownLatch(1);
        cleanerTest sample4 = new cleanerTest(0, countDownLatch3);
        sample4.close();

        // 2. try-with-resources 사용시 자동제거 필요가 없어짐
        try(cleanerTest sample5 = new cleanerTest(0, countDownLatch3)){
            System.out.println("sample 5 청소");
        }

        // 3. 생성자로 생성시 자동으로 run메서드가 실행되어 객체 삭제가 되어야하지만 안됨
        cleanerTest sample6 = new cleanerTest(0, countDownLatch3);


        // 네이티브 피어와 연결된 객체에서 사용.
        // - 자바 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체를 의미
        // - 가비지 컬렉터는 자바 객체가 아닌 것은 수거를 못함
        // - 이러한 경우에 사용


        // 이외에는 사용하지 말자

    }
}
