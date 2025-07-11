package com.example.gymplanner.admin;

import com.example.gymplanner.dto.ExerciseDto;
import com.example.gymplanner.gym.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/exercises")
public class ExerciseController {

    @Autowired
    private  ExerciseServiceImpl exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getAllExercise(){
        // 1. Ottieni la lista delle entità dal service
        List<Exercise> exercises = exerciseService.findAllExercise();

        // 2. Converti la lista di Exercise in una lista di ExerciseDto
        List<ExerciseDto> exerciseDtos = exercises.stream()
                .map(exercise -> new ExerciseDto(
                        exercise.getId(),
                        exercise.getName(),
                        exercise.getDescription(),
                        exercise.getMuscleGroup()
                ))
                .toList();

        return ResponseEntity.ok(exerciseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable Long id){
        return exerciseService.findById(id)// 1. Ottieni l'Optional<Exercise>
                .map(exercise -> {// 2. Se l'Optional è pieno, esegui questa trasformazione...
                            // ...crea un DTO a partire dall'entità...
                            ExerciseDto dto = new ExerciseDto(
                                    exercise.getId(),
                                    exercise.getName(),
                                    exercise.getDescription(),
                                    exercise.getMuscleGroup()
                            );
                            // ...e restituisci un ResponseEntity 200 OK con il DTO.
                            return ResponseEntity.ok(dto);
                        })// 3. Il risultato del .map() è un Optional<ResponseEntity<ExerciseDto>>
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        return new ResponseEntity<>(exerciseService.createExercise(exercise), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise exerciseNew){
        return exerciseService.updateExercise(id,exerciseNew)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id){
        // Chiamiamo il nostro nuovo metodo del service che restituisce un booleano
        boolean isDeleted = exerciseService.deleteExercise(id);

        // Se è stato cancellato con successo...
        if (isDeleted) {
            // ...restituisci 204 No Content.
            return ResponseEntity.noContent().build();
        } else {
            // ...altrimenti, restituisci 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }
}
