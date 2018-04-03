package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import io.github.farhad.R;
import io.github.farhad.typeface.ParsiTypeface;
import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.parsi.ParsiUtils;
import io.github.farhad.utils.PidgetUtils;

/**
 * Created by farhad on 12/20/16.
 */

public class ParsiButton extends AppCompatButton {

    private boolean useParsiDigits;
    private FontType typefaceStyle;

    public ParsiButton(Context context) {
        super(context);

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiButton) ;

        init(context,typedArray);

        typedArray.recycle();
    }

    public ParsiButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ParsiButton) ;

        init(context,typedArray);

        typedArray.recycle();
    }

    public ParsiButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ParsiButton,defStyleAttr,defStyleAttr) ;

        init(context,typedArray);

        typedArray.recycle();
    }

    private void init(Context context,TypedArray typedArray){

        if(!isInEditMode()) {

            useParsiDigits = typedArray.getBoolean(R.styleable.ParsiButton_useParsiDigits,true) ;
            typefaceStyle = FontType.getType(typedArray.getInt(R.styleable.ParsiButton_typefaceStyle,0)) ;

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

        setTypeface(ParsiTypeface.getInstance().getMatchingTypeface(typefaceStyle));
    }
}
