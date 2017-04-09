package ir.protelco.pidget.utils;

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

public class Utils {


    public static String getFormattedMoney(String value, String separator) {
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

    public static boolean containsDigits(String text) {

        Pattern pattern = Pattern.compile("([0-9])");
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }

    public static void closeKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void openLinkInBrower(Activity activity, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
    }

    public static void showAppSettingsPage(final Activity context) {
        final Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        context.startActivity(intent);
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {

                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isServiceRunning(Context context, Class<?> myService) {
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if (myService.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void setEditTextCursorColor(EditText view, @ColorInt int color) {
        try {
            // Get the cursor resource id
            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            int drawableResId = field.getInt(view);

            // Get the editor
            field = TextView.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            Object editor = field.get(view);

            // Get the drawable and set a color filter
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), drawableResId);
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            Drawable[] drawables = {drawable, drawable};

            // Set the drawables
            field = editor.getClass().getDeclaredField("mCursorDrawable");
            field.setAccessible(true);
            field.set(editor, drawables);
        } catch (Exception ignored) {

            ignored.printStackTrace();
        }
    }
}
