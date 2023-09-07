package Item5;

import java.util.HashMap;
import java.util.Set;

public class DicEnglish implements DicInterface{

    private static DicEnglish instance = new DicEnglish();
    private static HashMap<String, String> english = new HashMap<>();

    private DicEnglish(){

    }

    public static DicEnglish geDictionary(){
        instance.init();
        return instance;
    }
    
    private void init(){
        if(english.size() == 0){
            english.put("hello", "안녕");
            english.put("bye", "잘가");
        }
    }

    @Override
    public boolean isOkay(String word){
        Set<String> keys = english.keySet();
        if(keys.contains(word)){
            return true;
        }
        else{
            return false;
        }
    }
}
