package Item10;

import java.util.*;

// 3장 : 모든 객체의 공통 메서드
// Item10 : equals는 일반 규약을 지켜 재정의하라.
public class Main {
    public static void main(String[] args){

        // 3장
        // - Object의 final이 아닌 메서드(equals, hashcode, toString, clone. finalize)는 모두 재정의를
        // 염두해두고 설계된 것임.
        // - Object를 상속하는 즉, 모든 클래스는 해당 메서드들을 재정의해야 함.
        // - 잘못 구현시 해당 메서드들을 재정의할 때 지켜야하는 규약을 잘 지켰다고 가정하는 클래스(HashMap, HashSet 등)에
        // 오작동을 불러일으킨다.




        // Item10 : equals는 일반 규약을 지켜 재정의하라.
        // - 기본적으로 재정의를 안하면 자기 자신과만 같게 처리 됨.


        // 1. equals를 재정의 안해도 되는 상황
        // 1-1. 각 인스턴스가 본질적으로 고유하다.
        // - 값을 나타내는 것이 아닌 동작 개체를 표현하는 클래스
        // - 예시 : Thread
        // 1-2. 인스턴스의 논리적 동치성을 검사할 일이 없다.
        // 1-3. 상위클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다.
        // - 예시 : Set은 AbstractSet, List는 AbstractList, Map은 AbstractMap의 equals를 상속받아서 사용한다.
        // 1-4. 클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다.
        // - 만약, equals를 사용하고 싶지않다면 내부에 throw new AssertionError() 작성해 막자.


        // 2. equals를 재정의 하는 상황
        // - 객체 식별성(두 객체가 물리적으로 같은가)이 아니라 논리적 동치성을 확인해야 하는데,
        // 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을 때 재정의한다.
        // - 주로 값 클래스가 여기에 해당된다. (String, Integer)
        // - 재정의시 Map의 키와 Set의 원소로 사용가능하다.
        // - 값 클래스라도 값이 같은 인스턴스가 2개이상 만들어지는 것을 방지하는 통제 클래스라면 재정의가 필요없다.
        // (Enum, String 등이 해당)


        // 3. equals를 재정의하기 위한 규칙
        // - 이 규칙을 지키지 않는다면 오류 발생.

        // 3-1. 반사성 : null이 아닌 모든 참조 값 x에 대하여 x.equals(x)는 true 이다.
        // - 객체는 자기 자신과 같아야 한다.

        // 3-2. 대칭성 : null이 아닌 모든 참조 값 x,y에 대하여 x.equals(y)는 true이면 y.equals(x)도 true이다.
        // - 잘못된 예시
        // -> equals는 대소문자를 무시함.
        // -> 위치를 바꾸었을때, 같은 결과를 출력하지 않음
        CaseInsesitiveString obj = new CaseInsesitiveString("Polish");
        String s = "polish";
        System.out.println(obj.equals(s));
        System.out.println(s.equals(obj));
        // - equals 규약을 어기면 그 객체를 사용하는 다른 객체들이 어떻게 반응하는지 알 수 없다.
        // -> 어떤 JDK에서는 false를 반환하기도 함.
        List<CaseInsesitiveString> list = new ArrayList<>();
        list.add(obj);
        System.out.print("포함하고 있는가? ");
        System.out.println(list.contains(obj));
        // - 이를 해결하기 위해서는 String의 equals와 연동되어야 하는데 말도 안되는 얘기이다.
        // - 수정한 코드는 클래스에 작성해 두었다. (같은 클래스의 인스턴스끼리만 비교)

        // 3-3. 추이성 : null이 아닌 모든 참조 값 x,y,z에 대하여 x.equals(y)는 true, y.equals(x)도 true이면 x.equals(z)도 true이다.
        // 첫번째 객체와 두번째 객체가 같고 두번째 객체와 세번째 객체가 같으면 첫번째와 세번째 객체도 같아야 한다.
        Color color1 = new Color(1, 1, 1);
        int x = 0;
        int y = 0;
        ColorPoint point1 = new ColorPoint(x, y, color1);
        Point point2 = new Point(x, y);

        // 3-3-1. ColorPoint 객체끼리만 비교
        // 대칭성 위배
        // System.out.println(point1.equals(point2));
        // System.out.println(point2.equals(point1));

        // 3-3-2. 색상 무시 비교
        // 추이성 위배
        // 스택오버플로우도 발생가능 - 다른 하위클래스 만들어 해당 하위클래스와 equals하는 경우
        Color color2 = new Color(2, 2, 2);
        ColorPoint p1 = new ColorPoint(1, 2, color1);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, color2);
        // 색상 무시
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        // 색상 고려
        System.out.println(p1.equals(p3));

