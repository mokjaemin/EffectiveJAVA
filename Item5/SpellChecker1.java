package Item5;

public class SpellChecker1 {
    
    private static final Dictionary dic = Dictionary.geDictionary();

    private SpellChecker1(){

    }

    public static boolean isValid(String word){
        if(dic.isOkay(word)){
            return true;
        }
        else{
            return false;
        }
    }
}
