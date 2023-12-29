package com.saulo.exercisemvp.services;

import com.saulo.exercisemvp.dto.ExerciseDTO;

import java.util.List;

public interface ExerciseService {
    List<ExerciseDTO> getAllExercises();
    ExerciseDTO createExercise(ExerciseDTO exerciseDTO);
    ExerciseDTO getExerciseById(int exerciseId);
    ExerciseDTO updateExercise(int exerciseId, ExerciseDTO exerciseDTO);
    void deleteExercise(int exerciseId);
}
