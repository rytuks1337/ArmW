package com.rytis.armw.routes;

import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.models.GrupeData;
import com.rytis.armw.models.GrupeList;
import com.rytis.armw.models.RoleGet;
import com.rytis.armw.models.VarzybosGet;
import com.rytis.armw.ui.bracket.BracketGroupModel;
import com.rytis.armw.ui.queue_controller.Queue_Table;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TournamentRoute {
    @GET("/api/tournament/tournaments/{page}")
    Call<TournamentModel.TournamentRespGetData> getTournaments(
            @Path("page") int page,
            @Query("isMine") boolean isMine,
            @Query("hasPrivilages") boolean hasPrivilages,
            @Query("participant") boolean participant,
            @Query("search") String search
    );

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

    @GET("/api/tournament/{tournamentId}")
    Call<VarzybosGet> getTournament(@Path("tournamentId") int tournamentId);

    @GET("/api/tournament/{tournamentId}/role")
    Call<RoleGet> getRole(@Path("tournamentId") int tournamentId);

    @GET("/api/tournament/{tournamentId}/{groupId}/brackets")
    Call<BracketGroupModel> getgroupBracket(@Path("tournamentId") int tournamentId, @Path("groupId") int groupId);

    @GET("/api/tournament/{tournamentId}/tables")
    Call<List<Queue_Table>> getTournamentTables(@Path("tournamentId") int tournamentId);

    @PUT("/api/tournament/{tournamentId}/match/{matchID}")
    Call<Void> updateMatch(@Path("tournamentId") int tournamentId, @Path("matchID") int matchID, @Body RequestBody requestBody);

}
