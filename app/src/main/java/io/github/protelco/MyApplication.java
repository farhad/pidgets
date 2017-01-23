package io.github.protelco;

import android.app.Application;

import ir.protelco.pidget.font.FontAdapter;

/**
 * Created by farhad on 1/22/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        FontAdapter.getInstance(this)
                .regular("fonts/irsansregular.ttf")
                .bold("fonts/irsansbold.ttf") ;
    }
}
