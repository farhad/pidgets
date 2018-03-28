package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import io.github.farhad.R;
import io.github.farhad.typeface.ParsiTypeface;
import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.parsi.ParsiUtils;
import io.github.farhad.utils.Utils;

/**
 * Created by farhad on 12/20/16.
 */

public class ParsiButton extends AppCompatButton {

    private boolean  shouldReplaceWithParsiDigits;
    private FontType fontType ;

    public ParsiButton(Context context) {
        super(context);

        init(context);
    }

    public ParsiButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs);
    }

    public ParsiButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if (!isInEditMode()) {

            if (shouldReplaceWithParsiDigits && Utils.containsDigits(text.toString())) {

                super.setText(ParsiUtils.replaceWithParsiDigits(text.toString()), type);

            } else {

                super.setText(text, type);
            }
        } else {
            super.setText(text, type);
        }

        requestLayout();
    }


    private void init(Context context){

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiButton) ;

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiButton_useParsiDigits,true) ;
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiButton_typefaceStyle,0)) ;

            typedArray.recycle();

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(fontType));
        }

        requestLayout();
    }

    private void init(Context context,AttributeSet attributeSet){

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.ParsiButton,0,0) ;

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiButton_useParsiDigits,true) ;
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiButton_typefaceStyle,0)) ;

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
