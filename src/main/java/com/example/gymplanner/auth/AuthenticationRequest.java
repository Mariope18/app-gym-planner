package com.example.gymplanner.auth;

// Semplice classe per mappare il JSON della richiesta di login
public class AuthenticationRequest {

    private String username;
    private String password;

    // Getters e Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}