package com.vit.demodesignpatternarchitecture.ui;

import android.app.Activity;
import android.content.Context;

import com.vit.demodesignpatternarchitecture.data.UserRepository;
import com.vit.demodesignpatternarchitecture.data.remote.ApiService;
import com.vit.demodesignpatternarchitecture.data.remote.ApiServiceFactory;
import com.vit.demodesignpatternarchitecture.data.remote.source.UserRemoteDataSource;
import com.vit.demodesignpatternarchitecture.ui.user.UserActivity;
import com.vit.demodesignpatternarchitecture.ui.user.ViewModelFactory;
import com.vit.demodesignpatternarchitecture.ui.user.adapter.UserAdapter;
import com.vit.demodesignpatternarchitecture.ui.user_profile.UserProfileActivity;
import com.vit.demodesignpatternarchitecture.ui.user_profile.UserProfileContract;
import com.vit.demodesignpatternarchitecture.ui.user_profile.UserProfilePresenter;

/**
 * Demo Dependency Injection Design Pattern
 */

public class Injection {

    public static ApiService provideApiService() {
        return new ApiServiceFactory().makeApiService().create(ApiService.class);
    }

    public static UserRepository provideUserRepository() {
        return new UserRemoteDataSource(provideApiService());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserRepository repository = provideUserRepository();
        return new ViewModelFactory(repository);
    }

    public static UserProfileContract.View provideUserProfileView(Context context) {
        return (UserProfileContract.View) context;
    }

    public static UserProfileContract.Presenter provideUserProfilePresenter(Context context) {
        return new UserProfilePresenter(provideUserRepository(), provideUserProfileView(context));
    }


}
