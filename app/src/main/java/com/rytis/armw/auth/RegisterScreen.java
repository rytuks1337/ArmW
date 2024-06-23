package com.rytis.armw.auth;

import android.os.Bundle;
import android.view.View;

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
        binding.regRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit_Pre retro_p = new Retrofit_Pre();
                Retrofit retro = retro_p.getRetrofit();

                String name = binding.regName.getText().toString();
                String surname = binding.regSurname.getText().toString();
                Integer age = Integer.valueOf(binding.regAge.getText().toString());
                String email = binding.regMail.getText().toString();
                String password = binding.regPass.getText().toString();
                AuthenticationRoute registerUser = retro.create(AuthenticationRoute.class);



                registerUser.postRegisterUser(new UserRegisterModel.UserRegisterData(name,surname, email, password, age)).enqueue(new Callback<UserRegisterModel.UserRegisterModelResp>() {
                    @Override
                    public void onResponse(Call<UserRegisterModel.UserRegisterModelResp> call, Response<UserRegisterModel.UserRegisterModelResp> response) {
                        if (response.isSuccessful() && response.body() != null) {

                        } else {

                        }

                    }

                    @Override
                    public void onFailure(Call<UserRegisterModel.UserRegisterModelResp> call, Throwable t) {
                        System.err.println("Registration failed: " + t.getMessage());
                        t.printStackTrace();
                    }
                });
            }


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
