package Item2;

public class food {
    
    private final int calories; // 필수
    private final int fat; // 선택
    private final int servings; // 선택

    public food(int calories){
        this(calories, 0);
    }

    public food(int calories, int fat){
        this(calories, 0, 0);
    }
    
    public food(int calories, int fat, int servings){
        this.calories = calories;
        this.fat = fat;
        this.servings = servings;
    }
}
