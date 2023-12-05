package ThreadTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {

    private final int poolSize;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] threads;
    private volatile boolean isShutdown = false;

    public MyThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new Thread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
    }

    public void submit(Runnable task) {
        if (!isShutdown) {
            taskQueue.offer(task);
        }
    }

    public void shutdown() {
        isShutdown = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = taskQueue.take(); // 작업을 가져옴 (블록됨)
                    task.run(); // 작업 실행
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {

        MyThreadPool threadPool = new MyThreadPool(3);

        // 작업 제출
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            threadPool.submit(() -> System.out.println("Task ID: " + taskId + " executed by thread: " + Thread.currentThread().getName()));
        }

        // 스레드 풀 종료
        threadPool.shutdown();
    }
}

