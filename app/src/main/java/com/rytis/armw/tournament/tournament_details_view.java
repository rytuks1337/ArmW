package com.rytis.armw.tournament;

import static com.rytis.armw.R.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rytis.armw.R;
import com.rytis.armw.databinding.ActivityTournamentDetailsViewBinding;

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

        Intent intent = this.getIntent();

        if(intent != null) {

            Integer id = intent.getIntExtra("id", -1);
            String data = intent.getStringExtra("data");
            String pabaiga = intent.getStringExtra("pabaiga");
            String lokacija = intent.getStringExtra("lokacija");
            String aprasas = intent.getStringExtra("aprasas");
            String pavadinimas = intent.getStringExtra("pavadinimas");
            String time = getTime(data, pabaiga);

            binding.tournamentName.setText(pavadinimas);
            binding.tournamentDate.setText(data.substring(0, 10));
            binding.tournamentDescription.setText(aprasas);
            binding.tournamentTime.setText(time);
            if(lokacija==null) lokacija="Nenurodytas";
            binding.tournamentLocation.setText(String.valueOf("Miestas: "+lokacija));
            binding.registerToTournament.setOnClickListener(v -> {
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