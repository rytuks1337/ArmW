package com.rytis.armw.routes;

import com.rytis.armw.dataModels.UserloginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TournamentRoute {
    @POST("/api/users/login")
    Call<UserloginModel.UserLoginDataResp> post (@Body UserloginModel.UserLoginData loginUser);
    @GET("/api/tournament/")
    Call<UserloginModel.UserLoginDataResp> postR (@Body UserloginModel.UserLoginData loginUser);
}
