package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import io.github.farhad.R;
import io.github.farhad.typeface.ParsiTypeface;
import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.parsi.ParsiUtils;
import io.github.farhad.utils.PidgetUtils;

public class ParsiTextView extends AppCompatTextView {

    private boolean useParsiDigits;
    private FontType typefaceStyle;

    public ParsiTextView(Context context) {
        super(context);

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiTextView) ;

        initialize(context,typedArray);

        typedArray.recycle();
    }

    public ParsiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ParsiTextView) ;

        initialize(context,typedArray);

        typedArray.recycle();
    }

    public ParsiTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ParsiTextView,defStyle,defStyle) ;

        initialize(context,typedArray);

        typedArray.recycle();
    }

    private void initialize(Context context,TypedArray typedArray) {

        if (!isInEditMode()) {

            useParsiDigits = typedArray.getBoolean(R.styleable.ParsiTextView_useParsiDigits, false);
            typefaceStyle = FontType.getType(typedArray.getInt(R.styleable.ParsiTextView_typefaceStyle, 0));

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(typefaceStyle));
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if (useParsiDigits && PidgetUtils.containsDigits(text.toString()))
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
    }
}
