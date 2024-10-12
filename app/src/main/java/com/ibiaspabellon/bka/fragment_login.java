package com.ibiaspabellon.bka;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ibiaspabellon.bka.databinding.ActivityMainBinding;
import com.ibiaspabellon.bka.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_login.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_login newInstance(String param1, String param2) {
        fragment_login fragment = new fragment_login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize binding
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();

        // Set up button click listener
        binding.btnToApp.setOnClickListener(v -> {
            // Get email and password from EditText fields
            String email = binding.etLoginEmail.getText().toString().trim();
            String password = binding.etLoginPassword.getText().toString().trim();

            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show();
            } else {
                // Confirm login details with Firebase
                confirmLogin(email, password);
            }
        });

        // Return the root view of the binding (which contains the fragment's layout)
        return binding.getRoot();
    }

    // Method to confirm login details with Firebase
    private void confirmLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Login successful
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Navigate to home page
                            Intent intent = new Intent(getActivity(), homePage.class);
                            startActivity(intent);
                            getActivity().finish(); // Optional: Close login activity
                        }
                    } else {
                        // Login failed
                        Toast.makeText(getContext(), "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
