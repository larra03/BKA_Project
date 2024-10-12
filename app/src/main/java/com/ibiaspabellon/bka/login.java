package com.ibiaspabellon.bka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ibiaspabellon.bka.databinding.ActivityLoginBinding;

public class login extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply window insets for padding adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set an OnClickListener on the login button
        binding.login.setOnClickListener(v -> {

            binding.fragmentContainerViewTag.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view_tag, new fragment_login()) // Use the correct Fragment class
                    .commit();

        });
        binding.btnSignup.setOnClickListener(v -> {

            binding.fragmentContainerViewTag.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view_tag, new fragment_signup()) // Use the correct Fragment class
                    .commit();

        });

        // Optionally, ensure that the fragment container starts empty or is set to a default view.
        if (savedInstanceState == null) {
            // Optionally set a default view if needed (e.g., a placeholder view)
            // Otherwise, you can leave it empty to only show the Fragment when the button is clicked.
        }
    }
}
