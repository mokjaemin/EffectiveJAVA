package Item14;

import java.util.Comparator;

public class Info implements Comparable<Info>{

    private BasicInfo basicInfo;
    private int age;

    // 비교자 활용
    private static final Comparator<Info> COMPARATOR = Comparator
            .comparing((Info a) -> a.basicInfo)
            .thenComparing(a -> a.age);

    public Info(BasicInfo basicInfo, int age){
        this.basicInfo = basicInfo;
        this.age = age;
    }

    public int compareTo(Info info){
//        int result = this.basicInfo.compareTo(info.basicInfo);
//        if(result == 0){
//            return Integer.compare(this.age, info.age);
//        }
//        return result;

        // 비교자 사용
        return COMPARATOR.compare(this, info);
    }
}
