package Item6;

import java.util.regex.Pattern;

public class checkStringNew {

    private static final Pattern ROMAN 
    = Pattern.compile("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    public static boolean isRomanNumeral(String word){
        return ROMAN.matcher(word).matches();
    }
}
