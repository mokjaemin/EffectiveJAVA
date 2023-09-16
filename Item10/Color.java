package Item10;

public class Color {
    private final int r;
    private final int g;
    private final int b;

    public Color(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Color)){
            return false;
        }
        Color p = (Color) o;
        return this.r == p.r && this.g == p.g && this.b == p.b;
    }
}
