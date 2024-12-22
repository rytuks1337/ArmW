package com.rytis.armw.ui.queue_controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.models.Grupe;
import com.rytis.armw.routes.TournamentRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class WresQueue extends Fragment {

    private static final String TOURNAMENT_ID = "tournament_id";
    private RecyclerView recyclerView;
    private QueueTableAdapter tableadapter;
    private List<Queue_Table> tableList = new ArrayList<>();
    private int tournamentId;
    public static WresQueue newInstance(int tournamentId){
        WresQueue fragment = new WresQueue();
        Bundle args = new Bundle();
        args.putInt(TOURNAMENT_ID, tournamentId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tournamentId = getArguments().getInt(TOURNAMENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wres_queue, container, false);
        recyclerView = view.findViewById(R.id.recycler_master_table);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tableadapter = new QueueTableAdapter(tableList);
        recyclerView.setAdapter(tableadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getTables();
        return view;
    }

    public void addTable(Queue_Table table) {
        tableList.add(table);
        tableadapter.notifyDataSetChanged();
    }
    public void getTables() {
        Retrofit_Pre retrofit_pre = new Retrofit_Pre(getContext());
        Retrofit retrofit = retrofit_pre.getRetrofit(true);
        TournamentRoute tournamentRoute = retrofit.create(TournamentRoute.class);
        Call<List<Queue_Table>> call = tournamentRoute.getTournamentTables(tournamentId);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Queue_Table>> call, Response<List<Queue_Table>> response) {
                if (response.isSuccessful()) {
                    List<Queue_Table> tables = response.body();
                    if (tables != null) {
                        for (Queue_Table table : tables) {
                            addTable(table);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Queue_Table>> call, Throwable throwable) {
                Toast.makeText(getContext(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}