package javaclasses;

import android.app.Application;

import arshmdw.piratil.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Arash on 6/12/2019.
 */

public class font extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("iransansmob.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
