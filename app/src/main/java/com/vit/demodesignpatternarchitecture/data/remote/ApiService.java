package com.vit.demodesignpatternarchitecture.data.remote;

import com.vit.demodesignpatternarchitecture.data.model.User;
import com.vit.demodesignpatternarchitecture.data.remote.respone.UserResponse;
import com.vit.demodesignpatternarchitecture.data.remote.respone.UsersResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String URL_SERVER = "https://reqres.in/api/";

    @GET("users")
    Single<UsersResponse> getUsers();

    @GET("users")
    Single<UserResponse> getUser(@Query("id") int id);
}
