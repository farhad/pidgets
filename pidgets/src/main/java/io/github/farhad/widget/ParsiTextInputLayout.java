package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import io.github.farhad.R;
import io.github.farhad.typeface.FontType;
import io.github.farhad.typeface.ParsiTypeface;
import io.github.farhad.utils.Utils;
import io.github.farhad.utils.parsi.ParsiUtils;

public class ParsiTextInputLayout extends TextInputLayout {

    private boolean useParsiDigits ;
    private FontType typefaceStyle ;

    public ParsiTextInputLayout(Context context) {
        super(context);

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiTextInputLayout) ;

        initialize(context, typedArray);

        typedArray.recycle();
    }

    public ParsiTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ParsiTextInputLayout) ;

        initialize(context, typedArray);

        typedArray.recycle();
    }

    public ParsiTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ParsiTextInputLayout,defStyleAttr,defStyleAttr) ;

        initialize(context, typedArray);

        typedArray.recycle();
    }

    private void initialize(Context context, TypedArray typedArray)
    {
        if(!isInEditMode())
        {
            useParsiDigits = typedArray.getBoolean(R.styleable.ParsiEditText_useParsiDigits, true);
            typefaceStyle  = FontType.getType(typedArray.getInt(R.styleable.ParsiEditText_typefaceStyle, 0));

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(typefaceStyle));
        }
    }

    @Override
    public void setTooltipText(@Nullable CharSequence tooltipText) {
        super.setTooltipText(tooltipText);
    }
}
