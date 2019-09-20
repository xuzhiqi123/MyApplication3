package com.example.myapplication.util;

import android.app.Application;

/**
 * Created by $lzj on 2019/3/25.
 */
public class MyApp extends Application {

    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();

        myApp = this;
    }

    public static MyApp getMyApp() {
        return myApp;
    }
}
