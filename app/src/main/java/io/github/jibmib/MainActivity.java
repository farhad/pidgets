package io.github.jibmib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ir.jibmib.pidgets.utils.MonetaryTextWatcher;
import ir.jibmib.pidgets.widget.ParsiButton;
import ir.jibmib.pidgets.widget.ParsiEditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ParsiEditText editText = (ParsiEditText)findViewById(R.id.etVerifyCode) ;

        editText.addTextChangedListener(new MonetaryTextWatcher(editText));

        ParsiButton button = (ParsiButton)findViewById(R.id.btn) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,editText.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
