package Item1;

public class car {
    private int carNum;
    private int carPrice;
    private String carKind;
    private int carMade;

    public int getCarNum() {
        return this.carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public int getCarPrice() {
        return this.carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarKind() {
        return this.carKind;
    }

    public void setCarKind(String carKind) {
        this.carKind = carKind;
    }

    public int getCarMade() {
        return this.carMade;
    }

    public void setCarMade(int carMade) {
        this.carMade = carMade;
    }

    

    public static car numOnly(int carNum){
        car instance = new car();
        instance.carNum = carNum;
        return instance;
    }
    
}
