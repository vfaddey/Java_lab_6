package managers;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validator {
    public static boolean isStringWithIntegers(String inputString) {
        Pattern pattern = Pattern.compile("^\\s*\\d+\\s*(?:\\s+\\d+\\s*)*$");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }
}
