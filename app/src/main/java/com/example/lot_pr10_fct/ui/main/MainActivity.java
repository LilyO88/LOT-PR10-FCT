package com.example.lot_pr10_fct.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.lot_pr10_fct.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = ActivityCompat.requireViewById(this, R.id.navigationView);
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
//        AppBarConfiguration appbarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(navView, navController);
    }
}
