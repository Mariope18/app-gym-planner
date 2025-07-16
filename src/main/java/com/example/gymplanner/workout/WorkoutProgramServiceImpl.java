package com.example.gymplanner.workout;

import com.example.gymplanner.exercise.Exercise;
import com.example.gymplanner.exercise.ExerciseRepository;
import com.example.gymplanner.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutProgramServiceImpl implements WorkoutProgramService{

    @Autowired
    private WorkoutProgramRepository workoutProgramRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ProgramEntryRepository programEntryRepository;

    @Override
    @Transactional
    public WorkoutProgram createProgram(WorkoutProgram program, User user) {
        program.setUser(user);
        return workoutProgramRepository.save(program);
    }

    @Override
    public List<WorkoutProgram> findProgramsByUser(User user){
        return workoutProgramRepository.findByUser(user);
    }

    @Override
    public Optional<WorkoutProgram> findProgramByIdAndUser(Long id, User user){
        return workoutProgramRepository.findByIdAndUser(id,user);
    }

    @Override
    @Transactional
    public boolean deleteProgram(Long id, User user) {
        Optional<WorkoutProgram> workoutProgramOptional = workoutProgramRepository.findByIdAndUser(id,user);
        if (!workoutProgramOptional.isEmpty()){
            workoutProgramRepository.delete(workoutProgramOptional.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Optional<WorkoutProgram> updateProgramName(Long programId, String newName, User user) {
        // Troviamo il programma da modificare
        Optional<WorkoutProgram> programOptional = workoutProgramRepository.findByIdAndUser(programId, user);

        // Se esiste e appartiene all'utente, aggiorniamo il nome e salviamo
        return programOptional.map(program -> {
            program.setName(newName);
            return workoutProgramRepository.save(program);
        });
    }

    @Override
    @Transactional // <-- 1. Aggiungi per la consistenza dei dati
    public ProgramEntry addEntryToProgram(Long workoutProgramId, User user, AddEntryRequest request) {

        // 2. Trova il programma (o lancia un'eccezione se non trovato/non tuo)
        WorkoutProgram program = workoutProgramRepository.findByIdAndUser(workoutProgramId, user)
                .orElseThrow(() -> new EntityNotFoundException("Programma di allenamento non trovato o non appartenente all'utente. ID: " + workoutProgramId));

        // 3. Trova l'esercizio (o lancia un'eccezione se non trovato)
        Exercise exercise = exerciseRepository.findById(request.getExerciseId())
                .orElseThrow(() -> new EntityNotFoundException("Esercizio non trovato con id: " + request.getExerciseId()));

        // 4. Crea e popola la nuova entità
        ProgramEntry programEntry = new ProgramEntry();
        programEntry.setWorkoutProgram(program);
        programEntry.setExercise(exercise);
        programEntry.setSets(request.getSets());
        programEntry.setReps(request.getReps());
        programEntry.setNotes(request.getNotes());

        // 5. Salva la nuova entità e restituiscila in un solo passaggio
        return programEntryRepository.save(programEntry);
    }

    @Override
    @Transactional
    public boolean deleteProgramEntry(Long entryId, User user) {
        // Troviamo l'entry da cancellare
        Optional<ProgramEntry> entryOptional = programEntryRepository.findById(entryId);

        if (entryOptional.isEmpty()) {
            return false; // L'entry non esiste
        }

        ProgramEntry entry = entryOptional.get();

        // Controlliamo che l'utente che fa la richiesta sia il proprietario
        // della scheda a cui l'entry appartiene. Questa è una misura di sicurezza.
        if (!entry.getWorkoutProgram().getUser().getId().equals(user.getId())) {
            return false; // Non autorizzato
        }

        programEntryRepository.delete(entry);
        return true;
    }

    @Override
    @Transactional
    public Optional<ProgramEntry> updateProgramEntry(Long entryId, UpdateEntryRequest request, User user) {
        Optional<ProgramEntry> entryOptional = programEntryRepository.findById(entryId);
        if (entryOptional.isEmpty()) {
            return Optional.empty(); // L'entry non esiste
        }

        ProgramEntry entry = entryOptional.get();

        // Controllo di sicurezza: l'utente deve essere il proprietario
        if (!entry.getWorkoutProgram().getUser().getId().equals(user.getId())) {
            return Optional.empty(); // Non autorizzato
        }

        // Aggiorniamo i campi
        entry.setSets(request.getSets());
        entry.setReps(request.getReps());
        entry.setNotes(request.getNotes());

        // Salviamo le modifiche e restituiamo l'entry aggiornata
        return Optional.of(programEntryRepository.save(entry));
    }
}
