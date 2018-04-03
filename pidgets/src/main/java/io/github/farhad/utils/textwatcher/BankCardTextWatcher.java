package io.github.farhad.utils.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Pattern;

public class BankCardTextWatcher implements TextWatcher {

    private EditText editText ;

    public BankCardTextWatcher(EditText editText)
    {
        this.editText = editText ;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        Pattern cardCodePattern = Pattern.compile("([0-9]{0,4})|([0-9]{4}-)+|([0-9]{4}-[0-9]{0,4})+");

        String numbersOnly = removeGroupSeparators(editable.toString());

        if (editable.length() == 16 && !cardCodePattern.matcher(editable).matches())
        {
            String code = formatNumbersAsCode(numbersOnly);

            editText.removeTextChangedListener(this);
            editText.setText(code);
            editText.setSelection(code.length());
            editText.addTextChangedListener(this);
        }
    }



    private String removeGroupSeparators(CharSequence s) {
        return s.toString().replaceAll("-", "");
    }

    private String formatNumbersAsCode(CharSequence s) {
        int groupDigits = 0;
        String tmp = "";
        int i;
        for (i = 0; i < s.length() - 1; i++) {
            tmp += s.charAt(i);
            ++groupDigits;
            if (groupDigits == 4) {
                tmp += "-";
                groupDigits = 0;
            }
        }
        return tmp + s.charAt(i);
    }
}
