package com.vit.demodesignpatternarchitecture.ui;

import android.content.Context;

import com.vit.demodesignpatternarchitecture.data.UserRepository;
import com.vit.demodesignpatternarchitecture.data.remote.ApiService;
import com.vit.demodesignpatternarchitecture.data.remote.ApiServiceFactory;
import com.vit.demodesignpatternarchitecture.data.remote.source.UserRemoteDataSource;
import com.vit.demodesignpatternarchitecture.ui.user.ViewModelFactory;

/**
 * Demo Dependency Injection Design Pattern
 */

public class Injection {

    public static ApiService provideApiService() {
        return new ApiServiceFactory().makeApiService().create(ApiService.class);
    }

    public static UserRepository provideUserRepository(Context context) {
        return new UserRemoteDataSource(provideApiService());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserRepository repository = provideUserRepository(context);
        return new ViewModelFactory(repository);
    }
}
