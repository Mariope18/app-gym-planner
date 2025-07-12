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
}
