package com.example.gymplanner.workout;

import com.example.gymplanner.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
//Rappresenta la "scheda" di un utente
public class WorkoutProgram {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "workoutProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramEntry> programEntries;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProgramEntry> getProgramEntries() {
        return programEntries;
    }

    public void setProgramEntries(List<ProgramEntry> programEntries) {
        this.programEntries = programEntries;
    }

    public void addProgramEntry(ProgramEntry programEntry){
        this.programEntries.add(programEntry);
    }
}
