package com.rytis.armw.routes;

import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.models.Tournament;
import com.rytis.armw.models.Varzybos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TournamentRoute {
    @GET("/api/tournament/")
    Call<List<TournamentModel.TournamentRespGetData>> getTournaments();
    @POST("/api/tournament/")
    Call<Void> createTournament(@Body Varzybos varzybos);
}
