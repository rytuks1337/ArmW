package com.rytis.armw.ui.tournaments.register_tournament;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.auth.TokenManager;
import com.rytis.armw.databinding.FragmentRegisterPogrupisBinding;
import com.rytis.armw.models.Grupe;
import com.rytis.armw.models.Varzybos;
import com.rytis.armw.routes.TournamentRoute;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterPogrupis extends AppCompatActivity {

    private List<Grupe> pogrupisList = new ArrayList<>();
    private Button addGroupButton, submitButton;
    private RecyclerView groupsRecyclerView;
    private RegisterGroupAdapter groupAdapter;
    private FragmentRegisterPogrupisBinding binding;
    private byte[] imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register_pogrupis);

        EditText grupes_pavad = findViewById(R.id.groupNameEditText);
        EditText svoris = findViewById(R.id.weightEditText);
        EditText metai = findViewById(R.id.ageEditText);
        CheckBox lytisM = findViewById(R.id.maleCheckBox);
        CheckBox lytisF = findViewById(R.id.femaleCheckBox);
        CheckBox rankaD = findViewById(R.id.rightCheckBox);
        CheckBox rankaK = findViewById(R.id.leftCheckBox);
        addGroupButton = findViewById(R.id.addGroupButton);
        submitButton = findViewById(R.id.submitButton);
        groupsRecyclerView = findViewById(R.id.groupsRecyclerView);

        groupAdapter = new RegisterGroupAdapter(pogrupisList);
        groupsRecyclerView.setAdapter(groupAdapter);
        groupsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.Callback callback = new SimpleItemTouchCallback(groupAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(groupsRecyclerView);


        // Get the data from the previous activity

        final String pavadinimas = getIntent().getStringExtra("pavadinimas");
        final String data = getIntent().getStringExtra("data");
        final String pradzia = getIntent().getStringExtra("pradzia");
        final String pabaiga = getIntent().getStringExtra("pabaiga");
        final String lokacija = getIntent().getStringExtra("lokacija");
        final int stalu_sk = getIntent().getIntExtra("stalu_sk", 0);
        final String aprasas = getIntent().getStringExtra("aprasas");
        imageBitmap = getIntent().getByteArrayExtra("imageBitmap");

        addGroupButton.setOnClickListener(v -> {
            if (!lytisM.isChecked() && !lytisF.isChecked()) {
                Toast.makeText(this, "Prašau pasirinkti bent vieną lytį", Toast.LENGTH_SHORT).show();
                return;
            }
            if (grupes_pavad.getText().toString().isEmpty()) {
                Toast.makeText(this, "Prašau įvesti pavadinimą", Toast.LENGTH_SHORT).show();
                return;
            }
            if (svoris.getText().toString().isEmpty()) {
                Toast.makeText(this, "Prašau įvesti svorį", Toast.LENGTH_SHORT).show();
                return;
            }
            String svoris_str = svoris.getText().toString();
            if (!svoris_str.matches("(\\d+,?)+")) {
                Toast.makeText(this, "Svoris turi būti skaičius", Toast.LENGTH_SHORT).show();
                return;
            }
            if(svoris_str.contains(",")){
                String[] svoris_array = svoris_str.split(",");
                for(String s : svoris_array) {
                    if (!s.matches("\\d+")) {
                        Toast.makeText(this, "Svoris turi būti skaičius", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (lytisM.isChecked()) {

                        if (rankaK.isChecked()) {
                            Grupe newGrupe = new Grupe();
                            newGrupe.setPavadinimas(grupes_pavad.getText().toString());
                            newGrupe.setSvoris(s);
                            newGrupe.setLytis("Vyrai");
                            newGrupe.setRanka("Kairė");
                            pogrupisList.add(newGrupe);
                            groupAdapter.notifyItemInserted(pogrupisList.size() - 1);
                        }
                        if (rankaD.isChecked()) {
                            Grupe newGrupe = new Grupe();
                            newGrupe.setPavadinimas(grupes_pavad.getText().toString());
                            newGrupe.setSvoris(s);
                            newGrupe.setLytis("Vyrai");
                            newGrupe.setRanka("Dešinė");
                            pogrupisList.add(newGrupe);
                            groupAdapter.notifyItemInserted(pogrupisList.size() - 1);
                        }
                    }
                    if (lytisF.isChecked()) {
                        if (rankaK.isChecked()) {
                            Grupe newGrupe = new Grupe();
                            newGrupe.setPavadinimas(grupes_pavad.getText().toString());
                            newGrupe.setSvoris(s);
                            newGrupe.setLytis("Moterys");
                            newGrupe.setRanka("Kairė");
                            pogrupisList.add(newGrupe);
                            groupAdapter.notifyItemInserted(pogrupisList.size() - 1);
                        }
                        if (rankaD.isChecked()) {
                            Grupe newGrupe = new Grupe();
                            newGrupe.setPavadinimas(grupes_pavad.getText().toString());
                            newGrupe.setSvoris(s);
                            newGrupe.setLytis("Moterys");
                            newGrupe.setRanka("Dešinė");
                            pogrupisList.add(newGrupe);
                            groupAdapter.notifyItemInserted(pogrupisList.size() - 1);
                        }
                    }
                }
            }

            // Clear the input fields
            grupes_pavad.setText("");
            svoris.setText("");
            metai.setText("");
            lytisM.setChecked(false);
            lytisF.setChecked(false);
            rankaD.setChecked(false);
            rankaK.setChecked(false);
        });

        submitButton.setOnClickListener(v -> {
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
        });
    }

    private void sendPostRequest(Varzybos varzybos) {
        Retrofit_Pre retrofit_pre = new Retrofit_Pre(this);
        Retrofit retrofit = retrofit_pre.getRetrofit(true);

        TournamentRoute championshipRoute = retrofit.create(TournamentRoute.class);
        RequestBody partData = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(varzybos));
        MultipartBody.Part filePart = null;
        if(imageBitmap != null) {
            RequestBody fileData = RequestBody.create(MediaType.parse("image/*"), imageBitmap);
            filePart = MultipartBody.Part.createFormData("file", "image.jpg", fileData);
        }


        Call<String> call = championshipRoute.createTournament(partData, filePart);
        Request request = call.request();
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            try {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                String requestBodyString = buffer.readUtf8();
                System.out.println("Request Body"+ requestBodyString);
            } catch (IOException e) {
                System.out.println("Request Body "+ "Error reading request body"+e.toString());
            }
        }
        for (int i = 0; i < request.headers().size(); i++) {
            System.out.println("Request headers"+request.headers().name(i)+":"+request.headers().value(i));
        }
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("Response"+response.code());
                if (response.isSuccessful()) {
                    finish();
                } else {
                    System.out.println("Response"+response.message());
                    Toast.makeText(RegisterPogrupis.this, "Klaida kuriant turnyrą"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }

        });
    }
}