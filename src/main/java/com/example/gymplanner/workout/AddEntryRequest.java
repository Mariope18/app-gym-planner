package com.example.gymplanner.workout;

import com.example.gymplanner.exercise.Exercise;

public class AddEntryRequest {

    private Long exerciseId;
    private int sets;
    private String reps;
    private String notes;

    public AddEntryRequest(Long exerciseId, int sets, String reps, String notes) {
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.notes = notes;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
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
}
