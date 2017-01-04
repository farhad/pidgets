package io.github.protelco;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        View v = findViewById(R.id.touch_me_view);
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(MainActivity.this, RevealActivity.class);
                i.putExtra("x", (int)event.getX()); //We'll cover why we put x and y into the Intent
                i.putExtra("y", (int)event.getY());
                startActivity(i);
                return false;
            }
        });
    }
}
