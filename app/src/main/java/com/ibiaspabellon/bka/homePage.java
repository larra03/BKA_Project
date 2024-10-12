package com.ibiaspabellon.bka;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class homePage extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawer_layout);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the drawer toggle
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // You can handle drawer sliding animation here if needed
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                // Handle drawer opened if needed
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // Handle drawer closed if needed
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Handle drawer state changes if needed
            }
        });
    }
}
