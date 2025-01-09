package com.rytis.armw.ui.queue_controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.rytis.armw.R;

public class ControlTournament extends Fragment {
    private static final String TOURNAMENT_ID = "tournament_id";
    private int tournamentId;

    public ControlTournament() {

    }

    public static ControlTournament newInstance(int tournamentId) {
        ControlTournament fragment = new ControlTournament();
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
        View view = inflater.inflate(R.layout.activity_register_tournament, container, false);

//        EditText etTournamentName = view.findViewById(R.id.etTournamentName);
//        EditText etTournamentDescription = view.findViewById(R.id.etTournamentDescription);
//        Button btnStartTournament = view.findViewById(R.id.btnStartTournament);
//
//        btnStartTournament.setOnClickListener(v -> {
//            String tournamentName = etTournamentName.getText().toString();
//            String tournamentDescription = etTournamentDescription.getText().toString();
//
//            // Add API request to start the tournament
//            // Example:
//            // ApiService.getInstance().startTournament(tournamentId, tournamentName, tournamentDescription, new ApiCallback<Void>() {
//            //     @Override
//            //     public void onSuccess(Void result) {
//            //         Toast.makeText(getContext(), "Tournament started successfully", Toast.LENGTH_SHORT).show();
//            //     }
//            //
//            //     @Override
//            //     public void onFailure(Exception e) {
//            //         Toast.makeText(getContext(), "Failed to start tournament", Toast.LENGTH_SHORT).show();
//            //     }
//            // });
//        });

        return view;
    }
}
