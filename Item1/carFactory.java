package Item1;

public class carFactory {
    
    // 정적 팩토리 메서드 패턴
    // 1. 기본
    public static car getByName(String kind){
        if(kind.equals("truck")){
            truck truck = new truck();
            truck.setCarKind(kind);
            return truck;
        }
        else if(kind.equals("sportscar")){
            sportscar sportscar = new sportscar();
            sportscar.setCarKind(kind);
            return sportscar;
        }
        else{
            return null;
        }
    }


    // 2. 클래스명으로 받기
    public static car getByClass(String kind){
        try {
            Class<?> clz = Class.forName(kind);
            try {
                return (car) clz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
