package ir.farhadfaghihi;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import io.github.farhad.typeface.ParsiTypeface;

/**
 * Created by farhad on 1/22/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        LeakCanary.install(this);

        ParsiTypeface.getInstance()
                .regular("fonts/mirza_regular.ttf")
                .bold("fonts/mirza_bold.ttf")
                .semiBold("fonts/mirza_semibold.ttf")
                .init(this);


    }
}
