package Item23;

public class newCircle extends newFigure{

    final private double radius;

    public newCircle(double radius){
        this.radius = radius;
    }

    @Override
    public double area(){
        return (radius*radius)*Math.PI;
    }
}
