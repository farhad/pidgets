package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import io.github.farhad.R;
import io.github.farhad.typeface.ParsiTypeface;
import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.ParsiUtils;
import io.github.farhad.utils.Utils;

/**
 * Created by haniyeh on 08/07/16.
 */
public class ParsiCheckBox extends AppCompatCheckBox {

    private boolean shouldReplaceWithParsiDigits;
    private FontType fontType;

    public ParsiCheckBox(Context context) {
        super(context);

        init(context);
    }

    public ParsiCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs);
    }

    public ParsiCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type)
    {
        if(!isInEditMode()){

            if (shouldReplaceWithParsiDigits && Utils.containsDigits(text.toString())) {

                super.setText(ParsiUtils.replaceWithParsiDigits(text.toString()), type);
            } else {

                super.setText(text, type);

                requestLayout();
            }
        }

        else {

            super.setText(text,type);

            requestLayout();
        }
    }

    private void init(Context context){

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiCheckBox);

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiCheckBox_useParsiDigits, true);
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiCheckBox_typefaceStyle, 0));

            typedArray.recycle();

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(fontType));
        }

        requestLayout();
    }

    private void init(Context context,AttributeSet attributeSet){

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ParsiTextView, 0, 0);

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiCheckBox_useParsiDigits, true);
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiCheckBox_typefaceStyle, 0));

            typedArray.recycle();

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(fontType));
        }

        requestLayout();
    }

    public boolean isShouldReplaceWithParsiDigits() {
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
