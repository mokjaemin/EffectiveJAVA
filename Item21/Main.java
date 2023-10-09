package Item21;

import java.util.*;
import java.util.function.Predicate;

// Item 21 : 인터페이스는 구현하는 쪽을 생각해서 설계하라
public class Main {
    public static void main(String[] args){

        // 인터페이스의 가장 큰 단점은 메서드 추가시
        // 이미 해당 인터페이스를 구현한 구현체에 문제를 가한다는 것이다.

        // 자바8부터 디폴트 메서드의 도입으로 조금은 해소되었지만 완전히 해소되지는 않았다.
        // -> 해당 메서드를 구현체에서 재정의해서 사용하지 않는 한 구현체 모두에게 적합한 메서드를 만들 수 없기 때문이다.

        // 자바8부터 핵심 인터페이스들에 주로 람다 사용을 위한 많은 디폴트 메서드가 추가되었다.
        // 대부분 잘동작하지만 모든 상황을 고려해 문제없이 디폴트 메서드를 작성하는 것은 불가능하다.
        // 예시 : Collection의 removeIf 메서드
        // -> removeIf는 Predicate를 받아 Predicate가 true를 반환하는 멤버들을 remove를 이용해
        // 삭제하는 메서드이다.
        // -> 해당 메서드는 Collection을 구현한 구현체에 모두 적용되지 않는다.
        List<Integer> test = new ArrayList<>();
        for(int i=0; i<10; i++){
            test.add(i);
        }
        Predicate check = new Predicate() {
            @Override
            public boolean test(Object o) {
                if((Integer) o == 1){
                    return true;
                }
                return false;
            }
        };
        test.removeIf(check);
        System.out.println(test);


        // 자바에서 제공한 예방책
        // 구현한 인터페이스의 디폴트 메서드를 재정의하고,
        // 다른 메서드에서 디폴트 메서드를 호출하기 전에 필요한 작업을 수행하게 함
        // 예시로 Collections.SyncronizedCollection 이 반환하는 package-private 클래스들은
        // removeIf 를 재정의하고, 이를 호출하는 다른 메서드들은 디폴트 구현을 호출하기 전 동기화를 수행하게 함.
        // 하지만 자바 플랫폼에 속하지 못한 모든 구현체들이 이러한 방식을 적용하고 있지 않기 때문에 문제

        // 디폴트 메서드는 컴파일이 성공하더라도 기존 구현체에 런타임 오류를 일으킬 수도 있다.


        // 결론적으로 만들어져 이미 배포된 인터페이스에 디폴트 메서드를 추가하는 일은 피해야한다.
        // 반면, 새롭게 만드는 인터페이스라면 표준적인 메서드 구현을 제공하는 아주 유용한 수단이며,
        // 인터페이스를 쉽게 구현하게 해준다.

        // 인터페이스 구현은 정말 세심하게 하자

        // 인터페이스를 릴리스한 후라도 결함을 수정하는게 가능한 경우도 있겠지만, 절대 그 가능성에 기대서는 안된다.




    }
}
