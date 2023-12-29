package com.saulo.exercisemvp.controllers;

import com.saulo.exercisemvp.dto.RecordDTO;
import com.saulo.exercisemvp.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RecordController {
    private RecordService recordService;
    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }
    @GetMapping("/records/workout/{id}")
    public ResponseEntity<List<RecordDTO>>getRecordsByWorkout(@PathVariable int id){
        List<RecordDTO> recordList = recordService.getAllRecordsByWorkoutId(id);
        return ResponseEntity.ok(recordList);
    }
    @GetMapping("/record/{id}")
    public ResponseEntity<RecordDTO>getRecordById(@PathVariable int id){
        RecordDTO record = recordService.getRecordById(id);
        return new ResponseEntity<>(record, HttpStatus.ACCEPTED);
    }
    @PostMapping("/record/exercise/{exercise_id}/workout/{workout_id}")
    public ResponseEntity<RecordDTO>createRecord(@PathVariable int exercise_id, @PathVariable int workout_id){
        RecordDTO newRecord = recordService.addExerciseToRecord(workout_id, exercise_id);
        return new ResponseEntity<>(newRecord, HttpStatus.CREATED);
    }
    @DeleteMapping("/record/{id}")
    public ResponseEntity<String>deleteRecord(@PathVariable int id){
        recordService.deleteExerciseToRecord(id);
        return new ResponseEntity<>("Record deleted successfully",HttpStatus.ACCEPTED);
    }

}
