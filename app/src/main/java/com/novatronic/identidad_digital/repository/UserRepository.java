package com.novatronic.identidad_digital.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;

import com.novatronic.identidad_digital.models.AppDatabase;
import com.novatronic.identidad_digital.models.User;
import com.novatronic.identidad_digital.models.UserDao;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {
    private  final UserDao userDao;
    private MutableLiveData<User> user;

    @Inject
    public UserRepository(UserDao userDao ){
        this.userDao = userDao;
    }

    public LiveData<User> getUser(String documentId, String pin) {
        //refreshUser(userId);
        //Return user from database
        LiveData<User> user = userDao.login(documentId,pin);

        return user;
    }

    public void createUser(User user) {
        userDao.insert(user);
    }

    /*private void refreshUser(final String userId) {
        executor.execute(() -> {
            // running in a background thread
            // check if user was fetched recently
            boolean userExists = userDao.hasUser(FRESH_TIMEOUT);
            if (!userExists) {
                // refresh the data
                Response response = webservice.getUser(userId).execute();
                // TODO check for error etc.
                // Update the database.The LiveData will automatically refresh so
                // we don't need to do anything else here besides updating the database
                userDao.save(response.body());
            }
        });
    }*/
}
