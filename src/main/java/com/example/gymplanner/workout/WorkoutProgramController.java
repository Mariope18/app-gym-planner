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

    // Per la modifica del nome, ci basta ricevere una semplice stringa nel corpo della richiesta
    @PutMapping("/{programId}")
    public ResponseEntity<WorkoutProgram> updateProgramName(
            @PathVariable Long programId,
            @RequestBody String newName,
            @AuthenticationPrincipal User user) {

        return programService.updateProgramName(programId, newName, user)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Aggiungi un esercizio a un programma specifico
    @PostMapping("/{workoutProgramId}/entries")
    public ResponseEntity<ProgramEntry> addEntryToProgram(@PathVariable Long workoutProgramId,
                                                  @AuthenticationPrincipal User user,
                                                  @RequestBody AddEntryRequest entryRequest){
        ProgramEntry programEntry = programService.addEntryToProgram(workoutProgramId,user,entryRequest);

        if (programEntry != null){
            return new ResponseEntity<>(programEntry,HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/entries/{entryId}")
    public ResponseEntity<Void> deleteProgramEntry(@PathVariable Long entryId, @AuthenticationPrincipal User user) {
        boolean isDeleted = programService.deleteProgramEntry(entryId, user);
        if (isDeleted) {
            // 204 No Content è la risposta standard per una cancellazione riuscita
            return ResponseEntity.noContent().build();
        } else {
            // 404 Not Found se l'entry non esiste o l'utente non è il proprietario
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/entries/{entryId}")
    public ResponseEntity<ProgramEntry> updateProgramEntry(
            @PathVariable Long entryId,
            @RequestBody UpdateEntryRequest request,
            @AuthenticationPrincipal User user) {

        return programService.updateProgramEntry(entryId, request, user)
                .map(ResponseEntity::ok) // Se l'update ha successo, rispondi 200 OK con l'oggetto aggiornato
                .orElseGet(() -> ResponseEntity.notFound().build()); // Altrimenti 404 Not Found
    }
}