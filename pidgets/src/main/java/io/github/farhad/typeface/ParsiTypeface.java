package io.github.farhad.typeface;

import android.app.Application;
import android.graphics.Typeface;
import android.text.TextUtils;

public class ParsiTypeface {

  private static ParsiTypeface instance;

  private Typeface typefaceRegular;
  private Typeface typefaceSemiBold;
  private Typeface typefaceBold;
  private Typeface typefaceItalic;

  private String regularUri;
  private String semiBoldUri;
  private String boldUri;
  private String italicUri;

  private ParsiTypeface() {
  }

  public static ParsiTypeface getInstance() {

    if (instance == null) {
      instance = new ParsiTypeface();
    }

    return instance;
  }

  public ParsiTypeface regular(String pathToRegularFont) {

    regularUri = pathToRegularFont;
    return instance;
  }

  public ParsiTypeface semiBold(String pathToSemiBoldFont) {

    semiBoldUri = pathToSemiBoldFont;
    return instance;
  }

  public ParsiTypeface bold(String pathToBoldFont) {

    boldUri = pathToBoldFont;
    return instance;
  }

  public ParsiTypeface italic(String pathToItalicFont) {

    italicUri = pathToItalicFont;
    return instance;
  }

  public ParsiTypeface init(Application application) {
    if (!TextUtils.isEmpty(regularUri)) {
      typefaceRegular = Typeface.createFromAsset(application.getAssets(), regularUri);
    }

    if (!TextUtils.isEmpty(semiBoldUri)) {
      typefaceSemiBold = Typeface.createFromAsset(application.getAssets(), semiBoldUri);
    }

    if (!TextUtils.isEmpty(boldUri)) {
      typefaceBold = Typeface.createFromAsset(application.getAssets(), boldUri);
    }

    if (!TextUtils.isEmpty(italicUri)) {
      typefaceItalic = Typeface.createFromAsset(application.getAssets(), italicUri);
    }

    return instance;
  }


  public Typeface getMatchingTypeface(FontType fontType) {

    switch (fontType) {

      case REGULAR:
        return typefaceRegular;

      case SEMI_BOLD: {

        if (null != typefaceSemiBold) {
          return typefaceSemiBold;
        }

        return typefaceRegular;
      }

      case BOLD: {
        if (null != typefaceBold) {
          return typefaceBold;
        }

        return typefaceRegular;
      }

      case ITALIC: {
        if (null != typefaceItalic) {
          return typefaceItalic;
        }

        return typefaceRegular;
      }

      default:
        return typefaceRegular;
    }
  }

  public Typeface getRegular() {
    return typefaceRegular;
  }

  public Typeface getSemiBold() {
    return typefaceSemiBold;
  }

  public Typeface getBold() {
    return typefaceBold;
  }

  public Typeface getItalic() {
    return typefaceItalic;
  }
}
