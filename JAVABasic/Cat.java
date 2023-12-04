package JAVABasic;

public class Cat extends Animal{

    @Override
    String getType() {
        return "Cat";
    }

    @Override
    public String canUTalk() {
        return "Somtimes";
    }
}
