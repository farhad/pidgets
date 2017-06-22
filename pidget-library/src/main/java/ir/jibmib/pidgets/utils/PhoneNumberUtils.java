package ir.jibmib.pidgets.utils;

import android.text.TextUtils;

/**
 * Created by farhad on 4/9/17.
 */

public class PhoneNumberUtils {

    /**
     * removes the '+' , '98' and '0098' from the cellphone number
     * @param msisdn
     * @return
     */
    public static String removeIntlCodeFromMsisdn(String msisdn)
    {
        if (!TextUtils.isEmpty(msisdn)) {

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

    /**
     *
     * @param msisdn
     * @return
     * @throws NumberFormatException
     */
    public static String formatToIranMsisdnPattern(String msisdn)
    {
        if(!TextUtils.isEmpty(msisdn) && TextUtils.isDigitsOnly(msisdn)){

            String output = removeIllegalCharacters(msisdn).trim();

            if(output.length() == 11) {
                return output ;
            }
            else if(!output.startsWith("0") && output.length() == 10) {
                return "0" + output ;
            }

            else {

                throw new NumberFormatException("msisdn length is below 10 characters !") ;
            }
        }

        return null ;
    }

    /**
     * returns the hashed msisdn of length 7 and more, for display
     * sample output for 09123456789 is 0912***6789
     * @param msisdn
     * @return
     */
    public static String getHashedMsisdnForDisplay(String msisdn)
    {
        if (!TextUtils.isEmpty(msisdn)
                && TextUtils.isDigitsOnly(msisdn)) {

            if(msisdn.length() > 7) {

                return msisdn.substring(0, 4) + "***" + msisdn.substring(7);
            }
        }

        return msisdn;
    }

    public static String removeIllegalCharacters(String source) {

        char[] strArray = source.toCharArray();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < strArray.length; i++)
        {
            if( (strArray[i] != ' ') && (strArray[i] != '\t') && (strArray[i] != '+') )
            {
                sb.append(strArray[i]);
            }
        }

        return sb.toString() ;
    }
}
