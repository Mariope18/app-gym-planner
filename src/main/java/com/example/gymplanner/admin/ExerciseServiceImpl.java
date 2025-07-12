package com.example.gymplanner.admin;

import com.example.gymplanner.gym.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements  ExerciseService{

    @Autowired
    ExerciseRepository exerciseRepository;

    public List<Exercise> findAllExercise() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> findById(Long id) {
        return exerciseRepository.findById(id);
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Optional<Exercise> updateExercise(Long id, Exercise exerciseNew) {
        return exerciseRepository.findById(id)
                .map(exercise -> {
                    if(exerciseNew.getName() != null){
                        exercise.setName(exerciseNew.getName());
                    }
                    if (exerciseNew.getDescription() != null) {
                        exercise.setDescription(exerciseNew.getDescription());
                    }
                    if (exerciseNew.getMuscleGroup() != null) {
                        exercise.setMuscleGroup(exerciseNew.getMuscleGroup());
                    }
                    return exerciseRepository.save(exercise);
                });
    }

    public boolean deleteExercise(Long id) {
        // Cerchiamo l'esercizio per ID
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);

        // Se non viene trovato, restituiamo false
        if (exerciseOptional.isEmpty()) {
            return false;
        }

        // Se viene trovato, lo cancelliamo e restituiamo true
        exerciseRepository.delete(exerciseOptional.get());
        return true;
    }

}
