package Item8;

import java.util.concurrent.CountDownLatch;

public class Zombie {

    static Zombie zombie;

    private CountDownLatch countDownLatch;

    public Zombie(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    protected void finalize() throws Throwable{
        System.out.println("call finalize");
        zombie = this;
        countDownLatch.countDown();
    }
}
