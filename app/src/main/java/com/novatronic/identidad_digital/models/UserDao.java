package com.novatronic.identidad_digital.models;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name = :first AND "
            + "last_name = :last LIMIT 1")
    LiveData<User> findByName(String first, String last);

    @Query("SELECT * FROM user WHERE document = :document AND pin = :pin LIMIT 1")
    LiveData<User> login(String document, String pin);

    @Query("SELECT * FROM user WHERE document = :documentId")
    LiveData<User> load(String documentId);

    @Insert
    void insertAll(User... users);
    @Insert
    void insert(User user);
    @Delete
    void delete(User user);
}