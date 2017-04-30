package ir.jibmib.pidgets.parsi;

/**
 * Created by Farhad on 11/9/2015.
 */
public class ParsiUtils {

    private static String[] persianNumbers = new String[]{ "۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹" };
    private static String[] englishNumbers = new String[]{"0","1","2","3","4","5","6","7","8","9"} ;


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


    public static String replaceWithEnglishDigits(String digits){

        if (digits.length() == 0)
            return "";
        String out = "";
        int length = digits.length();
        for (int i = 0; i < length; i++) {
            char c = digits.charAt(i);
            if ('۰' <= c && c <= '۹') {
                int number = Integer.parseInt(String.valueOf(c));
                out += englishNumbers[number];
            } else if (c == '،') {
                out += '٫';
            } else {
                out += c;
            }

        }
        return out;

    }
}
