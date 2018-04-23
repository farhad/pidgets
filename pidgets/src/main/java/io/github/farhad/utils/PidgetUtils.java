package io.github.farhad.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by farhad on 12/28/16.
 */

public class PidgetUtils {

    public static boolean containsDigits(String text) {

        Pattern pattern = Pattern.compile("([0-9])");
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }

    public static String formatToIranCellPhoneNumber(String phoneNumber)
    {
        return fixNumberFormat(phoneNumber) ;
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

    public static void closeKeyboard(Context context,View view){

        try
        {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    public static String formatAmount(String value, String separator) {
        String result = "";
        int len = value.length();
        int loop = (len / 3);

        int start = 0;
        int end = len - (loop * 3);

        result = value.substring(start, end);

        for (int i = 0; i < loop; i++) {
            start = end;
            end += 3;
            if (result.equals(""))
                result = value.substring(start, end);
            else
                result = result + separator + value.substring(start, end);
        }

        return result;
    }

    public static String formatMoneyIR(String amount)
    {
        return formatAmount(amount,",") ;
    }

    public static String formatAmountWithCurrencyTagIR(String amount) {

        return new StringBuilder().append(formatMoneyIR(amount))
                .append(" ")
                .append("ریال").toString() ;
    }
}
