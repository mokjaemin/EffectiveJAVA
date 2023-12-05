package ThreadTest;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args){

        MemberController controller = new MemberController();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 스레드로 수행될 작업 생성 및 제출
        for (int i=1; i<10; i++) {
            executorService.execute(() -> {
                Member member = new Member("목재민");
                controller.registerMember(member);
                System.out.println("Thread " + Thread.currentThread().getId());
            });
        }

        // 모든 작업이 완료될 때까지 대기
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // 기다리는 동안 다른 작업 수행
        }

        for(Object m : controller.getMember()){
            System.out.print(m);
            System.out.print(" ");
        }
        System.out.println("");

    }
}
