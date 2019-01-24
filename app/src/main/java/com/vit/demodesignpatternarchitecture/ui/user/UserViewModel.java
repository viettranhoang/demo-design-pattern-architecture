package com.vit.demodesignpatternarchitecture.ui.user;

import android.arch.lifecycle.ViewModel;

import com.vit.demodesignpatternarchitecture.data.UserRepository;
import com.vit.demodesignpatternarchitecture.data.model.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Demo MVVM Architecture
 */

public class UserViewModel extends ViewModel {

    private UserRepository mRepository;

    public UserViewModel(UserRepository repository) {
        this.mRepository = repository;
    }

    public Flowable<List<User>> getUsers() {
        return mRepository.getUsersResponse()
                .map(usersResponse -> usersResponse.getUsers());
    }
}
