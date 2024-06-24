package com.rytis.armw.routes;

import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.models.Tournament;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TournamentRoute {
    @GET("/api/tournament/")
    Call<List<TournamentModel.TournamentRespGetData>> getTournaments();
}
