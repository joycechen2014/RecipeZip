package com.test.recipezip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.test.recipezip.model.RecipeResponse;
import com.test.recipezip.network.RecipeApi;
import com.test.recipezip.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
//    private static final String API_ID = "5f5adea8";
//    private static final String API_KEY ="90db22d451899254bdaca3ede182a0c9";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        int userId = getIntent().getIntExtra("uid", -1);
               BottomNavigationView navView = findViewById(R.id.nav_view);
               NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.nav_host_fragment);
               navController = navHostFragment.getNavController();
               NavigationUI.setupWithNavController(navView, navController);
               NavigationUI.setupActionBarWithNavController(this, navController);
    }

   @Override
   public boolean onSupportNavigateUp() {
               return navController.navigateUp();
           }
}