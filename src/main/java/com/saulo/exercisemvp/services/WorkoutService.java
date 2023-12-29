package com.saulo.exercisemvp.services;

import com.saulo.exercisemvp.dto.WorkoutDTO;

import java.util.List;

public interface WorkoutService {
    List<WorkoutDTO> getAllWorkout();
    WorkoutDTO createWorkout(WorkoutDTO workoutDTO);
    WorkoutDTO getWorkoutById(int workoutId);
    WorkoutDTO updateWorkout(int workoutId, WorkoutDTO workoutDTO);
    void deleteWorkoutById(int workoutId);

}
