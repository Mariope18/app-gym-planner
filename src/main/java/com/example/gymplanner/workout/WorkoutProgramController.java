package com.example.gymplanner.workout;

import com.example.gymplanner.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class WorkoutProgramController {

    @Autowired
    WorkoutProgramService programService;

    @PostMapping
    public ResponseEntity<WorkoutProgram> createProgram(@RequestBody WorkoutProgram workoutProgram,
                                                        @AuthenticationPrincipal User user){
        return new ResponseEntity<>(programService.createProgram(workoutProgram,user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutProgram>> findProgramsByUser(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(programService.findProgramsByUser(user));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<WorkoutProgram> findProgramByIdAndUser(@PathVariable Long id, @AuthenticationPrincipal User user){
        return programService.findProgramByIdAndUser(id,user)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id, @AuthenticationPrincipal User user){
        boolean isDeleted = programService.deleteProgram(id,user);
        if (isDeleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}