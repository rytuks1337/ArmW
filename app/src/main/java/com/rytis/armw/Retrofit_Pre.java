package com.rytis.armw;

import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.routes.AuthenticationRoute;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Pre {
    Retrofit retrofit;
    public Retrofit getRetrofit() {
        return retrofit;
    }
    public Retrofit_Pre() {
        this.retrofit = new Retrofit.Builder().baseUrl("http://10.107.0.155:3000").addConverterFactory(GsonConverterFactory.create()).build();
    }


}
