package Item5;

public class SpellChecker3 {

    private final DicInterface dic;

    public SpellChecker3(DicInterface dic){
        this.dic = dic;
    }

    public boolean isValid(String word){
        if(dic.isOkay(word)){
            return true;
        }
        else{
            return false;
        }
    }
}
