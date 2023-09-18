package Item11;

public class PhoneNumber {

    private int areaCode;
    private int prefix;
    private int lineNum;

    private int result;

    public PhoneNumber(int areaCode, int prefix, int lineNum){
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof PhoneNumber)){
            return false;
        }
        PhoneNumber new_o = (PhoneNumber) o;
        return new_o.areaCode == this.areaCode && new_o.prefix == this.prefix
                && new_o.lineNum == this.lineNum;
    }

//    @Override
//    public int hashCode(){
//        int result = Integer.hashCode(this.areaCode);
//        result += 31*result + Integer.hashCode(this.prefix);
//        result += 31*result + Integer.hashCode(this.lineNum);
//        return result;
//    }


    // 지연 초기화로 캐싱 구현
    @Override
    public int hashCode(){
        if(result == 0){
            result = Integer.hashCode(this.areaCode);
            result += 31*result + Integer.hashCode(this.prefix);
            result += 31*result + Integer.hashCode(this.lineNum);
        }
        return result;
    }
}
