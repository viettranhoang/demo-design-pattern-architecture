package com.vit.demodesignpatternarchitecture.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.vit.demodesignpatternarchitecture.data.remote.ApiService.URL_SERVER;

/**
 * Demo Singleton Design Pattern
 */

public class ApiServiceFactory {

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    public static Retrofit makeApiService() {
        if (okHttpClient == null) {
            okHttpClient = makeOkHttpClient();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_SERVER)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient makeOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();

                    return chain.proceed(request);
                })
                .addInterceptor(logging)
                .build();
    }
}
