package com.vit.demodesignpatternarchitecture.ui.user;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.vit.demodesignpatternarchitecture.data.UserRepository;

/**
 * Demo Factory Design Pattern
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final UserRepository mRepository;

    public ViewModelFactory(UserRepository repository) {
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(mRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
