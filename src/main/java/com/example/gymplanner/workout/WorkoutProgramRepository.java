package com.example.gymplanner.workout;

import com.example.gymplanner.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram,Long> {

    List<WorkoutProgram> findByUser(User user);

    Optional<WorkoutProgram> findByIdAndUser(Long id, User user);
}
