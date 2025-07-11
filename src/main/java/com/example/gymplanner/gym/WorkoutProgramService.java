package com.example.gymplanner.gym;

import com.example.gymplanner.user.User;
import java.util.List;
import java.util.Optional;

public interface WorkoutProgramService {
    // Crea un nuovo programma per un utente
    WorkoutProgram createProgram(WorkoutProgram program, User user);

    // Trova tutti i programmi di un utente
    List<WorkoutProgram> findProgramsByUser(User user);

    // Trova un programma specifico per ID e utente
    Optional<WorkoutProgram> findProgramByIdAndUser(Long id, User user);

    // Cancella un programma di un utente
    boolean deleteProgram(Long id, User user);
}