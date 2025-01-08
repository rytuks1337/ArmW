package com.rytis.armw.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.dataModels.UserRegisterModel;
import com.rytis.armw.databinding.ActivityRegisterScreenBinding;
import com.rytis.armw.routes.AuthenticationRoute;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterScreen extends AppCompatActivity {

    ActivityRegisterScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterScreenBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());
        binding.regRegister.setOnClickListener(v -> {
            Retrofit_Pre retro_p = new Retrofit_Pre(binding.getRoot().getContext());
            Retrofit retro = retro_p.getRetrofit(false);
            DatePicker datePicker = binding.regBirthday;
            int year = datePicker.getYear();
            int month = datePicker.getMonth(); // Month is 0-indexed (0 = January)
            int dayOfMonth = datePicker.getDayOfMonth();

            // Format the date using SimpleDateFormat
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);

            String name = binding.regName.getText().toString();
            String surname = binding.regSurname.getText().toString();
            String age = dateFormat.format(calendar.getTime());
            String email = binding.regMail.getText().toString();
            String password = binding.regPass.getText().toString();
            String gender ="";
            if (binding.regMale.isChecked()) {
                gender = "V";
            } else if (binding.regFemale.isChecked()) {
                gender = "M";
            }
            AuthenticationRoute registerUser = retro.create(AuthenticationRoute.class);


            registerUser.postRegisterUser(new UserRegisterModel.UserRegisterData(name,surname, email, password, age, gender)).enqueue(new Callback<UserRegisterModel.UserRegisterModelResp>() {
                @Override
                public void onResponse(Call<UserRegisterModel.UserRegisterModelResp> call, Response<UserRegisterModel.UserRegisterModelResp> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RegisterScreen.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        finish();
                        // Handle successful response
                    } else {
                        // Handle error based on HTTP status code
                        switch (response.code()) {
                            case 400:
                                Toast.makeText(RegisterScreen.this, "Invalid request", Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                Toast.makeText(RegisterScreen.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                                // Internal Server Error
                                break;
                            // ... handle other error codes ...
                            default:
                                Toast.makeText(RegisterScreen.this, "Internal error", Toast.LENGTH_SHORT).show();
                                // Handle unknown error
                                break;
                        }
                    }

                }

                @Override
                public void onFailure(Call<UserRegisterModel.UserRegisterModelResp> call, Throwable t) {
                    Toast.makeText(RegisterScreen.this, "Registration failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        });

    }
    private void handleHttpError(Response<UserRegisterModel.UserRegisterModelResp> response) {
        int statusCode = response.code();
        switch (statusCode) {
            case 400:
                // Handle Bad Request
                binding.regError.setText("Error 400, bad request");
                break;
            case 401:
                // Handle Unauthorized
                System.err.println("Unauthorized: " + response.message());
                break;
            case 403:
                // Handle Forbidden
                System.err.println("Forbidden: " + response.message());
                break;
            case 404:
                // Handle Not Found
                System.err.println("Not Found: " + response.message());
                break;
            // Add more cases as needed for other status codes
            default:
                System.err.println("Error: " + response.message());
                break;
        }
    }
}
