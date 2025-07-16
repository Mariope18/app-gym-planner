package com.example.gymplanner.workout;

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

    //Modifica noe di un programma
    Optional<WorkoutProgram> updateProgramName(Long programId, String newName, User user);

    ProgramEntry addEntryToProgram(Long workoutProgramId, User user, AddEntryRequest entryRequest);

    // Cancella un singolo pragramEntry
    boolean deleteProgramEntry(Long entryId, User user);

    // Modifica di un singolo pragramEntry
    Optional<ProgramEntry> updateProgramEntry(Long entryId, UpdateEntryRequest request, User user);

}