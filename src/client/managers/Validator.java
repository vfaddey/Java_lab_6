package client.managers;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Class that consist of some static methods that check users' inputs
 */
public class Validator {
    public static boolean isStringWithIntegers(String inputString) {
        Pattern pattern = Pattern.compile("^\\s*\\d+\\s*(?:\\s+\\d+\\s*)*$");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }

    public static boolean isStringWithNumbers(String str) {
        String[] numbers = str.split(" ");
        for (String number : numbers) {
            if (!number.matches("-?\\d+(\\.\\d+)?")) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmptyArray(Object[] arr) {
        return arr.length == 0;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isValidName(String str) {
        return !isNull(str) && !str.isEmpty() && str.matches("^[^\\s].*");
    }

    public static boolean areCorrectLocationParams(String x, String y, String z) {
        return isCorrectNumber(x, Double.class) && isCorrectNumber(y, Double.class) && isCorrectNumber(z, Long.class);
    }

    public static <T extends Number> boolean isCorrectNumber(String string, Class<T> type) {
        try {
            T number = type.getConstructor(String.class).newInstance(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isSubstring(String substring, String string) {
        return string.contains(substring);
    }


    public static boolean isArrayConsistsOfOnlyNull(Object[] array) {
        for (Object element : array) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }
}
