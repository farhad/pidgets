package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import io.github.farhad.R;
import io.github.farhad.font.FontAdapter;
import io.github.farhad.font.FontType;


public class ParsiEditText extends AppCompatEditText {

    private boolean shouldReplaceWithParsiDigits;
    private FontType fontType;
    private boolean shouldHideBottomLine;
    private OnTextEventListener eventListener ;

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

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ParsiEditText, 0, 0);

        initialize(context,typedArray);

        typedArray.recycle();

    }

    private void init(Context context,AttributeSet attributeSet, int defStyleAttr){

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.ParsiEditText,defStyleAttr,defStyleAttr) ;

        initialize(context,typedArray);

        typedArray.recycle();
    }

    private void initialize(Context context ,TypedArray typedArray){

        shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiEditText_replaceWithPersianDigits, true);
        fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiEditText_fontAdapterType, 0));
        shouldHideBottomLine = typedArray.getBoolean(R.styleable.ParsiEditText_hideBottomLine, true);

        setTypeface(FontAdapter.getInstance(context).getMatchingTypeface(fontType));

        if (shouldHideBottomLine) {
            getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.transparent), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public boolean shouldReplaceWithParsiDigits() {
        return shouldReplaceWithParsiDigits;
    }

    public void setShouldReplaceWithParsiDigits(boolean shouldReplaceWithParsiDigits) {
        this.shouldReplaceWithParsiDigits = shouldReplaceWithParsiDigits;

    }

    public FontType getFontType() {
        return fontType;
    }

    public void setFontType(FontType fontType) {
        this.fontType = fontType;
    }

    public boolean shouldHideBottomLine() {
        return shouldHideBottomLine;
    }

    public void setShouldHideBottomLine(boolean shouldHideBottomLine) {
        this.shouldHideBottomLine = shouldHideBottomLine;

        if(shouldHideBottomLine){

            getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.transparent), PorterDuff.Mode.SRC_ATOP);
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
    public boolean onTextContextMenuItem(int id) {

        boolean consumed = super.onTextContextMenuItem(id);

        switch (id){
            case android.R.id.cut:
            {
                if(eventListener != null)
                    eventListener.onCut();
                break;
            }
            case android.R.id.copy:
            {
                if(eventListener != null)
                    eventListener.onCopy();

                break;
            }

            case android.R.id.paste:
            {
                if(eventListener != null)
                    eventListener.onPaste();
            }
        }
        return consumed;
    }

    public OnTextEventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(OnTextEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public interface OnTextEventListener
    {
        void onCut() ;

        void onPaste() ;

        void onCopy() ;
    }
}
