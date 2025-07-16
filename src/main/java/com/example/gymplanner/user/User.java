package com.example.gymplanner.user;

import com.example.gymplanner.workout.WorkoutProgram;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "utente")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING) // Salva il ruolo come stringa ("USER", "ADMIN") nel DB
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<WorkoutProgram> workoutPrograms;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Controlla se il ruolo è nullo per sicurezza
        if (role == null) {
            return Collections.emptyList();
        }
        // Crea una lista contenente un singolo permesso basato sul nostro Enum Role
        // Es: se role è ADMIN, crea un'autorità "ROLE_ADMIN"
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonIgnore
    public List<WorkoutProgram> getWorkoutPrograms() {
        return workoutPrograms;
    }

    public void setWorkoutPrograms(List<WorkoutProgram> workoutPrograms) {
        this.workoutPrograms = workoutPrograms;
    }
}
