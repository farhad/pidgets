package io.github.farhad.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;

public class ParsiAutoFitTextView extends ParsiTextView {

    private static final int DEFAULT_MIN_TEXT_SIZE = 8; // px
    private static final float PRECISION = 0.5f;

    private float minTextSize;
    private float maxTextSize;
    private float precision;
    private Paint mPaint;

    public ParsiAutoFitTextView(Context context) {

        super(context);

        initialize();
    }

    public ParsiAutoFitTextView(Context context, AttributeSet attrs) {

        super(context, attrs);

        initialize();
    }

    public ParsiAutoFitTextView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        initialize();
    }

    private void initialize() {

        minTextSize = DEFAULT_MIN_TEXT_SIZE;
        maxTextSize = getTextSize();
        precision = PRECISION;
        mPaint = new Paint();
    }

    private void refitText(String text, int width) {
        if (width > 0) {
            Context context = getContext();
            Resources r = Resources.getSystem();

            int targetWidth = width - getPaddingLeft() - getPaddingRight();
            float newTextSize = maxTextSize;
            float high = maxTextSize;
            float low = 0;

            if (context != null) {
                r = context.getResources();
            }

            mPaint.set(getPaint());
            mPaint.setTextSize(newTextSize);

            if (mPaint.measureText(text) > targetWidth) {
                newTextSize = getTextSize(r, text, targetWidth, low, high);

                if (newTextSize < minTextSize) {
                    newTextSize = minTextSize;
                }
            }

            setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);

        }
    }

    private float getTextSize(Resources resources, String text,
                              float targetWidth, float low, float high) {
        float mid = (low + high) / 2.0f;

        mPaint.setTextSize(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, mid, resources.getDisplayMetrics()));
        float textWidth = mPaint.measureText(text);

        if ((high - low) < precision) {
            return low;
        } else if (textWidth > targetWidth) {
            return getTextSize(resources, text, targetWidth, low, mid);
        } else if (textWidth < targetWidth) {
            return getTextSize(resources, text, targetWidth, mid, high);
        } else {
            return mid;
        }
    }

    @Override
    protected void onTextChanged(final CharSequence text, final int start,
                                 final int lengthBefore, final int lengthAfter) {

        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        refitText(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            refitText(getText().toString(), w);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        refitText(getText().toString(), parentWidth);
    }

    public float getMinTextSize() {
        return minTextSize;
    }

    public void setMinTextSize(float minTextSize) {
        this.minTextSize = minTextSize;
    }

    public float getMaxTextSize() {
        return maxTextSize;
    }

    public void setMaxTextSize(float maxTextSize) {
        this.maxTextSize = maxTextSize;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
}
