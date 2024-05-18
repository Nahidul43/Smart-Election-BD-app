package com.example.smartelectionmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Use a handler to delay the start of the main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent intent = new Intent(MainActivity.this, Splash.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}



