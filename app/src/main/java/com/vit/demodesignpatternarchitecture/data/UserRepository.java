package com.vit.demodesignpatternarchitecture.data;

import com.vit.demodesignpatternarchitecture.data.remote.respone.UsersResponse;

import io.reactivex.Flowable;

public interface UserRepository {

    Flowable<UsersResponse> getUsersResponse();
}
