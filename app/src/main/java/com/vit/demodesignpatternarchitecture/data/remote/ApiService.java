package com.vit.demodesignpatternarchitecture.data.remote;

import com.vit.demodesignpatternarchitecture.data.remote.respone.UsersResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
    String URL_SERVER = "https://reqres.in/api/";

    @GET("users")
    Single<UsersResponse> getUsers();
}
