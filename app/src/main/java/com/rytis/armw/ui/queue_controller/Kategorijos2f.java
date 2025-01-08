package com.rytis.armw.ui.queue_controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.models.Grupe;
import com.rytis.armw.models.GrupeList;
import com.rytis.armw.routes.TournamentRoute;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Kategorijos2f extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GroupListAdaptor groupAdapter;
    private List<Grupe> pogrupisList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_groupf);

        pogrupisList = (ArrayList<Grupe>) getIntent().getSerializableExtra("tournamentList");
        recyclerView = findViewById(R.id.recycler_groups);
        groupAdapter = new GroupListAdaptor(this, pogrupisList);
        recyclerView.setAdapter(groupAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}