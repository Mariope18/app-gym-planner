package com.example.gymplanner.auth;

// Semplice classe per mappare il JSON della richiesta di registrazione
// In RegisterRequest.java

public class RegisterRequest {
    private String username;
    private String password;
    // AGGIUNGI QUESTI
    private String firstName;
    private String lastName;
    private String email;

    // Getters e Setters per tutti i campi...
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}