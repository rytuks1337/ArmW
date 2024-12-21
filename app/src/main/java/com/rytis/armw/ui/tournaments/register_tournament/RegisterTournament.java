package com.rytis.armw.ui.tournaments.register_tournament;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.models.Varzybos;
import com.rytis.armw.routes.TournamentRoute;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterTournament extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 445;
    private Varzybos varzybos;
    private ImageView tournamentImageView;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tournament);

        EditText pavadinimas = findViewById(R.id.nameEditText);
        EditText data = findViewById(R.id.dateEditText);
        EditText pradzia = findViewById(R.id.startTimeEditText);
        EditText pabaiga = findViewById(R.id.endTimeEditText);
        EditText lokacija = findViewById(R.id.locationEditText);
        EditText stalu_sk = findViewById(R.id.tablesEditText);
        EditText aprasas = findViewById(R.id.descriptionEditText);
        Button uploadImageButton = findViewById(R.id.uploadImageButton);
        tournamentImageView = findViewById(R.id.tournamentImageView);



        Button kitas = findViewById(R.id.nextButton);
        kitas.setOnClickListener(v -> {
            try {
                Varzybos varzybos = new Varzybos();
                varzybos.setPavadinimas(pavadinimas.getText().toString());
                varzybos.setData(data.getText().toString());
                varzybos.setPradzia(pradzia.getText().toString());
                varzybos.setPabaiga(pabaiga.getText().toString());
                varzybos.setLokacija(lokacija.getText().toString());
                varzybos.setStalu_sk(Integer.parseInt(stalu_sk.getText().toString()));
                varzybos.setAprasas(aprasas.getText().toString());

                // Send the POST request
                sendPostRequest(varzybos);
                finish();
            }catch (Exception e){
                Toast.makeText(RegisterTournament.this, e.toString(),Toast.LENGTH_SHORT).show();
            }

        });
        pradzia.setOnClickListener(v -> {
            // Get current time
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            // Create and show TimePickerDialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(RegisterTournament.this,
                    (view, hourOfDay, minute1) -> {
                        // Format the selected time
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);

                        // Update the TextInputEditText
                        pradzia.setText(selectedTime);
                    }, hour, minute, true); // true for 24-hour format
            timePickerDialog.show();
        });
        pabaiga.setOnClickListener(v -> {
            // Get current time
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create and show TimePickerDialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(RegisterTournament.this,
                    (view, hourOfDay, minute1) -> {
                        // Format the selected time
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);

                        // Update the TextInputEditText
                        pabaiga.setText(selectedTime);
                    }, hour, minute, true); // true for 24-hour format
            timePickerDialog.show();
        });
        data.setOnClickListener(v -> {
            // Get current date
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create and show DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterTournament.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        // Format the selected date
                        String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year1, monthOfYear + 1, dayOfMonth);
                        // Update the TextInputEditText
                        data.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });
        uploadImageButton.setOnClickListener(v -> {
            // Open image picker or camera intent
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                tournamentImageView.setImageBitmap(bitmap);
                imageBitmap = bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void sendPostRequest(Varzybos varzybos) {
        Retrofit_Pre retrofit_pre = new Retrofit_Pre(this);
        Retrofit retrofit = retrofit_pre.getRetrofit(true);

        TournamentRoute championshipRoute = retrofit.create(TournamentRoute.class);
        RequestBody partData = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(varzybos));
        MultipartBody.Part filePart = null;
        if(imageBitmap != null) {

            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byte[] byteArray = bStream.toByteArray();

            RequestBody fileData = RequestBody.create(MediaType.parse("image/*"), byteArray);
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
                    Toast.makeText(RegisterTournament.this, "Klaida kuriant turnyrą"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }

        });
    }


}