package com.rytis.armw.routes;

import com.rytis.armw.dataModels.UserRegisterModel;
import com.rytis.armw.dataModels.UserloginModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface AuthenticationRoute {
        @POST("/api/auth/login")
        Call<UserloginModel.UserLoginDataResp> postLoginUser (@Body UserloginModel.UserLoginData loginUser);

        @GET("/api/auth/test")
        Call<ResponseBody> getAuthTest(@Header("Authorization") String authToken);


        @POST("/api/user/register")
        Call<UserRegisterModel.UserRegisterModelResp> postRegisterUser (@Body UserRegisterModel.UserRegisterData registerUser);
}

