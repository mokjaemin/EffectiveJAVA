package JAVABasic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {
    public static void main(String[] args){

        // String
        StringBuffer buffer = new StringBuffer();
        // Map
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        // List
        List<String> unsynchronizedList = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(unsynchronizedList);
        // Set
        Set<Integer> unsynchronizedSet = new HashSet<>();
        Set<Integer> synchronizedSet = Collections.synchronizedSet(unsynchronizedSet);



        // 현재 시각 얻기
        LocalDateTime now = LocalDateTime.now();

        // 출력 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        // 스레드 풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 공유 데이터 구조 (HashSet) 생성
        Set<Integer> sharedSet = new HashSet<>();

        // 스레드로 수행될 작업 생성 및 제출
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                // 각 스레드에서 HashSet에 1 추가
                synchronized (sharedSet) {
                    sharedSet.add(1);
                }
                System.out.println("Thread " + Thread.currentThread().getId() + " added 1 to the set.");
            });
        }

        // 모든 작업이 완료될 때까지 대기
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // 기다리는 동안 다른 작업 수행
            System.out.println("Current Time: " + now.format(formatter));
        }

        // 결과 출력
        System.out.println("Final set size: " + sharedSet.size());
        System.out.println("Contents of the set: " + sharedSet);
    }
}
