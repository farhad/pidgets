package io.github.protelco;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ir.protelco.pidget.parsi.ParsiUtils;
import ir.protelco.pidget.widget.ParsiEditText;

import static java.security.AccessController.getContext;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);

        CustomEditText editText = (CustomEditText)findViewById(R.id.et) ;

        editText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/irsansregular.ttf"));
        editText.setText("345345345");

        final ParsiEditText parsiEditText = (ParsiEditText)findViewById(R.id.et2) ;

        parsiEditText.setText("0935227،7060");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("custom",ParsiUtils.replaceWithEnglishDigits(parsiEditText.getText().toString())) ;
            }
        });


    }
}
