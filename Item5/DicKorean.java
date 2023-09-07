package Item5;

import java.util.HashMap;
import java.util.Set;

public class DicKorean implements DicInterface{
    
    private static DicKorean instance = new DicKorean();
    private static HashMap<String, String> korean = new HashMap<>();

    private DicKorean(){

    }

    public static DicKorean geDictionary(){
        instance.init();
        return instance;
    }
    
    private void init(){
        if(korean.size() == 0){
            korean.put("안녕", "hello");
            korean.put("잘가", "bye");
        }
    }

    @Override
    public boolean isOkay(String word){
        Set<String> keys = korean.keySet();
        if(keys.contains(word)){
            return true;
        }
        else{
            return false;
        }
    }
}
