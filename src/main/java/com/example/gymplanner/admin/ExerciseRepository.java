package com.example.gymplanner.admin;

import com.example.gymplanner.gym.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

}
