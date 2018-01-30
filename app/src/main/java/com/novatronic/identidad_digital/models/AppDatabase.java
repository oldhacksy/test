package com.novatronic.identidad_digital.models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class}, version = AppDatabase.VERSION)
public abstract class AppDatabase extends RoomDatabase {
    static final int VERSION = 1;
    public abstract UserDao getUserDao();
}