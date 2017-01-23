package io.github.protelco;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import ir.protelco.pidget.parsi.Parsi;
import ir.protelco.pidget.parsi.ParsiUtils;
import ir.protelco.pidget.utils.Utils;

/**
 * Created by farhad on 1/22/17.
 */

public class CustomEditText extends AppCompatEditText {

    private InputFilter inputFilter ;

    public CustomEditText(Context context) {

        super(context);

        Log.d("custom" , "before init") ;

        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.d("custom" , "before init") ;

        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.d("custom" , "before init") ;

        init();
    }

    private void init(){

        inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {

                if(Utils.containsDigits(charSequence.toString())){

                    String converted = ParsiUtils.replaceWithParsiDigits(charSequence.toString()) ;

                    return converted ;
                }

                return charSequence ;
            }
        } ;

        this.setFilters(new InputFilter[]{inputFilter});
    }
}
