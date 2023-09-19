package Item12;

public class PhoneNumber {

    private int areaCode;
    private int prefix;
    private int lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum){
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
    }

    @Override
    public String toString(){
        // 포멧 설정해 구현
        // 자세한 설명을 적자. 아래는 간단한 예시이지만 좀 더 자세히 적어야 함.
        // 3자리-3자리-4자리 표현, 적다면 앞에 0붙임
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
    }
}
