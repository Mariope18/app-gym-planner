package com.example.gymplanner.gym;

import jakarta.persistence.*;

@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING) // Salva il ruolo come stringa ("USER", "ADMIN") nel DB
    private MuscleGroup muscleGroup;
}