        // 3-3-3. Point의 equals 메서드를 getClass()를 통해 처리
        // - 하위 클래스는 정의상 여전히 Point이므로 어디서든 Point로 활용이 가능해야한다.
        // - 위의 기본적인 조건이 위배됨
        Point p4 = new Point(1, 2);
        System.out.println(p2.equals11(p4));

        // 주어진 점이 단위 원안에 존재하는지 확인해주는 메서드
        Point p5 = new Point(1, 0);
        System.out.println(Point.onUnitCircle(p5));

        // 리스코프 치환 원칙 : 어떤 타입에 있어서 중요한 속성이라면 그 하위 타입에서도 마찬가지로 중요하다.
        // 따라서 그 타입의 모든 메서드가 하위 타입에서도 똑같이 작동해야 한다.
        // equals를 getClass를 통해 작성시 아래에서 false 반환
        CounterPoint p6 = new CounterPoint(1, 0);
        System.out.println(Point.onUnitCircle(p6));
        Set<Point> test = Set.of(new Point(1, 0), new Point(-1, 0));
        System.out.println(test.contains(p6));

        // - Set을 포함한 대부분의 Collection에서는 eqauls를 통해 객체간 비교를 한다.
        // - getClass를 통해 비교시 CounterPoint는 Point가 어떤 경우에서라도 될 수 없기 때문이다.

        // 다른 예시
        // - java.sql.Timestamp는 java.util.Date를 확장한 후 nanoseconds 필드를 추가하였다.
        // - 결과적으로, Timestamp의 eqauls는 대칭성을 위배, Date의 객체와 섞여 한 컬렉션에 들어가면 동작 오류 발생
        // - TimeStamp를 이렇게 설계한 것은 실수이니 따라하면 안된다.

        // 결론 : 구체 클래스를 확장해 새로운 값을 추가해가며 equals 규약을 만족시킬 방법은 존재하지 않는다.

        // 우회 방법
        // "상속대신 컴포지션을 사용하라"
        // - Point를 상속하는 대신 Point를 ColorPoint의 Private Field로 두고, ColorPoint와 같은 위치의
        // 일반 Point를 반환하는 View 메서드를 public으로 추가하는 방식
        // - 아래의 경우는 eqauls 규약을 지키면서 상위클래스에 값을 추가한 하위클래스를 만드는 경우이다.
        // - 위에서 나온 문제들을 해당 클래스는 하위클래스가 아님을 명시하며 해소하는 느낌이다.
        Point newP1 = new Point(0, 0);
        Color newColor = new Color(0, 0, 0);
        newColorPoint newP2 = new newColorPoint(newP1, newColor);
        newColorPoint newP3 = new newColorPoint(newP1, newColor);
        newColorPoint newP4 = new newColorPoint(newP1, newColor);
        System.out.println("대칭성 체크");
        System.out.println(newP2.equals(newP3));
        System.out.println(newP3.equals(newP2));
        System.out.println("추이성 체크");
        System.out.println(newP2.equals(newP3));
        System.out.println(newP3.equals(newP4));
        System.out.println(newP2.equals(newP4));


        // 61p부터 시작

        // 3-4. 일관성 : null이 아닌 모든 참조 값 x,y에 대하여 x.equals(y)를 반복해도 호출해도 같은 값이 나온다.


        // 3-5. null이 아님 : null이 아닌 모든 참조값 x에 대하여 x.equals(null)은 false이다.




    }
}
