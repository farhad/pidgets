package ir.farhadfaghihi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.KeyEvent;
import android.view.View;

import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.PidgetUtils;
import io.github.farhad.utils.textwatcher.BankCardTextWatcher;
import io.github.farhad.widget.ParsiButton;
import io.github.farhad.widget.ParsiEditText;
import io.github.farhad.widget.ParsiTextView;

public class MainActivity extends Activity {


    ParsiEditText editText ;
    ParsiTextView textView ;
    ParsiButton btnChange ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.etCardNumber) ;
        editText.addTextChangedListener(new BankCardTextWatcher(editText,16));
        textView = findViewById(R.id.tvCardNumber) ;
        textView.addTextChangedListener(new BankCardTextWatcher(textView,16));
        textView.setText("6219861030604545");
        btnChange = findViewById(R.id.btnChange) ;

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText.setText(PidgetUtils.formatAmountWithCurrencyTagIR(String.valueOf(50000)));
            }
        });
    }
}
