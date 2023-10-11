package Item23;

public class newRectangle extends newFigure{

    final private double length;

    final private double width;

    public newRectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    @Override
    public double area(){
        return length*width;
    }
}
