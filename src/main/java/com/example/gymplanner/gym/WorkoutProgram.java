package com.example.gymplanner.gym;

import com.example.gymplanner.user.User;
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

    @OneToMany(mappedBy = "workoutProgram")
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
}
