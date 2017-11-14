package com.android.voicenotes;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by maria on 17.10.17.
 */

public class ClientApplication extends Application {

    private static ClientApplication application;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        application = this;
        context = getApplicationContext();
     }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public static ClientApplication getInstance() {
        return application;
    }

    public static Context getContext() {
        return context;
    }
}
