package com.novatronic.identidad_digital.modules;

import android.app.Application;

import com.novatronic.identidad_digital.models.AppDatabase;
import com.novatronic.identidad_digital.models.UserDao;
import com.novatronic.identidad_digital.repository.UserRepository;
import com.novatronic.identidad_digital.views.ui.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    void inject(Application application);
    void inject(LoginActivity loginActivity);
    UserDao userDao();

    AppDatabase appDatabase();

    UserRepository userRepository();

    Application application();

}
