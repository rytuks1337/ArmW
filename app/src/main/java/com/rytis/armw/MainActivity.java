package com.rytis.armw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.rytis.armw.auth.LoginScreen;
import com.rytis.armw.auth.TokenManager;
import com.rytis.armw.auth.OnActionSuccessListener;
import com.rytis.armw.databinding.ActivityMainBinding;
import com.rytis.armw.tournament.tournament_details_view;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnActionSuccessListener {

    private static final int REQUEST_CODE_LOGIN = 70;
    private ActivityMainBinding binding;
    private NavController navController;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            String accessToken = result.getData().getExtras().getString("accessToken");
            if(accessToken != null) {
                TokenManager.saveJwtToken(MainActivity.this, accessToken);
            }
            updateBottom();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //ActionBar used for animated top text
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.action_bar_top_item);
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because the array changes.
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
        updateBottom();


    }

    public void updateBottom(){
        BottomNavigationView navView = binding.navView;

        String token = TokenManager.getJwtToken(MainActivity.this);
        if (token != null) {
            if(TokenManager.isTokenValid(MainActivity.this)){
                System.out.println("Token is valid");
                navController.setGraph(R.navigation.mobile_navigation_auth);
            }else{
                System.out.println("Token is not valid");
                navController.setGraph(R.navigation.mobile_navigation);

            }
        }else{
            System.out.println("Token not found");
            navController.setGraph(R.navigation.mobile_navigation);
        }
        SharedPreferences sharedPreferences = getSharedPreferences("tournament", MODE_PRIVATE);
        String tournamentName = sharedPreferences.getString("tournament_name", null);
        if(tournamentName != null){
            TextView scrollingTitle = findViewById(R.id.animated_action_bar);
            scrollingTitle.setText(tournamentName);
            scrollingTitle.setSelected(true);
        }


    }

    public void getHome(){
        navController.navigate(R.id.navigation_home, null);
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(this, LoginScreen.class);
        startForResult.launch(intent);
    }

    @Override
    public void onChoiceSuccess(Intent i) {
        startForResult.launch(i);
    }


}