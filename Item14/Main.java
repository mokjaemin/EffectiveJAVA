package Item14;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

// Item 14 : Comparable을 구현할지 고려하라.
public class Main {


    public static void main(String[] args){

        // Comparable의 유일무이한 메서드인 compareTo 메서드


        // 1. equals 메서드와 다른점
        // - Object의 메서드가 아니라 Comparable의 메서드이다.
        // - 단순 동치성 비교에 더해 순서까지 비교할 수 있으며 제네릭하다.
        // (Comparable을 구현했다는 것은 클래스의 인스턴스들에 자연적인 순서가 존재함을 의미한다.)
        // - 따라서, 정렬이 가능하다.
        // - 검색, 극단값 계산, 자동 정렬되는 컬렉션관리 가능
        // (TreeSet, TreeMap, Collections, Array)

        // "알파벳, 숫자 등과 같이 순서가 명확한 값 클래스를 작성한다면 반드시 Comparable 인터페이스를 구현하자"


        // 2. compareTo의 일반규약
        // - 이 객체와 주어진 객체의 순서를 비교한다.
        // - 이 객체가 주어진 객체보다 작다면 음수(-1), 같으면 0, 크다면 양수(1)를 반환한다.
        // - 비교 불가능하다면, 클래스의 타입이 다르다면 ClassCastException 처리를 한다.

        // -> sgn(x.compareTo(y) == -sgn(y.compareTo(x)))
        // -> x.compareTo(y) > 0 && y.compareTo(z) => x.compareTo(z) > 0
        // -> x.compareTo(y) == 0 => x.compareTo(z) == y.compareTo(z)
        // -> x.compareTo(y) == 0 => x.equals(y)
        // (equals와 마찬가지로 기존 클래스를 확장한 구체 클래스에서 새로운 값 컴포넌트를 추가했다면 compareTo 규약을 지키지 못한다.)
        // (우회 방법 : 독립된 클래스를 만들고, 이 클래스에 원래 클래스의 인스턴스를 가리키는 필드를 둔다. 이 후, 내부 인스턴스를 반환하는 뷰 메서드를 둔다.)
        // (HashSet은 equals 비교 TreeSet은 compareTo 비교)
        BigDecimal instance1 = new BigDecimal("1.0");
        BigDecimal instance2 = new BigDecimal("1.00");
        HashSet set1 = new HashSet();
        TreeSet set2 = new TreeSet();
        set1.add(instance1);
        set1.add(instance2);
        set2.add(instance1);
        set2.add(instance2);
        System.out.println(set1.size());
        System.out.println(set2.size());


        // 3. compareTo 작성요령

        // - compareTo 메서드에서 관계연산자 <, >를 사용하는 것은 오류를 발생하니 더이상 추천하지 않는다.
        // - 박싱된 기본타입 클래스들에 새로 추가된 정적 메서드인 compare을 이용한다.
        char a = 'a';
        char b = 'b';
        System.out.println(Character.compare(a, b));
        System.out.println(Integer.compare(1, 2));
        System.out.println("a".compareTo("b"));


        // - implements Comparable<Class> 를 작성시 해당 클래스와만 비교한다고 명시한다.
        // - 객체 참조 필드를 비교하려면 compareTo 메서드를 재귀적으로 호출한다.
        // - 핵심필드가 여러개라면 가장 핵심적인 필드부터 순차적으로 비교해간다.
        BasicInfo basicInfo1 = new BasicInfo("a");
        BasicInfo basicInfo2 = new BasicInfo("b");
        Info info1 = new Info(basicInfo1, 1);
        Info info2 = new Info(basicInfo2, 2);
        System.out.println("기본 방식 사용 : " + info1.compareTo(info2));

        // - Comparable을 구현하지 않은 필드라면 비교자(Comparator)를 사용한다.
        // - 비교자를 연쇄적으로 생성해 비교가 가능하지만 약간의 성능저하가 뒤따른다.
        // - 메서드 종류 : comparingInt 등 기본타입 비교, comparing으로 객체 참조 비교
        System.out.println("생성자 사용 : " + info1.compareTo(info2));



        // 해시코드를 통해 비교를 하는경우

        // 잘못된 예시
        // hashCodeOrder1 : 정수 오버플로우 (범위 초과)가 발생할 수 있다.

        // 다른 두개의 방식을 대신 사용하자.
        // hashCodeOrder2
        // hashCodeOrder3



    }

    public static Comparator<Object> hashCodeOrder1 = new Comparator<Object>() {
        public int compare(Object o1, Object o2) {
            return o1.hashCode() - o2.hashCode();
        }
    };

    public static Comparator<Object> hashCodeOrder2 = new Comparator<Object>() {
        public int compare(Object o1, Object o2) {
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }
    };

    public static Comparator<Object> hashCodeOrder3
            = Comparator.comparingInt(o -> o.hashCode());


}
