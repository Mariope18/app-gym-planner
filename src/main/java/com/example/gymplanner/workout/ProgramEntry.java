package com.example.gymplanner.workout;

import com.example.gymplanner.exercise.Exercise;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
//Rappresenta una singola riga nella scheda di allenamento
public class ProgramEntry {

    @Id
    @GeneratedValue
    private Long id;

    private int sets;
    private String reps;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "workoutProgram_id")
    @JsonBackReference
    private WorkoutProgram workoutProgram;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public WorkoutProgram getWorkoutProgram() {
        return workoutProgram;
    }

    public void setWorkoutProgram(WorkoutProgram workoutProgram) {
        this.workoutProgram = workoutProgram;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
