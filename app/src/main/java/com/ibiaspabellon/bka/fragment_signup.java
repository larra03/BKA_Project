package com.ibiaspabellon.bka;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ibiaspabellon.bka.databinding.FragmentSignupBinding;

public class fragment_signup extends Fragment {

    private FragmentSignupBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Firebase Database instance
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        // Set an OnClickListener for the Create Account button
        binding.btnCreateAcc.setOnClickListener(v -> {
            // Get values from EditText fields
            String fullName = binding.etFullName.getText().toString().trim();
            String email = binding.etSignUpEmail.getText().toString().trim();
            String password = binding.etSignUpCreatePassword.getText().toString().trim();
            String confirmPassword = binding.etSignUpConfirmPassword.getText().toString().trim();  // Confirm password input

            // Validate the input data
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all the fields.", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(getContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();  // Check if passwords match
            } else {
                // Create a user with FirebaseAuth if validation passes
                createFirebaseUser(fullName, email, password);
            }
        });

        return view;
    }

    // Method to create user in FirebaseAuth and store data in Firebase Realtime Database
    private void createFirebaseUser(String fullName, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // User successfully created in FirebaseAuth
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();

                        if (firebaseUser != null) {
                            // Get the unique Firebase user ID
                            String userId = firebaseUser.getUid();

                            // Store the user data in Firebase Realtime Database under the unique user ID
                            databaseReference.child(userId).child("fullName").setValue(fullName);
                            databaseReference.child(userId).child("email").setValue(email)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(getContext(), "User created successfully!", Toast.LENGTH_SHORT).show();
                                            // Optionally navigate to another fragment/activity
                                        } else {
                                            Toast.makeText(getContext(), "Failed to store user data.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        // Failed to create user in FirebaseAuth
                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                        handleSignUpError(errorCode);
                    }
                });
    }

    private void handleSignUpError(String errorCode) {
        switch (errorCode) {
            case "ERROR_INVALID_EMAIL":
                Toast.makeText(getContext(), "The email address is badly formatted.", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(getContext(), "The password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(getContext(), "The email address is already in use by another account.", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getContext(), "Registration failed: " + errorCode, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
