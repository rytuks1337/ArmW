package com.rytis.armw.ui.bracket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.routes.TournamentRoute;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BracketActivity extends AppCompatActivity {

    LinearLayout bracketLayoutW;
    LinearLayout bracketLayoutL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_brackets);


        bracketLayoutW = findViewById(R.id.linear_layout_bracketW);
        bracketLayoutL = findViewById(R.id.linear_layout_bracketL);

        Intent intent = getIntent();
        int tournamentId = intent.getIntExtra("tournamentId", -1);
        int groupId = intent.getIntExtra("groupId", -1);

        // Get data for the bracket
        BracketGroupModel groupMatchData = getBracketData(tournamentId, groupId);

    }

    private BracketGroupModel getBracketData(int tournamentId, int groupId) { //Logic for getting and using the bracket data
        BracketGroupModel groupBracketModel = new BracketGroupModel();

        Retrofit_Pre retrofit_pre = new Retrofit_Pre(this);
        Retrofit retrofit = retrofit_pre.getRetrofit(false);
        TournamentRoute tournamentRoute = retrofit.create(TournamentRoute.class);
        Call<BracketGroupModel> call = tournamentRoute.getgroupBracket(tournamentId, groupId);
        call.enqueue(new Callback<BracketGroupModel>() {
            @Override
            public void onResponse(Call<BracketGroupModel> call, Response<BracketGroupModel> response) {
                if (response.isSuccessful()) {
                    BracketGroupModel result = response.body();
                    if (result == null || result.getWinB() == null) {
                        Toast.makeText(BracketActivity.this, "Error: Nerasta grupė", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    for (int i = 0; i < result.getWinB().size(); i++) {
                        List<BracketMatchModel> roundMatches = result.getWinB().get(i);
                        LinearLayout roundLayout = createRoundLayout(roundMatches, i + 1);
                        bracketLayoutW.addView(roundLayout);
                    }
                    for (int i = 0; i < result.getLosB().size(); i++) {
                        List<BracketMatchModel> roundMatches = result.getLosB().get(i);
                        LinearLayout roundLayout = createRoundLayout(roundMatches, i + 1);
                        bracketLayoutL.addView(roundLayout);
                    }
                }
            }
            @Override
            public void onFailure(Call<BracketGroupModel> call, Throwable throwable) {
                Toast.makeText(BracketActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        return groupBracketModel;
    }

    private LinearLayout createRoundLayout(List<BracketMatchModel> round, int roundNumber) {
        LinearLayout roundLayout = new LinearLayout(this);
        roundLayout.setOrientation(LinearLayout.VERTICAL);
        roundLayout.setPadding(16, 0, 16, 0);

        // Add a title for the round
        TextView roundTitle = new TextView(this);
        roundTitle.setText("Raundas " + roundNumber);
        roundTitle.setGravity(Gravity.CENTER);
        roundTitle.setTextSize(21);
        roundTitle.setTextColor(getResources().getColor(R.color.white));
        roundTitle.setPadding(0, 16, 0, 16);
        roundTitle.setVisibility(View.GONE);
        roundLayout.addView(roundTitle);

        // Add each match to the round layout
        for (BracketMatchModel match : round) {
            View matchView = createMatchView(match, roundLayout);
            if((match.getPlayer1Id() == null || match.getPlayer2Id() == null) && match.getRound() != 1) {
                matchView.setVisibility(View.GONE);
            }else{
                if(roundTitle.getVisibility() == View.GONE){
                    roundTitle.setVisibility(View.VISIBLE);
                }
            }


            roundLayout.addView(matchView);
        }


        return roundLayout;
    }

    private View createMatchView(BracketMatchModel match , ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View matchView = inflater.inflate(R.layout.matchvs_item, parent, false);

        TextView player1TextView = matchView.findViewById(R.id.text_player1);
        TextView player2TextView = matchView.findViewById(R.id.text_player2);
        TextView winnerTextView = matchView.findViewById(R.id.text_winner);


        player1TextView.setText(match.getPlayer1());
        player2TextView.setText(match.getPlayer2());
        winnerTextView.setText(match.getWinner());

        return matchView;
    }
}
