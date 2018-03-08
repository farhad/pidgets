package io.github.farhad.font;

import android.content.Context;
import android.graphics.Typeface;

public class FontAdapter {

    private static Typeface typefaceRegular;
    private static Typeface typefaceSemiBold;
    private static Typeface typefaceBold;
    private static Typeface typefaceItalic;

    private static FontAdapter instance;
    private static Context context;

    private FontAdapter(Context context) {

        this.context = context;
    }

    public static FontAdapter getInstance(Context context) {
        if (null == instance)
            instance = new FontAdapter(context);

        return instance;
    }

    public FontAdapter regular(String pathToRegularFont) {

        typefaceRegular = getTypeface(pathToRegularFont);

        return instance;
    }

    public FontAdapter semiBold(String pathToSemiBoldFont) {

        typefaceSemiBold = getTypeface(pathToSemiBoldFont);

        return instance;
    }

    public FontAdapter bold(String pathToBoldFont) {

        typefaceBold = getTypeface(pathToBoldFont);

        return instance;
    }

    public FontAdapter italic(String pathToItalicFont) {

        typefaceItalic = getTypeface(pathToItalicFont);

        return instance;
    }


    public Typeface getTypefaceRegular() {
        return typefaceRegular;
    }

    public Typeface getTypefaceSemiBold() {
        return typefaceSemiBold;
    }

    public Typeface getTypefaceBold() {
        return typefaceBold;
    }

    public Typeface getTypefaceItalic() {
        return typefaceItalic;
    }


    private Typeface getTypeface(String pathToTypeface) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), pathToTypeface);

        return typeface;
    }

    public Typeface getMatchingTypeface(FontType fontType) {

        switch (fontType) {

            case REGULAR:
                return typefaceRegular;

            case SEMI_BOLD: {

                if (null != typefaceSemiBold)
                    return typefaceSemiBold;

                return typefaceRegular;
            }

            case BOLD: {
                if (null != typefaceBold)
                    return typefaceBold;

                return typefaceRegular;
            }

            case ITALIC: {
                if (null != typefaceItalic)
                    return typefaceItalic;

                return typefaceRegular;
            }

            default:
                return typefaceRegular;
        }
    }
}
