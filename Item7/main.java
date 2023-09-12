package Item7;


import java.util.*;

// Item7 : 다 쓴 객체 참조를 해제하라
public class main {
    public static void main(String[] args){

        // 주제 : 매모리 누수 문제를 해결하자
        // 가비지 컬렉터가 다 쓴 객체를 회수하지 못하는 경우들을 알고 대비하자
        
        
        // C, C++ 처럼 메모리를 직접 관리하는 언어에 비해 자바의 가비지 컬렉터는 굉장히 편안함을 제공함.
        // 다 쓴 객체를 회수해주기 때문에

        
        // 문제점 : 메모리 누수
        // 해당 스택을 계속 사용하다보면 점차 가비지 컬렉션 활동과 메모리 사용량이 늘어나 성능 저하
        // 심한 경우 디스크 페이징이나 OutOfMemoryError를 일으켜 프로그램이 예기치 않게 종료 가능

        // 메모리 누수가 일어나는 부분

        // 메모리 누수 1. 스택이 커졌다가 줄어들때, 스택에서 꺼내진 객체들이 가비지 컬렉터로 가지 않음
        // 스택이 다 쓴 객체들의 참조를 여전히 가지고 있기 때문이다.

        // 구현된 Stack
        Stack stack = new Stack();
        // 삽입
        for(int i=0; i<10; i++){
            stack.push(new Object());
        }
        for(int i=0; i<10; i++){
            System.out.println(System.identityHashCode(stack.get(i)));
        }
        System.out.println("삭제 후");
        // 삭제
        for(int i=0; i<10; i++){
            stack.pop();
        }
        for(int i=0; i<10; i++){
            System.out.println(System.identityHashCode(stack.get(i)));
            // System.out.println(stack.get(i));
        }
        // -> 그대로 남아있음
        // 이유 : size 변수를 증가/감소 하면서 실제 삭제가 아닌 가리키는 인덱스를 변경하는 구조이기 때문에

        

        // 해결책 : 참조 해제
        // null 처리를 통해 다 쓴 객체를 참조 해제한다.
        // -> null 처리한 참조를 실수로 사용하면 프로그램은 즉시 NullPointException 던지게 설정
        // -> 하지만 지나친 null처리는 프로그램을 지저분하게 만든다.


        // "객체 참조를 null처리 하는 것은 예외적인 경우여야 한다."
        // -> 다 쓴 객체를 참조 해제하는 가장 좋은 방법은 유효 범위 밖으로 밀어내는 방법이 가장 좋다.
        // -> 변수의 범위를 최소가 되게 정의했다면 자연스럽게 참조 해제 처리


        // null 처리를 하는 경우
        // 스택이 메모리 누수에 취약한 이유 : 자기 메모리를 직접 관리하기 때문에
        // -> 스택은 객체 자체를 담는 것이 아닌 객체 참조를 담는다.
        // -> 배열의 활성 영역에 속한 원소들은 사용되고 비활성화 영역은 쓰이지 않는다.
        // -> 가비지 컬렉터는 이 사실을 알지 못한다.
        // -> 비활성화 영역이 되는 순간(스택에 해당 공간이 안쓰이는 순간) null 처리를 통해 가비지 컬렉터에게 해당 객체를 더이상 쓰지 않을 것임을 알린다.
        // 결론적으로, 자기 메모리를 직접 관리하는 클래스라면 프로그래머는 항시 메모리 누수에 주의해야 한다.
        // 다 쓴 객체는 null처리를 해줘야함을 의미.

        
        // 메모리 누수 2. 캐시 역시 메모리 누수를 일으키는 주범이다.
        // -> 캐시 외부에서 키를 참조하는 동안만 엔트리가 살아있는 캐시가 필요한 상황이라면 WeakHashMap을 사용하자
        // -> 이는 다 쓴 엔트리는 즉시 자동으로 제거해준다.
        // -> 단 WeakHashMap은 이런 상황에서만 유용하다.

        // (엔트리 : 키-값 구조)

        WeakHashMap<Integer, String> map = new WeakHashMap<>();

        Integer key1 = 1000;
        Integer key2 = 2000;

        map.put(key1, "객체 1");
        map.put(key2, "객체 2");

        key1 = null;

        System.out.println("삭제 전 ->");
        for(Integer key : map.keySet()){
            System.out.println(map.get(key));
        }

        System.gc(); // 강제 Garbage Collection
        // 추가적으로 Integer 같은 경우, 특정범위 (약 : 128) 까지는 캐싱처리 되어서
        // 삭제가 안됨.
        
        System.out.println("삭제 후 ->");
        for(Integer key : map.keySet()){
            System.out.println(map.get(key));
        }


        // 보통은 캐시 엔트리의 유효기간을 알기 어렵기 때문에 시간이 지날수록 엔트리의 가치를 떨어뜨리는 방식을 사용
        // -> 이런 방식에서는 쓰지 않는 엔트리를 청소해주어야 한다.
        // -> 백그라운드 스레드를 활용하거나 캐시에 새 엔트리를 추가할 때, 부수작업으로 수행하는 방법 존재
        // -> LinkedHashMap은 removeEldestEntry 메서드를 써서 부수작업으로 수행하는 방법을 사용한다.
        // -> 복잡한 캐시를 만들고 싶다면 java.lang.ref 패키지 활용

        // 메모리 누수 3. 메모리 누수의 세번째 주범은 리스너와 콜백
        // -> 클라이언트는 콜백을 등록만하고 명확히 해지하지 않는다.
        // -> 이렇게 되면 콜백은 쌓여갈것이다.
        // -> 콜백을 약한참조로 저장하면 가비지 컬렉터가 즉시 수거해간다.
        // -> 예를 들어, WeakHashMap의 키로 저장하면 된다.



    }
}
