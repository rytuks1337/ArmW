package com.rytis.armw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rytis.armw.auth.LoginScreen;
import com.rytis.armw.auth.TokenManager;
import com.rytis.armw.auth.OnLoginSuccessListener;
import com.rytis.armw.databinding.ActivityMainBinding;
import com.rytis.armw.ui.tournaments.RegisterTournament;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnLoginSuccessListener {

    private static final int REQUEST_CODE_LOGIN = 70;
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
        updateBottom();


    }


    public void updateBottom(){
        BottomNavigationView navView = binding.navView;
        if (TokenManager.getJwtToken(MainActivity.this) != null) {
            navController.setGraph(R.navigation.mobile_navigation_auth);


        }

    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LOGIN && resultCode == RESULT_OK) {
            String accessToken = Objects.requireNonNull(data.getExtras()).getString("accessToken");
            TokenManager.saveJwtToken(MainActivity.this, accessToken);
            updateBottom();
        }
    }


}