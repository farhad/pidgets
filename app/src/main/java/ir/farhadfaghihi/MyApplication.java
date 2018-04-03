package ir.farhadfaghihi;

import android.app.Application;

import io.github.farhad.typeface.ParsiTypeface;

/**
 * Created by farhad on 1/22/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParsiTypeface.getInstance()
                .regular("fonts/irsansregular.ttf")
                .bold("fonts/irsansbold.ttf")
                .init(this);
    }
}
