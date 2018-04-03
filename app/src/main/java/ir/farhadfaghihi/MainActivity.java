package ir.farhadfaghihi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import io.github.farhad.typeface.FontType;
import io.github.farhad.utils.textwatcher.BankCardTextWatcher;
import io.github.farhad.widget.ParsiButton;
import io.github.farhad.widget.ParsiEditText;

public class MainActivity extends Activity {

    ParsiEditText editText ;
    ParsiButton btnChange ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.etPhoneNumber) ;
        editText.addTextChangedListener(new BankCardTextWatcher(editText));
        btnChange = findViewById(R.id.btnChange) ;

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText.setText("6219861030904261");
            }
        });
    }
}
