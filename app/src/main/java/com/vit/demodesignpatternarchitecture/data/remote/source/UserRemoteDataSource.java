package com.vit.demodesignpatternarchitecture.data.remote.source;

import com.vit.demodesignpatternarchitecture.data.UserRepository;
import com.vit.demodesignpatternarchitecture.data.remote.ApiService;
import com.vit.demodesignpatternarchitecture.data.remote.respone.UsersResponse;

import io.reactivex.Flowable;

public class UserRemoteDataSource implements UserRepository {

    private final ApiService mApiService;

    public UserRemoteDataSource(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Flowable<UsersResponse> getUsersResponse() {
        return mApiService.getUsers().toFlowable();
    }
}
