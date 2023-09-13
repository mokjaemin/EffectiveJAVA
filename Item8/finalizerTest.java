package Item8;

import java.util.concurrent.CountDownLatch;

public class finalizerTest {

    private final CountDownLatch countDownLatch;

    public finalizerTest(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    protected void finalize(){
        System.out.println("before call finalizer : " + Thread.currentThread());
        // 아래 코드에서 예외가 발생해도 알리지 않음
        // String a = null;
        // System.out.println(a.toString());
        System.out.println("after call finalizer : " + Thread.currentThread());
        countDownLatch.countDown();
    }
}
