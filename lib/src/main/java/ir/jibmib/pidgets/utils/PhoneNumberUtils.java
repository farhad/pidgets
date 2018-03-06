package ir.jibmib.pidgets.utils;

import android.text.TextUtils;

/**
 * Created by farhad on 4/9/17.
 */

public class PhoneNumberUtils {

    public static String formatToIranMsisdnPattern(String msisdn)
    {
        return fixNumberFormat(msisdn) ;
    }

    private static String fixNumberFormat(String input)
    {
        String msisdn = removeInvalidCharacters(input) ;

        if(!TextUtils.isEmpty(msisdn)
                && TextUtils.isDigitsOnly(msisdn)
                && msisdn.length() >= 10)
        {
            msisdn = removeIntlCodeFromMsisdn(msisdn) ;

            if(msisdn.startsWith("9") && msisdn.length() == 10)
                return "0" + msisdn ;

            else if(msisdn.startsWith("09") && msisdn.length() == 11)
                return msisdn ;

            else if(msisdn.startsWith("98") && msisdn.length() == 12)
                return "0" + msisdn.substring(2) ;

            else if(msisdn.startsWith("0098") && msisdn.length() == 14)
                return "0" + msisdn.substring(4) ;

            else
                return null ;
        }

        return null ;
    }

    public static String removeIntlCodeFromMsisdn(String msisdn)
    {
        if (!TextUtils.isEmpty(msisdn) && TextUtils.isDigitsOnly(msisdn)) {

            if (msisdn.startsWith("+")) {
                return msisdn.substring(1);
            }
            if (msisdn.startsWith("98")) {
                return msisdn.substring(2);

            } else if (msisdn.startsWith("0098")) {
                return msisdn.substring(4);

            } else {
                return msisdn;
            }
        }

        return msisdn;
    }

    public static String removeInvalidCharacters(String source) {

        char[] strArray = source.toCharArray();

        StringBuffer sb = new StringBuffer();

        for (char item : strArray)
        {
            if (TextUtils.isDigitsOnly(String.valueOf(item)))
            {
                sb.append(item);
            }
        }

        return sb.toString() ;
    }
}
