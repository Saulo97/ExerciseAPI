package com.saulo.exercisemvp.services.IMPL;

import com.saulo.exercisemvp.dto.RecordDTO;
import com.saulo.exercisemvp.exceptions.ExerciseNotFoundException;
import com.saulo.exercisemvp.exceptions.RecordNotFoundException;
import com.saulo.exercisemvp.exceptions.WorkoutNotFoundException;
import com.saulo.exercisemvp.models.Exercise;
import com.saulo.exercisemvp.models.Record;
import com.saulo.exercisemvp.models.Workout;
import com.saulo.exercisemvp.repositories.ExerciseRepository;
import com.saulo.exercisemvp.repositories.RecordRepository;
import com.saulo.exercisemvp.repositories.WorkoutRepository;
import com.saulo.exercisemvp.services.ExerciseService;
import com.saulo.exercisemvp.services.RecordService;
import com.saulo.exercisemvp.services.StatsService;
import com.saulo.exercisemvp.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceIMPL implements RecordService {
    private RecordRepository recordRepository;
    private WorkoutRepository workoutRepository;
    private ExerciseRepository exerciseRepository;
    private ExerciseService exerciseService;
    private WorkoutService workoutService;
    private StatsService statsService;
    @Autowired
    public RecordServiceIMPL(RecordRepository recordRepository, WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, ExerciseService exerciseService, WorkoutService workoutService, StatsService statsService) {
        this.recordRepository = recordRepository;
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseService = exerciseService;
        this.workoutService = workoutService;
        this.statsService = statsService;
    }
    @Override
    public RecordDTO getRecordById(int recordId) {
        return mapToDTO(recordRepository.findById(recordId).orElseThrow(()->new RecordNotFoundException("Record could not be found by id")));
    }

    @Override
    public List<RecordDTO> getAllRecordsByWorkoutId(int workoutId) {
        return recordRepository.findByWorkoutId(workoutId).stream().map(record -> mapToDTO(record)).collect(Collectors.toList());
    }

    @Override
    public RecordDTO addExerciseToRecord(int workoutID, int exerciseId) {
        Record newRecord = new Record();
        Workout targetWorkout = workoutRepository.findById(workoutID).orElseThrow(()->new WorkoutNotFoundException("Workout could not bre found by id"));
        Exercise targetExercise = exerciseRepository.findById(exerciseId).orElseThrow(()-> new ExerciseNotFoundException("Exercise could not be found by id"));
        newRecord.setWorkout(targetWorkout);
        newRecord.setExercise(targetExercise);
        return mapToDTO(recordRepository.save(newRecord));
    }

    @Override
    public void deleteExerciseToRecord(int recordId) {
        Record targetRecord = recordRepository.findById(recordId).orElseThrow(()-> new RecordNotFoundException("Record could not be found by id"));
        recordRepository.delete(targetRecord);
    }
    private RecordDTO mapToDTO(Record record){
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setId(record.getId());
        //recordDTO.setExerciseName(record.getExercise().getName());
        recordDTO.setExercise(exerciseService.getExerciseById(record.getExercise().getId()));
        recordDTO.setWorkout(workoutService.getWorkoutById(record.getWorkout().getId()));
        recordDTO.setStats(statsService.getStatsByRecordId(record.getId()));
        return recordDTO;
    }
    private Record mapToEntity(RecordDTO recordDTO){
        Record record = new Record();
        //record.setExercise(recordDTO.getExercise());
        //record.setWorkout(recordDTO.getWorkout());
        //record.setStatsList(recordDTO.getStats());
        return record;
    }
}
