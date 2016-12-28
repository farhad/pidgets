package ir.protelco.pidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import ir.protelco.pidget.R;
import ir.protelco.pidget.font.FontAdapter;
import ir.protelco.pidget.font.FontType;
import ir.protelco.pidget.parsi.ParsiUtils;
import ir.protelco.pidget.utils.Utils;

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
        if (shouldReplaceWithParsiDigits && Utils.containsDigits(text.toString())) {

            super.setText(ParsiUtils.replaceWithParsiDigits(text.toString()), type);
        } else {

            super.setText(text, type);
        }
    }

    private void init(Context context) {

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiTextView);

        shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiTextView_replaceWithPersianDigits, false);
        fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiEditText_fontAdapterType, 0));

        typedArray.recycle();

        setTypeface(FontAdapter.getInstance(context).getMatchingTypeface(fontType));
    }

    private void init(Context context, AttributeSet attributeSet) {

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ParsiTextView, 0, 0);

        shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiTextView_replaceWithPersianDigits, false);
        fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiEditText_fontAdapterType, 0));

        typedArray.recycle();

        setTypeface(FontAdapter.getInstance(context).getMatchingTypeface(fontType));

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
}
