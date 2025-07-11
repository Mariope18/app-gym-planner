package com.example.gymplanner.gym;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class WorkoutProgram {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
