package Item17;

public class Complex {

    public static final Complex zero = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);

    private final double re;
    private final double im;

    public Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    public Complex plus(Complex other){
        return new Complex(this.re+other.re, this.im+other.im);
    }

    public Complex minus(Complex other){
        return new Complex(this.re-other.re, this.im-other.im);
    }

    public static Complex valueOf(double re, double im){
        return new Complex(re, im);
    }
}
