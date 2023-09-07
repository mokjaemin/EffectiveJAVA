package Item6;

import java.util.regex.Pattern;

public class checkString {
    
    public static boolean isRomanNumeral(String word){
        return word.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }
}
