package Item2;

public class food1 {
    
    // 자바 빈즈 패턴의 경우 final 사용이 불가능
    private int calories;
    private int fat;
    private int servings;

    public food1(){

    }

    public int getCalories() {
        return this.calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFat() {
        return this.fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getServings() {
        return this.servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }


}
