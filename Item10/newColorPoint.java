package Item10;

public class newColorPoint {
    private final Point point;
    private final Color color;

    public newColorPoint(Point point, Color color){
        this.point = point;
        this.color = color;
    }

    // test

    public Point asPoint(){
        return this.point;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof newColorPoint)){
            return false;
        }
        newColorPoint p = (newColorPoint) o;
        return p.point.equals(this.point) && p.color.equals(this.color);
    }
}
