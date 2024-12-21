package com.rytis.armw.routes;

import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.models.GrupeData;
import com.rytis.armw.models.GrupeList;
import com.rytis.armw.models.Tournament;
import com.rytis.armw.models.Varzybos;
import com.rytis.armw.ui.bracket.BracketGroupModel;
import com.rytis.armw.ui.queue_controller.QueueModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface TournamentRoute {
    @GET("/api/tournament/")
    Call<TournamentModel.TournamentRespGetData> getTournaments();

    @Multipart
    @POST("/api/tournament/create")
    Call<String> createTournament(
            @Part("data") RequestBody data,
            @Part MultipartBody.Part file
    );

    @POST("/api/tournament/{id}/groups")
    Call<Void> createGroup( @Path("id") int id, @Body GrupeData grupeData);

    @GET("/api/tournament/{tournamentId}/groups")
    Call<GrupeList> getGroups(@Path("tournamentId") int tournamentId);

    @GET("/api/tournament/{tournamentId}/{groupId}/brackets")
    Call<BracketGroupModel> getgroupBracket(@Path("tournamentId") int tournamentId, @Path("groupId") int groupId);

    @GET("/api/tournament/{tournamentId}/tables")
    Call<List<QueueModel.Queue_Table>> getTournamentTables(@Path("tournamentId") int tournamentId);

}
