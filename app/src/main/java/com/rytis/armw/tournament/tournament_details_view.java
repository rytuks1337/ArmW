package com.rytis.armw.tournament;

import static com.rytis.armw.R.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.rytis.armw.R;
import com.rytis.armw.databinding.ActivityTournamentDetailsViewBinding;
import com.rytis.armw.Retrofit_Pre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class tournament_details_view extends AppCompatActivity {

    ActivityTournamentDetailsViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTournamentDetailsViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Retrofit_Pre preRetrofit = new Retrofit_Pre(this);

        Intent intent = this.getIntent();

        if(intent != null) {

            int id = intent.getIntExtra("id", -1);
            String data = intent.getStringExtra("data");
            String status = intent.getStringExtra("status");
            String filepath = intent.getStringExtra("filepath");
            String pabaiga = intent.getStringExtra("pabaiga");
            String lokacija = intent.getStringExtra("lokacija");
            String aprasas = intent.getStringExtra("aprasas");
            String pavadinimas = intent.getStringExtra("pavadinimas");
            String time = getTime(data, pabaiga);
            if(filepath != null && !filepath.isEmpty()) {
                binding.tournamentName.setText(pavadinimas);
                Glide.with(this)
                        .load(preRetrofit.getBaseUrl() +
                                "api/" + filepath) // Assuming tournaments.getImageUrl() returns the image URL
                        .into(binding.tournamentImage); // Assuming you have an ImageView named 'tournamentImage' in your ViewHolder
            }
            binding.tournamentDate.setText(data.substring(0, 10));
            binding.tournamentDescription.setText(aprasas);
            binding.tournamentTime.setText(time);
            if(lokacija==null) lokacija="Nenurodytas";
            binding.tournamentLocation.setText("Adresas: " + lokacija);
            binding.registerToTournament.setOnClickListener(v -> {
                finish();
            });
            binding.viewTournament.setOnClickListener(v -> {
                SharedPreferences sharedPreferences = getSharedPreferences("tournament", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent resultIntent = new Intent();

                editor.putInt("tournament_id", id);

                editor.putString("tournament_name", pavadinimas);
                resultIntent.putExtra("tournament_name", pavadinimas);
                setResult(RESULT_OK, resultIntent);
                editor.apply();

                finish();
            });

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @NonNull
    private static String getTime(String data, String pabaiga) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        String formattedTime;
        Date date = null;
        try {
            date = inputFormat.parse(data);
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            formattedTime = outputFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return "Prasideda: " + formattedTime;
    }
}