package ir.farhadfaghihi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.github.farhad.utils.MonetaryTextWatcher;
import io.github.farhad.widget.ParsiButton;
import io.github.farhad.widget.ParsiEditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ParsiEditText editText = (ParsiEditText)findViewById(R.id.et) ;

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
