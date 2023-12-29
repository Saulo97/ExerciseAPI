package com.saulo.exercisemvp.services;

import com.saulo.exercisemvp.dto.RecordDTO;

import java.util.List;

public interface RecordService {
    //List<RecordDTO> getAllRecords();
    RecordDTO getRecordById(int recordId);
    List<RecordDTO> getAllRecordsByWorkoutId(int workoutId);
    RecordDTO addExerciseToRecord(int workoutID, int exerciseId);//This is createRecord
    //RecordDTO updateRecordById(int workoutId, int exerciseId, int recordId, RecordDTO recordDTO);
    void deleteExerciseToRecord(int recordId);// This is deleteRecordById;

}
