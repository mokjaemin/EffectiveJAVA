package Item1;

public class car {
    private int carNum;
    private int carPrice;
    private String carKind;
    private int carMade;

    public static car example = car.getAll(10000, 10000, "test", 1997);

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
        instance.setCarNum(carNum);
        return instance;
    }

    public static car getAll(int carNum, int carPrice, String carKind, int carMade){
        car instance = new car();
        instance.setCarNum(carNum);
        instance.setCarPrice(carPrice);
        instance.setCarKind(carKind);
        instance.setCarMade(carMade);
        return instance;
    }

    public static car getExample(){
        return example;
    }

    public static car getTruck(){
        truck truck = new truck();
        truck.setCarKind("truck");
        return truck;
    }

    
}
