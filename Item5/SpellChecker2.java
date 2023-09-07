package Item5;

public class SpellChecker2 {

    private final Dictionary dic = Dictionary.geDictionary();

    private SpellChecker2(){

    }

    public static SpellChecker2 instance = new SpellChecker2();

    public boolean isValid(String word){
        if(dic.isOkay(word)){
            return true;
        }
        else{
            return false;
        }
    }

}
