package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import io.github.farhad.R;
import io.github.farhad.typeface.ParsiTypeface;
import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.parsi.ParsiUtils;
import io.github.farhad.utils.Utils;

/**
 * Created by haniyeh on 08/07/16.
 */
public class ParsiCheckBox extends AppCompatCheckBox {

    private boolean useParsiDigits;
    private FontType typefaceStyle;

    public ParsiCheckBox(Context context) {
        super(context);

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiCheckBox) ;

        init(context,typedArray);

        typedArray.recycle();
    }

    public ParsiCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ParsiCheckBox) ;

        init(context,typedArray);

        typedArray.recycle();
    }

    public ParsiCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ParsiCheckBox,defStyleAttr,defStyleAttr) ;

        init(context,typedArray);

        typedArray.recycle();
    }

    private void init(Context context,TypedArray typedArray){

        if(!isInEditMode()){

            useParsiDigits = typedArray.getBoolean(R.styleable.ParsiCheckBox_useParsiDigits, true);
            typefaceStyle = FontType.getType(typedArray.getInt(R.styleable.ParsiCheckBox_typefaceStyle, 0));

            typedArray.recycle();

            setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(typefaceStyle));
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type)
    {
        if(!isInEditMode()) {

            if (useParsiDigits && Utils.containsDigits(text.toString()))
                super.setText(ParsiUtils.replaceWithParsiDigits(text.toString()), type);
            else
                super.setText(text, type);
        }
    }

    public boolean isUseParsiDigits() {
        return useParsiDigits;
    }

    public void setUseParsiDigits(boolean useParsiDigits) {
        this.useParsiDigits = useParsiDigits;

        requestLayout();
    }

    public FontType getTypefaceStyle() {
        return typefaceStyle;
    }

    public void setTypefaceStyle(FontType typefaceStyle) {
        this.typefaceStyle = typefaceStyle;

        requestLayout();
    }
}
