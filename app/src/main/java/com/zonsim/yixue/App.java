package com.zonsim.yixue;

import android.app.Application;

/**
 * ^-^
 * Created by tang-jw on 2017/6/23.
 */

public class App extends Application {
    
    public static App app;
    
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
