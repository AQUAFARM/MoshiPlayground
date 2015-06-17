package net.yslibrary.moshiplayground;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
