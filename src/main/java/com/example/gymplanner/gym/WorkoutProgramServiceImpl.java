package com.example.gymplanner.gym;

import com.example.gymplanner.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutProgramServiceImpl implements WorkoutProgramService{

    @Autowired
    private WorkoutProgramRepository workoutProgramRepository;

    @Override
    public WorkoutProgram createProgram(WorkoutProgram program, User user) {
        program.setUser(user);
        return workoutProgramRepository.save(program);
    }

    public List<WorkoutProgram> findProgramsByUser(User user){
        return workoutProgramRepository.findByUser(user);
    }

    public Optional<WorkoutProgram> findProgramByIdAndUser(Long id, User user){
        return workoutProgramRepository.findByIdAndUser(id,user);
    }

    @Override
    public boolean deleteProgram(Long id, User user) {
        Optional<WorkoutProgram> workoutProgramOptional = workoutProgramRepository.findByIdAndUser(id,user);
        if (!workoutProgramOptional.isEmpty()){
            workoutProgramRepository.delete(workoutProgramOptional.get());
            return true;
        }
        return false;
    }
}
