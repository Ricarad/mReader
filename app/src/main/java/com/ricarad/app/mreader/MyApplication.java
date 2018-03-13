package com.ricarad.app.mreader;

import android.app.Application;
import android.content.Context;

/**
 * Created by dongdong on 2018/3/2.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

       // initXunFei();
    }
    /**
     * 获取全局上下文*/
    public static Context getContext() {
        return context;
    }
}