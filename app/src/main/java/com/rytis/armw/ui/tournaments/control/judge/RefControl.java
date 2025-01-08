package com.rytis.armw.ui.tournaments.control.judge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;
import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.databinding.ActivityTableControlBinding;
import com.rytis.armw.routes.TournamentRoute;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefControl extends AppCompatActivity {

    ActivityTableControlBinding binding;
    int id;
    int tournamentID;
    int dalyvio_id;
    int dalyvio2_id;
    int round;
    String dalyvio1_name;
    String dalyvio2_name;
    boolean executedSuccesfully;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();
        if(intent != null) {
            id = intent.getIntExtra("id", -1);
            dalyvio_id = intent.getIntExtra("dalyvio_id", -1);
            dalyvio2_id = intent.getIntExtra("dalyvio2_id", -1);
            dalyvio1_name = intent.getStringExtra("dalyvio1_name");
            dalyvio2_name = intent.getStringExtra("dalyvio2_name");
            tournamentID = intent.getIntExtra("tournamentID", -1);
            round = intent.getIntExtra("round", -1);
        }
        binding.player1Radio.setText(dalyvio1_name);
        binding.player2Radio.setText(dalyvio2_name);
        binding.winnerButton.setOnClickListener(v -> {
            if(binding.player1Radio.isChecked()){
                sendWinner(id,tournamentID,dalyvio_id);
            }else if(binding.player2Radio.isChecked()) {
                sendWinner(id, tournamentID, dalyvio2_id);
            }
            if(executedSuccesfully){
                setResult(RESULT_OK);
            }else {
                setResult(RESULT_CANCELED);
            }
            finish();
        });
    }

    public void sendWinner(int id, int tournament_id, int dalyvio_id){
        Retrofit_Pre preRetrofit = new Retrofit_Pre(this);
        Retrofit retrofit = preRetrofit.getRetrofit(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("laimetojas", dalyvio_id);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }


        TournamentRoute tournamentRoute = retrofit.create(TournamentRoute.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()); // send {"laimetojas": variable}
        Call<Void> call = tournamentRoute.updateMatch(tournament_id, id, requestBody);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    executedSuccesfully = true;
                    Toast.makeText(RefControl.this, "Išsaugotas atsakyamas", Toast.LENGTH_SHORT).show();
                } else {
                    executedSuccesfully = false;
                    Toast.makeText(RefControl.this, "Nepavyko išsaugoti atsakymo: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                executedSuccesfully = false;
                Toast.makeText(RefControl.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
