package Item4;

public interface Job {

    public void work();

    public static Job makePolice(){
        return new Police();
    }
}
