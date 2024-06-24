package com.rytis.armw.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.databinding.ActivityLoginScreenBinding;
import com.rytis.armw.routes.AuthenticationRoute;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginScreen extends AppCompatActivity {

    ActivityLoginScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retro = new Retrofit.Builder().baseUrl("http://10.107.0.155:3000").addConverterFactory(GsonConverterFactory.create()).build();

                String email = binding.emailSign.getText().toString();
                String pass = binding.passwordSign.getText().toString();
                AuthenticationRoute loginUser = retro.create(AuthenticationRoute.class);



                loginUser.postLoginUser(new UserloginModel.UserLoginData(email,pass)).enqueue(new Callback<UserloginModel.UserLoginDataResp>() {
                    @Override
                    public void onResponse(Call<UserloginModel.UserLoginDataResp> call, Response<UserloginModel.UserLoginDataResp> response) {
                        assert response.body() != null;
                        if(response.body().accessToken != null) {
                            TokenManager.saveJwtToken(LoginScreen.this, response.body().accessToken);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserloginModel.UserLoginDataResp> call, Throwable t) {

                    }
                });
            }
        });
    }

}