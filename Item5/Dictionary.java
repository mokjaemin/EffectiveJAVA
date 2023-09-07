package Item5;

import java.util.*;

public class Dictionary {
    
    private static Dictionary instance = new Dictionary();
    private static HashMap<String, String> korean = new HashMap<>();

    private Dictionary(){

    }

    public static Dictionary geDictionary(){
        instance.init();
        return instance;
    }
    
    private void init(){
        if(korean.size() == 0){
            korean.put("hello", "안녕");
            korean.put("bye", "잘가");
        }
    }

    public static boolean isOkay(String word){
        Set<String> keys = korean.keySet();
        if(keys.contains(word)){
            return true;
        }
        else{
            return false;
        }
    } 
}
