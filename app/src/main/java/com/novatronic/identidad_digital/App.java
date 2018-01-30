package com.novatronic.identidad_digital;


import android.app.Application;

import com.novatronic.identidad_digital.modules.AppComponent;
import com.novatronic.identidad_digital.modules.AppModule;
import com.novatronic.identidad_digital.modules.DaggerAppComponent;
import com.novatronic.identidad_digital.modules.RoomModule;

public class App extends Application {
    AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent =DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .roomModule(new RoomModule(this))
                .build();

    }
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
