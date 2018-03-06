package io.github.farhad.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import io.github.farhad.font.FontAdapter;

/**
 * Created by farhad on 9/16/16.
 */
public class ParsiNumberPicker extends NumberPicker {
    public ParsiNumberPicker(Context context) {
        super(context);
    }

    public ParsiNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParsiNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ParsiNumberPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

    private void setTypeface(View view)
    {
        if(view instanceof TextView)
        {
            ((TextView)view).setTypeface(FontAdapter.getInstance(getContext()).getTypefaceRegular());
            ((TextView)view).setTextSize(18);
        }

        requestLayout();
    }
}
