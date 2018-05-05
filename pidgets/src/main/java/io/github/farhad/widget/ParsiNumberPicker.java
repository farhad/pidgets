package io.github.farhad.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import io.github.farhad.R;
import io.github.farhad.typeface.FontType;
import io.github.farhad.typeface.ParsiTypeface;
import java.lang.reflect.Field;

/**
 * Created by farhad on 9/16/16.
 */
public class ParsiNumberPicker extends NumberPicker {

  private FontType typefaceStyle;
  private float textSize;
  private String textColor;

  public ParsiNumberPicker(Context context) {
    super(context);

    TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiNumberPicker);

    initialize(context, typedArray);

    typedArray.recycle();
  }

  public ParsiNumberPicker(Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ParsiNumberPicker);

    initialize(context, typedArray);

    typedArray.recycle();
  }

  public ParsiNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray typedArray = context
        .obtainStyledAttributes(attrs, R.styleable.ParsiNumberPicker, defStyleAttr, defStyleAttr);

    initialize(context, typedArray);

    typedArray.recycle();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public ParsiNumberPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  private void initialize(Context context, TypedArray typedArray) {
    if (!isInEditMode()) {
      textSize = typedArray.getDimensionPixelSize(R.styleable.ParsiNumberPicker_textSize, 14);
      typefaceStyle = FontType
          .getType(typedArray.getInt(R.styleable.ParsiEditText_typefaceStyle, 0));
      textColor = typedArray.getString(R.styleable.ParsiNumberPicker_textColor);

      removeSelectionDivider();
    }
  }

  private void removeSelectionDivider() {
    try {
      Class<?> numberPickerClass = NumberPicker.class;
      Field selectionDivider = numberPickerClass.getDeclaredField("mSelectionDivider");

      selectionDivider.setAccessible(true);
      ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
      selectionDivider.set(this, colorDrawable);
    } catch (Exception exc) {
      Log.d("pidgets", "removeDivider: " + exc.getMessage());
    }
  }

  @Override
  public void addView(View child) {
    super.addView(child);

    setTypeface(child);
  }

  @Override
  public void addView(View child, int index) {
    super.addView(child, index);

    setTypeface(child);
  }

  @Override
  public void addView(View child, int width, int height) {
    super.addView(child, width, height);

    setTypeface(child);
  }

  @Override
  public void addView(View child, ViewGroup.LayoutParams params) {
    super.addView(child, params);

    setTypeface(child);
  }

  @Override
  public void addView(View child, int index, ViewGroup.LayoutParams params) {
    super.addView(child, index, params);

    setTypeface(child);
  }

  private void setTypeface(View view) {
    if (view instanceof TextView) {
      ((TextView) view).setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(typefaceStyle));
      ((TextView) view).setTextSize(textSize);

      ((TextView) view).setTextColor(textColor == null ? Color
          .parseColor("#000000") : Color.parseColor(textColor));
    }

    requestLayout();
  }
}
