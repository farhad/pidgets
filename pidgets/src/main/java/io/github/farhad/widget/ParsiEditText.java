package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import io.github.farhad.R;
import io.github.farhad.typeface.ParsiTypeface;
import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.Utils;
import io.github.farhad.utils.parsi.ParsiUtils;


public class ParsiEditText extends AppCompatEditText {

    private boolean useParsiDigits;
    private FontType typefaceStyle;
    private boolean hideBottomLine;

    public ParsiEditText(Context context) {
        super(context);

        init(context);
    }

    public ParsiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public ParsiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs , defStyleAttr);
    }

    private void init(Context context) {

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiEditText);

        initialize(context,typedArray);

        typedArray.recycle();
    }

    private void init(Context context, AttributeSet attributeSet) {

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ParsiEditText);

        initialize(context,typedArray);

        typedArray.recycle();

    }

    private void init(Context context,AttributeSet attributeSet, int defStyleAttr){

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.ParsiEditText,defStyleAttr,defStyleAttr) ;

        initialize(context,typedArray);

        typedArray.recycle();
    }

    private void initialize(Context context ,TypedArray typedArray){

        if(!isInEditMode())
        {
            useParsiDigits = typedArray.getBoolean(R.styleable.ParsiEditText_useParsiDigits, true);
            typefaceStyle  = FontType.getType(typedArray.getInt(R.styleable.ParsiEditText_typefaceStyle, 0));
            hideBottomLine = typedArray.getBoolean(R.styleable.ParsiEditText_hideBottomLine, false);

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(typefaceStyle));

            if (hideBottomLine) {
                getBackground().mutate().setColorFilter(ContextCompat.getColor(context, R.color.transparent), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {

        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        if(focused && getText() != null)
        {
            setSelection(getText().length());
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if (useParsiDigits && Utils.containsDigits(text.toString()))
            super.setText(ParsiUtils.replaceWithParsiDigits(text.toString()), type);

        else
            super.setText(text, type);
    }

    public boolean useParsiDigits() {
        return useParsiDigits;
    }

    public void setUseParsiDigits(boolean useParsiDigits) {
        this.useParsiDigits = useParsiDigits;

    }

    public FontType getTypefaceStyle() {
        return typefaceStyle;
    }

    public void setTypefaceStyle(FontType typefaceStyle) {
        this.typefaceStyle = typefaceStyle;

        setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(typefaceStyle));
    }

    public boolean hideBottomLine() {
        return hideBottomLine;
    }

    public void setHideBottomLine(boolean hideBottomLine) {
        this.hideBottomLine = hideBottomLine;

        if(hideBottomLine){

            getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.transparent), PorterDuff.Mode.SRC_ATOP);
        }
    }
}
