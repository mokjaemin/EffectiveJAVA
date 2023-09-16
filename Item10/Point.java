package Item10;

import java.util.Set;

public class Point {

    private final int x;
    private final int y;
    private static final Set<Point> unitCircle
            = Set.of(new Point(1, 0), new Point(-1, 0),
            new Point(0, -1), new Point(0, 1));

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Point)){
            return false;
        }
        Point p = (Point) o;
        return p.x == this.x && p.y == this.y;
    }

    // 리스코프 치환 원칙 위배
    public boolean equals11(Object o){
        if(o == null | o.getClass() != getClass()){
            return false;
        }
        Point p = (Point) o;
        return p.x == this.x && p.y == this.y;
    }

    // 주어진 점이 단위 원안에 존재하는지 확인해주는 메서드
    public static boolean onUnitCircle(Point p){
        return unitCircle.contains(p);
    }

}
