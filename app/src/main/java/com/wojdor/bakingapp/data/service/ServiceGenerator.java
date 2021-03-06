package com.wojdor.bakingapp.data.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceGenerator {

    private static ServiceGenerator instance;

    private Retrofit retrofit;

    private ServiceGenerator(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ServiceGenerator getInstance(String baseUrl) {
        if (instance == null) {
            instance = new ServiceGenerator(baseUrl);
        }
        return instance;
    }

    public <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }
}
