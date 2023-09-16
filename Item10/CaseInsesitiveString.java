package Item10;

import java.util.Objects;

public class CaseInsesitiveString {
    private final String s;

    public CaseInsesitiveString(String s){
        this.s = Objects.requireNonNull(s);
    }

//    @Override
//    public boolean equals(Object o){
//        if(o instanceof CaseInsesitiveString){
//            return s.equalsIgnoreCase(((CaseInsesitiveString) o).s);
//        }
//        if(o instanceof String){
//            return s.equalsIgnoreCase(((String) o));
//        }
//        return false;
//    }

    // 수정
    @Override
    public boolean equals(Object o){
        return o instanceof CaseInsesitiveString && ((CaseInsesitiveString) o).s.equalsIgnoreCase(s);
    }

}
