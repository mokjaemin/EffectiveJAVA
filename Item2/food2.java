package Item2;


// 유효성 검사는 빌더의 생성자와 메서드에서 처리
public class food2 {

    private final int calories;
    private final int fat;
    private final int servings;

    public static class Builder{

         // 필수 매개변수
        private final int calories;

        // 선택 매개변수 - 기본값으로 초기화
        private int fat = 0;
        private int servings = 0;

        // 필수 매개변수를 받는 생성자
        public Builder(int calories){
            this.calories = calories;
        }

        // 빌더의 세터 메서드들은 빌더 자신을 반환하기 때문에 연쇄적으로 호출 가능
        public Builder fat(int val){
            fat = val;
            return this;
        }

        public Builder servings(int val){
            servings = val;
            return this;
        }

        public food2 build(){
            return new food2(this);
        }
    }

    private food2(Builder builder){
        calories = builder.calories;
        fat = builder.fat;
        servings = builder.servings;
    }
}
