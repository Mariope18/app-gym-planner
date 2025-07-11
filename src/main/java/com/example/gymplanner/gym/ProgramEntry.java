package com.example.gymplanner.gym;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProgramEntry {

    @Id
    @GeneratedValue
    private Long id;

    private int sets;
    private String reps;
    private String notes;


}
