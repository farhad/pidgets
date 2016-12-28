package ir.protelco.pidget.parsi;

/**
 * Created by Farhad on 11/9/2015.
 */
public class ParsiUtils {

    private static String[] persianNumbers = new String[]{ "۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹" };


    public static String replaceWithParsiDigits(String digits) {
        if (digits.length() == 0)
            return "";
        String out = "";
        int length = digits.length();
        for (int i = 0; i < length; i++) {
            char c = digits.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(String.valueOf(c));
                out += persianNumbers[number];
            } else if (c == '٫') {
                out += '،';
            } else {
                out += c;
            }

        }
        return out;
    }
}
