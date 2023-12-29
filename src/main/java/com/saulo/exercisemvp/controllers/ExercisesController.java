package com.saulo.exercisemvp.controllers;

import com.saulo.exercisemvp.dto.ExerciseDTO;
import com.saulo.exercisemvp.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ExercisesController {
    private ExerciseService exerciseService;
    @Autowired
    public ExercisesController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseDTO>>getAllExercises(){
        List<ExerciseDTO> exerciseList = exerciseService.getAllExercises();
        return ResponseEntity.ok(exerciseList);
    }
    @PostMapping("/exercise")
    public ResponseEntity<ExerciseDTO>createExercise(@RequestBody ExerciseDTO newExercise){
        ExerciseDTO createdExercise = exerciseService.createExercise(newExercise);
        return new ResponseEntity<ExerciseDTO>(createdExercise, HttpStatus.CREATED);
    }
    @GetMapping("/exercise/{id}")
    public ResponseEntity<ExerciseDTO>getExercise(@PathVariable int id){
        ExerciseDTO exercise = exerciseService.getExerciseById(id);
        return new ResponseEntity<ExerciseDTO>(exercise, HttpStatus.ACCEPTED);
    }
    @PutMapping("/exercise/{id}")
    public ResponseEntity<ExerciseDTO>updateExercise(@PathVariable int id,@RequestBody ExerciseDTO exercise){
        ExerciseDTO updatedExercise = exerciseService.updateExercise(id,exercise);
        return new ResponseEntity<ExerciseDTO>(updatedExercise, HttpStatus.CREATED);
    }
    @DeleteMapping("exercise/{id}")
    public ResponseEntity<String>deleteExercise(@PathVariable int id){
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok("Exercise deleted successfully");
    }


}
