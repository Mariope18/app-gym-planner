package com.example.gymplanner.exercise;

import com.example.gymplanner.workout.ProgramEntry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
//Rappresenta un singolo esercizio disponibile
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING) // Salva il ruolo come stringa ("USER", "ADMIN") nel DB
    private MuscleGroup muscleGroup;

    @OneToMany(mappedBy = "exercise")
    @JsonIgnore
    private List<ProgramEntry> programEntries;

    // Costruttore vuoto per JPA/Hibernate
    public Exercise() {
    }

    public Exercise(String name, String description, MuscleGroup muscleGroup) {
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public List<ProgramEntry> getProgramEntries() {
        return programEntries;
    }

    public void setProgramEntries(List<ProgramEntry> programEntries) {
        this.programEntries = programEntries;
    }
}
