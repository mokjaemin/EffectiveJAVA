package Item2;

public class main {
    public static void main(String[] args) {

        // 1. 점층적 생성자 패턴
        // 필수 매개변수 3개, 선택 매개변수 2개가 있다고 가정할 때,
        // - 필수 매개변수를 받는 생성자
        // - 필수 매개변수 + 선택 매개변수 1개를 받는 생성자
        // - 필수 매개변수 + 선택 매개변수 2개를 받는 생성자
        // 와 같이 점층적으로 매개변수를 늘려 생성자를 생성하는 패턴
        // 굉장히 비효율적이다.

        int calories = 0;
        int fat = 0;
        int servings = 0;
        food food1 = new food(calories);
        food food2 = new food(calories, fat);
        food food3 = new food(calories, fat, servings);



        // 2. 자바빈즈 패턴
        // 매개변수가 없는 생성자로 객체 생성 후, setter로 매개변수 값 설정
        // 문제점1 : 객체 하나를 만들기 위해 여러개의 메서드가 호출된다는 점
        // 문제점2 : 객체가 완전히 완성되기 전까지 일관성이 없다.
        // -> 클래스를 불변으로 만들 수 없으며(setter를 통해 계속 변경 가능하기 때문에)
        // -> 스레드 안정성을 얻기 위해서는 추가작업이 필요하다.
        food1 food4 = new food1();
        food4.setCalories(calories);
        food4.setFat(fat);
        food4.setServings(servings);


        // 3. 빌더 패턴
        // 직관적으로 이해가 가능
        // 불변으로 생성 가능 -> final 사용, getter, setter(x)
        // 일관성 -> 한번 만들면 변경 불가능
        food2 food5 = new food2.Builder(calories).fat(fat).servings(servings).build();


    }
}
