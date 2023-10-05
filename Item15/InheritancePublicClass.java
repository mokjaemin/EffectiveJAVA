package Item15;

import Item15Test.PublicClass;

public class InheritancePublicClass extends PublicClass {

    public InheritancePublicClass(int value){
        super(value);
    }

    public int getValue(){
        return super.value;
    }

    @Override
    public void sing(){
        System.out.println("low sing");
    }
}
