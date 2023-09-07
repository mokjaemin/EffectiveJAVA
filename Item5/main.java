package Item5;


// 아이템 5 : 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라.
public class main {
    public static void main(String[] args){
        
        // 많은 클래스가 하나 이상의 자원에 의존하는 경우가 있다.
        // 아래는 맞춤법 검사기 클래스는 Dictionary 클래스에 의존하는 형태이다.

        // 정적 유틸리티를 잘못 사용하여 구현한 경우
        // SpellChecker1
        String word1 = "hello";
        System.out.println(SpellChecker1.isValid(word1));

        // 싱글턴을 잘못 사용하여 구현한 경우
        // SpellChecker2
        String word2 = "hello";
        System.out.println(SpellChecker2.instance.isValid(word2));


        // 잘못된 이유
        // - 현재 두 클래스는 하나의 사전을 사용하고 있다. -> 의존성이 커서 변경이 어렵다.
        // - 실전에서는 여러가지 사전이 존재하고 사전 하나로 여러 쓰임새를 대응하는 것은 불가능하다.
        // -> 즉, 현재의 SpellChecer들은 여러개의 사전을 사용할 준비가 되어있지 않다.

        // 해결 방법
        // 1. 사전을 나타내는 멤버 변수에 final을 제외시켜 다른 사전으로 교체를 유동적으로 진행
        // -> 문제점 : 오류를 내기 쉬우며 멀티 테스크 환경에서는 사용이 불가능하다.

        // 정리 : 사용하는 자원에 따라 동작이 달라지는 클래스에서는 정적 유틸리티 클래스나 싱글턴 방식이 적합하지 않다.




        // 최종 해결책 : 인스턴스를 생성할 때, 생성자에 필요한 자원을 넘겨주는 방식
        // -> 의존 객체 주입 방법
        // 1. 사전 인스턴스 호출
        DicInterface korean = DicKorean.geDictionary();
        DicInterface english = DicEnglish.geDictionary();
        // 2. 맞춤법 검사기 생성
        SpellChecker3 s_korean = new SpellChecker3(korean);        
        SpellChecker3 s_english = new SpellChecker3(english);
        // 3. 검사
        System.out.println(s_korean.isValid("안녕"));
        System.out.println(s_english.isValid("hello"));

        // (+)
        // 인터페이스말고 Supplier를 통해 구현 가능하다.
        


    }
}
