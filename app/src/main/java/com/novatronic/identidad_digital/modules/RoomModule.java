package com.novatronic.identidad_digital.modules;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.novatronic.identidad_digital.models.AppDatabase;
import com.novatronic.identidad_digital.models.UserDao;
import com.novatronic.identidad_digital.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private AppDatabase appDatabase;

    public RoomModule(Application mApplication) {
        appDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, "mainDatabase").allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    UserDao providesUserDao(AppDatabase appDatabase) {
        return appDatabase.getUserDao();
    }

   /* @Singleton
    @Provides
    UserRepository userRepository(UserDao userDao) {
        return new UserRepository(userDao);
    }*/

}