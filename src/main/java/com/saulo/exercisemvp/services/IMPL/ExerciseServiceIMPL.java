package com.saulo.exercisemvp.services.IMPL;

import com.saulo.exercisemvp.dto.ExerciseDTO;
import com.saulo.exercisemvp.exceptions.ExerciseNotFoundException;
import com.saulo.exercisemvp.models.Exercise;
import com.saulo.exercisemvp.repositories.ExerciseRepository;
import com.saulo.exercisemvp.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceIMPL implements ExerciseService {
    private ExerciseRepository exerciseRepository;
    @Autowired
    public ExerciseServiceIMPL(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll().stream().map(exercise -> mapToDTO(exercise)).collect(Collectors.toList());
    }

    @Override
    public ExerciseDTO createExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = mapToEntity(exerciseDTO);
        Exercise newExercise = exerciseRepository.save(exercise);
        return mapToDTO(newExercise);
    }

    @Override
    public ExerciseDTO getExerciseById(int exerciseId) {
        Exercise targetExercise = exerciseRepository.findById(exerciseId).orElseThrow(()->new ExerciseNotFoundException("Exercise could not be found by id"));
        return mapToDTO(targetExercise);
    }

    @Override
    public ExerciseDTO updateExercise(int exerciseId, ExerciseDTO exerciseDTO) {
        Exercise targetExercise = exerciseRepository.findById(exerciseId).orElseThrow(()->new ExerciseNotFoundException("Exercise could not be found by id"));
        targetExercise.setName(exerciseDTO.getName());
        targetExercise.setMuscle(exerciseDTO.getMuscle());
        return mapToDTO(exerciseRepository.save(targetExercise));
    }

    @Override
    public void deleteExercise(int exerciseId) {
        Exercise targetExercise = exerciseRepository.findById(exerciseId).orElseThrow(()->new ExerciseNotFoundException("Exercise could not be found by id"));
        exerciseRepository.delete(targetExercise);
    }

    private ExerciseDTO mapToDTO(Exercise exercise){
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setId(exercise.getId());
        exerciseDTO.setName(exercise.getName());
        exerciseDTO.setMuscle(exercise.getMuscle());
        return exerciseDTO;
    }
    private Exercise mapToEntity(ExerciseDTO exerciseDTO){
        Exercise exercise = new Exercise();
        exercise.setName(exerciseDTO.getName());
        exercise.setMuscle(exerciseDTO.getMuscle());
        return exercise;
    }
}
