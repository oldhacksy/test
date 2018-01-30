package com.novatronic.identidad_digital.views.ui.login;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.novatronic.identidad_digital.models.User;
import com.novatronic.identidad_digital.repository.UserRepository;


import javax.inject.Inject;



public class UserProfileViewModel extends ViewModel {
    private  UserRepository userRepo;
    private MutableLiveData<User> user;

    @Inject
    public UserProfileViewModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public void tryLogin(String documentId, String pin) {
        this.user.setValue(userRepo.getUser(documentId,pin).getValue());

    }

    public LiveData<User> getUser() {
        if (this.user == null) {
            this.user = new MutableLiveData<User>();
        }
        return user;
    }

    public void createMainUser(){
        User user = new User();
        user.setCountry("PE");
        user.setDocument("43186279");
        user.setPin("12345");
        user.setDocument_kind("DNI");
        user.setFirstName("Hansy");
        user.setLastName("Schmitt");
        userRepo.createUser(user);
    }


}
