package com.example.gymplanner.workout;

public class UpdateEntryRequest {
    private int sets;
    private String reps;
    private String notes;

    // Getters e Setters
    public int getSets() { return sets; }
    public void setSets(int sets) { this.sets = sets; }
    public String getReps() { return reps; }
    public void setReps(String reps) { this.reps = reps; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}