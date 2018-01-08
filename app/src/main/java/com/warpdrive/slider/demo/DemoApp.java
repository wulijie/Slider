package com.warpdrive.slider.demo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wulijie on 2018/1/8.
 */
public class DemoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) return;
        LeakCanary.install(this);
    }
}
