package com.saulo.exercisemvp.services.IMPL;

import com.saulo.exercisemvp.dto.ExerciseDTO;
import com.saulo.exercisemvp.dto.WorkoutDTO;
import com.saulo.exercisemvp.exceptions.WorkoutNotFoundException;
import com.saulo.exercisemvp.models.Workout;
import com.saulo.exercisemvp.repositories.WorkoutRepository;
import com.saulo.exercisemvp.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceIMPL implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    @Autowired
    public WorkoutServiceIMPL(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public List<WorkoutDTO> getAllWorkout() {
        return workoutRepository.findAll().stream().map(workout -> mapToDTO(workout)).collect(Collectors.toList());
    }

    @Override
    public WorkoutDTO createWorkout(WorkoutDTO workoutDTO) {
        return mapToDTO(workoutRepository.save(mapToEntity(workoutDTO)));
    }

    @Override
    public WorkoutDTO getWorkoutById(int workoutId) {
        return mapToDTO(workoutRepository.findById(workoutId).orElseThrow(()->new WorkoutNotFoundException("Workout could not be found by id")));
    }

    @Override
    public WorkoutDTO updateWorkout(int workoutId, WorkoutDTO workoutDTO) {
        Workout targetWorkout = workoutRepository.findById(workoutId).orElseThrow(()-> new WorkoutNotFoundException("Workout could not be found by id"));
        targetWorkout.setName(workoutDTO.getName());
        targetWorkout.setDayOfWeek(workoutDTO.getDayOfWeek());
        return mapToDTO(workoutRepository.save(targetWorkout));
    }

    @Override
    public void deleteWorkoutById(int workoutId) {
        Workout deleteWorkout = workoutRepository.findById(workoutId).orElseThrow(()-> new WorkoutNotFoundException("Workout could not be found by id"));
        workoutRepository.delete(deleteWorkout);
    }
    private WorkoutDTO mapToDTO(Workout workout){
        WorkoutDTO workoutDTO = new WorkoutDTO();
        workoutDTO.setId(workout.getId());
        workoutDTO.setName(workout.getName());
        workoutDTO.setDayOfWeek(workout.getDayOfWeek());
        return workoutDTO;
    }
    private Workout mapToEntity(WorkoutDTO workoutDTO){
        Workout workout = new Workout();
        workout.setName(workoutDTO.getName());
        workout.setDayOfWeek(workoutDTO.getDayOfWeek());
        return workout;
    }
}
