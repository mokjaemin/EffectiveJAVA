package Item8;

import java.lang.ref.Cleaner;
import java.util.concurrent.CountDownLatch;

public class cleanerTest implements AutoCloseable{

    // create - 클리너 스레드 생성
    private static final Cleaner cleaner = Cleaner.create();

    private Cleaner.Cleanable cleanable;

    private final int idx;

    private final CountDownLatch countDownLatch;

    private static class State implements Runnable{

        private final int idx;

        private final CountDownLatch countDownLatch;

        private State(int idx, CountDownLatch countDownLatch){
            this.idx = idx;
            this.countDownLatch = countDownLatch;
        }

        // cleanerTest 생성자가 호출되거나 close 메서드가 호출될 때 실행
        @Override
        public void run(){
            System.out.println("before call cleaner - idx : " + idx + " , thread : " + Thread.currentThread().getName());
            System.out.println("after call cleaner - idx : " + idx + " , thread : " + Thread.currentThread().getName());
            countDownLatch.countDown();
        }
    }

    public cleanerTest(int idx, CountDownLatch countDownLatch){
        this.idx = idx;
        this.countDownLatch = countDownLatch;
        // this(cleanerTest)가 해제될 때, State 클래스가 실행된다.
        // 즉, 해당 객체가 null처리 될떄, run 매서드 실행됨.
        this.cleanable = cleaner.register(this, new State(idx, countDownLatch));
    }

    @Override
    public void close(){
        cleanable.clean();
    }
}
