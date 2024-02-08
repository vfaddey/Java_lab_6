package managers;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validator {
    public static boolean isStringWithIntegers(String inputString) {
        Pattern pattern = Pattern.compile("^\\s*\\d+\\s*(?:\\s+\\d+\\s*)*$");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }

    public static boolean areCorrectCoordinatesParams(String inputString) {

        return false;
    }

    public static boolean isSubstring(String substring, String string) {
        return string.contains(substring);
    }

    public static boolean isCorrectLong(String string) {
        try {
            long number = Long.parseLong(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
