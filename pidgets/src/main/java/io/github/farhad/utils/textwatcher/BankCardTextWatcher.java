package io.github.farhad.utils.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.Pattern;

public class BankCardTextWatcher implements TextWatcher {

  private TextView textView;
  private int lastLength;
  private int minLengthForGrouping;

  public BankCardTextWatcher(TextView textView) {
    this.textView = textView;
    this.minLengthForGrouping = 0;
  }

  public BankCardTextWatcher(TextView textView, int minLengthForGrouping) {
    this.textView = textView;
    this.minLengthForGrouping = minLengthForGrouping;
  }

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    lastLength = charSequence.length();
  }

  @Override
  public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable editable) {

    Pattern cardCodePattern = Pattern.compile("([0-9]{0,4})|([0-9]{4}-)+|([0-9]{4}-[0-9]{0,4})+");

    String formattedText = editable.toString();

    if (lastLength > editable.toString().length()) {
      formattedText = removeGroupSeparators(editable.toString());
    } else if (editable.length() >= minLengthForGrouping
        && !cardCodePattern.matcher(editable).matches()) {
      formattedText = formatNumbersAsCode(removeGroupSeparators(editable.toString()));
    }

    textView.removeTextChangedListener(this);
    textView.setText(formattedText);
    textView.addTextChangedListener(this);

    if (textView instanceof EditText) {
      ((EditText) textView).setSelection(textView.getText().length());
    }
  }


  private String removeGroupSeparators(CharSequence s) {
    return s.toString().replaceAll("-", "");
  }

  private String formatNumbersAsCode(CharSequence s) {
    int groupDigits = 0;
    StringBuilder tmp = new StringBuilder();
    int i;
    for (i = 0; i < s.length() - 1; i++) {
      tmp.append(s.charAt(i));
      ++groupDigits;
      if (groupDigits == 4) {
        tmp.append("-");
        groupDigits = 0;
      }
    }
    return tmp.toString() + s.charAt(i);
  }
}
