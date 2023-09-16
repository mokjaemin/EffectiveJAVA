package Item10;

public class ColorPoint extends Point{

    private final Color color;

    public ColorPoint(int x, int y, Color color){
        super(x, y);
        this.color = color;
    }


    // 1. ColorPoint 객체끼리만 비교
    // ColorPoint와 Point를 비교할때 대칭성 위배
    // Point의 equals는 true를 ColorPoint의 equals는 false를 반환
//    @Override
//    public boolean equals(Object o){
//        if(!(o instanceof ColorPoint)){
//            return false;
//        }
//        return super.equals(o) && ((ColorPoint) o).color.equals(this.color);
//    }


    // 2. Color를 빼고 비교
    // ColorPoint와 Point를 비교할때 추이성 위배
    @Override
    public boolean equals(Object o){
        // 아무곳에도 속하지 않는 경우 : false 반환
        if(!(o instanceof Point)){
            return false;
        }
        // Point지만 ColorPoint는 아닌 경우 : Color 빼고 비교
        if(!(o instanceof ColorPoint)){
            return o.equals(this);
        }
        // ColorPoint인 경우 : 색상까지 전체 비교
        return super.equals(o) && ((ColorPoint) o).color.equals(this.color);
    }


}
