package com.example.gymplanner.admin;

import com.example.gymplanner.gym.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/exercises")
public class ExerciseController {

    @Autowired
    private  ExerciseServiceImpl exerciseService;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercise(){
        return ResponseEntity.ok(exerciseService.findAllExercise());
    }

    @GetMapping("{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id){
        return exerciseService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        return new ResponseEntity<>(exerciseService.createExercise(exercise), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
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
