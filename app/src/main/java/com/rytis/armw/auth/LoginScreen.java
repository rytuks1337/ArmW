package com.rytis.armw.auth;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.rytis.armw.R;
import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.databinding.ActivityLoginScreenBinding;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.routes.AuthenticationRoute;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    private static final String TAG = "LoginScreen";
    private ActivityLoginScreenBinding binding;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        loading = findViewById(R.id.loading);

        Animation buttonClick = AnimationUtils.loadAnimation(LoginScreen.this, R.anim.button_click);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.login.startAnimation(buttonClick);

                performLogin();

            }
        });
    }

    private void performLogin() {
        String email = binding.emailSign.getText().toString();
        String pass = binding.passwordSign.getText().toString();

        Retrofit_Pre pre_retro = new Retrofit_Pre();
        AuthenticationRoute loginUser = pre_retro.getRetrofit().create(AuthenticationRoute.class);

        loading.setVisibility(View.VISIBLE);

        loginUser.postLoginUser(new UserloginModel.UserLoginData(email, pass)).enqueue(new Callback<UserloginModel.UserLoginDataResp>() {
            @Override
            public void onResponse(Call<UserloginModel.UserLoginDataResp> call, Response<UserloginModel.UserLoginDataResp> response) {
                loading.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    String accessToken = response.body().accessToken;
                    if (accessToken != null) {
                        Toast.makeText(LoginScreen.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("accessToken", accessToken);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(LoginScreen.this, "Login Failed: Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginScreen.this, "Failure: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserloginModel.UserLoginDataResp> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Log.e(TAG, "Failure: " + t.getMessage());
                Toast.makeText(LoginScreen.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
