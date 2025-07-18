package com.example.gymplanner.exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    List<Exercise> findAllExercise();
    Optional<Exercise> findById(Long id);
    Exercise createExercise(Exercise exercise);
    Optional<Exercise> updateExercise(Long id, Exercise exerciseNew);
    boolean deleteExercise(Long id);
}