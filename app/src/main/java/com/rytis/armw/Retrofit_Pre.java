package com.rytis.armw;

import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.routes.AuthenticationRoute;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Pre {
    Retrofit retrofit;
    public Retrofit getRetrofit() {
        return this.retrofit;
    }
    public Retrofit_Pre() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new LoggingInterceptor());
        this.retrofit = new Retrofit.Builder().baseUrl("http://192.168.137.1:3000/").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
    }


}
