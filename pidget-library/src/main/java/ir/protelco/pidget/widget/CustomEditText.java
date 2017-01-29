package ir.protelco.pidget.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import ir.protelco.pidget.R;
import ir.protelco.pidget.font.FontAdapter;

/**
 * Created by farhad on 1/29/17.
 */

public class CustomEditText extends AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);

        if(!isInEditMode()){

            init();
        }
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(!isInEditMode()){

            init();
        }

    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(!isInEditMode()){

            init();
        }
    }

    private void init(){

        getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.transparent), PorterDuff.Mode.SRC_ATOP);

        setTypeface(FontAdapter.getInstance(getContext()).getTypefaceRegular());
    }
}
