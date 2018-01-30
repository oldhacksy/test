package com.novatronic.identidad_digital.modules;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.novatronic.identidad_digital.views.ui.login.UserProfileViewModel;
import com.novatronic.identidad_digital.views.ui.login.UserProfileViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    ViewModelProvider.Factory provideListIssuesViewModelFactory(
            UserProfileViewModelFactory factory
    ) {
        return factory;
    }
}