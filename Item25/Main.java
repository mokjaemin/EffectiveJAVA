package Item25;


// Item 25 : 톱 클래스 파일은 한 파일에 하나만 담아라
public class Main {
    public static void main(String[] args){

        // 소스파일 하나에 여러 톱 클래스를 담는 것은 위험을 감수하는 일이다.
        // - 한 클래스를 여러가지로 정의할 수 있으며
        // 그 중 어느것을 사용하는지는 어느 소스 파일을 컴파일하냐에 따라 달라지기 때문이다.

        // 예시
        System.out.println(Utensil.name + Dessert.name);

        // 우연히, 같은 내용이지만 이름이 다른 파일 두개를 만든 경우
        // 운이 좋으면 컴파일 오류가 발생한다.
        // 예를들어, javac Main.java Dessert.java로 명령했다면
        // 컴파일러는 Main.java를 컴파일하고 Utensil.name이 먼저 나오면 Utensil.java를 먼저 찾고
        // 그안에서 Dessert도 찾을 것이다.
        // 하지만 javac Dessert.java Main.java로 명령시 이상한 결과가 나온다.

        System.out.println(Dessert.name + Utensil.name);

        // 해결책
        // 톱클래스를 분리
        // 굳이 한 파일에 담고 싶다면 Item24의 정적 멤버 클래스를 사용하자


    }
}
