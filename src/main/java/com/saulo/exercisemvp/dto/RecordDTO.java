package com.saulo.exercisemvp.dto;

import com.saulo.exercisemvp.models.Exercise;
import com.saulo.exercisemvp.models.Stats;
import com.saulo.exercisemvp.models.Workout;
import lombok.Data;

import java.util.List;

@Data
public class RecordDTO {
    private int id;
    private WorkoutDTO workout;
    private ExerciseDTO exercise;
    //private String exerciseName;
    private List<StatsDTO> stats;
}
