package com.rytis.armw.routes;

import com.rytis.armw.dataModels.UserRegisterModel;
import com.rytis.armw.dataModels.UserloginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface AuthenticationRoute {
        @POST("/api/auth/login")
        Call<UserloginModel.UserLoginDataResp> postLoginUser (@Body UserloginModel.UserLoginData loginUser);
        @POST("/api/auth/register")
        Call<UserRegisterModel.UserRegisterModelResp> postRegisterUser (@Body UserRegisterModel.UserRegisterData registerUser);
}

