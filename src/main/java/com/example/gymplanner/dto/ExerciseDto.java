// ExerciseDto.java
package com.example.gymplanner.dto;

import com.example.gymplanner.gym.MuscleGroup;

public class ExerciseDto {
    private Long id;
    private String name;
    private String description;
    private MuscleGroup muscleGroup;

    // Costruttore, Getters e Setters...

    // Un costruttore utile per la conversione
    public ExerciseDto(Long id, String name, String description, MuscleGroup muscleGroup) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public MuscleGroup getMuscleGroup() { return muscleGroup; }
}