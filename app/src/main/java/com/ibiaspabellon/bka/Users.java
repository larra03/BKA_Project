package com.ibiaspabellon.bka;

public class Users {

    private String fullName;
    private String email;
    private String password;

    // Default constructor required for Firebase Database
    public Users() {
        // Required for Firebase Realtime Database
    }

    // Constructor with parameters
    public Users(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password; // Initialize the password
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password; // Note: Be cautious with password exposure
    }

    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
