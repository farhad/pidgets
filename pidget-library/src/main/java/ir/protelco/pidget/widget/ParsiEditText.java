package ir.protelco.pidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import ir.protelco.pidget.R;
import ir.protelco.pidget.font.FontAdapter;
import ir.protelco.pidget.font.FontType;
import ir.protelco.pidget.parsi.ParsiUtils;
import ir.protelco.pidget.utils.Utils;


public class ParsiEditText extends AppCompatEditText {

    private boolean shouldReplaceWithParsiDigits;
    private FontType fontType;
    private boolean shouldHideBottomLine;

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

        init(context, attrs);
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


    private void init(Context context) {

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiTextView);

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiEditText_replaceWithPersianDigits, true);
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiEditText_fontAdapterType, 0));
            shouldHideBottomLine = typedArray.getBoolean(R.styleable.ParsiEditText_hideBottomLine, true);

            typedArray.recycle();

            setTypeface(FontAdapter.getInstance(context).getMatchingTypeface(fontType));

            if (shouldHideBottomLine) {
                getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.transparent), PorterDuff.Mode.SRC_ATOP);
            }
        }

        requestLayout();
    }

    private void init(Context context, AttributeSet attributeSet) {

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ParsiTextView, 0, 0);

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiEditText_replaceWithPersianDigits, true);
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiEditText_fontAdapterType, 0));
            shouldHideBottomLine = typedArray.getBoolean(R.styleable.ParsiEditText_hideBottomLine, true);

            typedArray.recycle();

            if (!isInEditMode()) {
                setTypeface(FontAdapter.getInstance(context).getMatchingTypeface(fontType));
            }

            if (shouldHideBottomLine) {
                getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.transparent), PorterDuff.Mode.SRC_ATOP);
            }
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

    public boolean shouldHideBottomLine() {
        return shouldHideBottomLine;
    }

    public void setShouldHideBottomLine(boolean shouldHideBottomLine) {
        this.shouldHideBottomLine = shouldHideBottomLine;

        requestLayout();
    }

}
