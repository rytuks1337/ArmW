package com.rytis.armw.ui.tournaments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rytis.armw.MainActivity;
import com.rytis.armw.R;
import com.rytis.armw.auth.TokenManager;
import com.rytis.armw.databinding.FragmentRegisterPogrupisBinding;
import com.rytis.armw.models.Pogrupis;
import com.rytis.armw.models.Varzybos;
import com.rytis.armw.routes.TournamentRoute;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterPogrupis extends AppCompatActivity {

    private List<Pogrupis> pogrupisList = new ArrayList<>();
    EditText grupes_pavad, svoris, metai, lytis, ranka;
    private Button addGroupButton, submitButton;
    private FragmentRegisterPogrupisBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register_pogrupis);

        EditText grupes_pavad = findViewById(R.id.groupNameEditText);
        EditText svoris = findViewById(R.id.weightEditText);
        EditText metai = findViewById(R.id.ageEditText);
        EditText lytis = findViewById(R.id.genderEditText);
        EditText ranka = findViewById(R.id.handEditText);
        addGroupButton = findViewById(R.id.addGroupButton);
        submitButton = findViewById(R.id.submitButton);

        // Get the data from the previous activity
        final String pavadinimas = getIntent().getStringExtra("pavadinimas");
        final String data = getIntent().getStringExtra("data");
        final String pradzia = getIntent().getStringExtra("pradzia");
        final String pabaiga = getIntent().getStringExtra("pabaiga");
        final String lokacija = getIntent().getStringExtra("lokacija");
        final int stalu_sk = getIntent().getIntExtra("stalu_sk", 0);
        final String aprasas = getIntent().getStringExtra("aprasas");

        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pogrupis pogrupis = new Pogrupis();
                pogrupis.setPavadinimas(grupes_pavad.getText().toString());
                pogrupis.setSvoris(Integer.parseInt(svoris.getText().toString()));
                pogrupis.setAmzius_k(Integer.parseInt(metai.getText().toString()));
                pogrupis.setLytis(lytis.getText().toString());
                pogrupis.setRanka(ranka.getText().toString());
                pogrupisList.add(pogrupis);

                // Clear the input fields
                grupes_pavad.setText("");
                svoris.setText("");
                metai.setText("");
                lytis.setText("");
                ranka.setText("");
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Varzybos varzybos = new Varzybos();
                varzybos.setPavadinimas(pavadinimas);
                varzybos.setData(data);
                varzybos.setPradzia(pradzia);
                varzybos.setPabaiga(pabaiga);
                varzybos.setLokacija(lokacija);
                varzybos.setStalu_sk(stalu_sk);
                varzybos.setAprasas(aprasas);
                varzybos.setPogrupis_sarasas(pogrupisList);

                // Send the POST request
                sendPostRequest(varzybos);
            }
        });
    }

    private void sendPostRequest(Varzybos varzybos) {
        String token = TokenManager.getJwtToken(this);
        if (token==null){
            Toast.makeText(this, "Session expired",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.107.0.155:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new okhttp3.OkHttpClient.Builder().addInterceptor(chain -> {
                    okhttp3.Request original = chain.request();
                    okhttp3.Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", token);
                    okhttp3.Request request = requestBuilder.build();
                    return chain.proceed(request);
                }).build())
                .build();

        TournamentRoute championshipRoute = retrofit.create(TournamentRoute.class);
        Call<Void> call = championshipRoute.createTournament(varzybos);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    finish();
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
            }
        });
    }
}