package Item3;

public enum enumPerson implements ISingleTon{
    
    instanceName;

    @Override
    public boolean send(String message){
        System.out.println(message);
        return true;
    }
    
}
