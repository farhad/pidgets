package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import io.github.farhad.R;
import io.github.farhad.typeface.ParsiTypeface;
import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.parsi.ParsiUtils;
import io.github.farhad.utils.Utils;

public class ParsiTextView extends AppCompatTextView {

    private boolean shouldReplaceWithParsiDigits;
    private FontType fontType;

    public ParsiTextView(Context context) {
        super(context);

        init(context);
    }

    public ParsiTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs);
    }

    public ParsiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if(!isInEditMode()){

            if (shouldReplaceWithParsiDigits && Utils.containsDigits(text.toString())) {

                super.setText(ParsiUtils.replaceWithParsiDigits(text.toString()), type);
            } else {

                super.setText(text, type);
            }
        }

        else {

            super.setText(text,type);
        }

        requestLayout();
    }

    private void init(Context context) {

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiTextView);

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiTextView_useParsiDigits,true);
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiTextView_typefaceStyle, 0));

            typedArray.recycle();

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(fontType));
        }

        requestLayout();
    }

    private void init(Context context, AttributeSet attributeSet) {

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ParsiTextView, 0, 0);

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiTextView_useParsiDigits, true);
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiTextView_typefaceStyle, 0));

            typedArray.recycle();

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(fontType));
        }

        requestLayout();
    }


    public boolean shouldReplaceWithParsiDigits() {
        return shouldReplaceWithParsiDigits;
    }

    public void setShouldReplaceWithParsiDigits(boolean shouldReplaceWithParsiDigits) {
        this.shouldReplaceWithParsiDigits = shouldReplaceWithParsiDigits;

        requestLayout();
    }

    public FontType getFontType() {
        return fontType;
    }

    public void setFontType(FontType fontType) {
        this.fontType = fontType;

        requestLayout();
    }
}
