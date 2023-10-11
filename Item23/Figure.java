package Item23;

public class Figure {

    enum Shape {RECTANGLE, CIRCLE};

    // 태그 필드 - 현재의 모양을 나타냄
    final Shape shape;

    // Rectangle 일 때만 사용된다.
    double length;
    double width;

    // Circle 일때만 사용
    double radius;

    // 원 생성자
    Figure(double radius){
        shape = Shape.RECTANGLE;
        this.radius = radius;
    }

    // 사각형 생성자
    Figure(double length, double width){
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area(){
        switch (shape){
            case RECTANGLE:
                return width*length;
            case CIRCLE:
                return (radius*radius)*Math.PI;
            default:
                throw new AssertionError(shape);
        }
    }

}
