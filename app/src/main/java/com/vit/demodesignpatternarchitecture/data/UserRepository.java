package com.vit.demodesignpatternarchitecture.data;

import com.vit.demodesignpatternarchitecture.data.model.User;
import com.vit.demodesignpatternarchitecture.data.remote.respone.UsersResponse;

import io.reactivex.Flowable;

public interface UserRepository {

    Flowable<UsersResponse> getUsersResponse();

    Flowable<User> getUser(int id);
}
