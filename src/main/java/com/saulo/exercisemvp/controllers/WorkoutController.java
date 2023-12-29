package com.saulo.exercisemvp.controllers;

import com.saulo.exercisemvp.dto.WorkoutDTO;
import com.saulo.exercisemvp.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class WorkoutController {
    private WorkoutService workoutService;
    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }
    @GetMapping("/workouts")
    public ResponseEntity<List<WorkoutDTO>>getAllWorkouts(){
        List<WorkoutDTO> workoutList = workoutService.getAllWorkout();
        return ResponseEntity.ok(workoutList);
    }
    @PostMapping("workout")
    public ResponseEntity<WorkoutDTO>createWorkout(@RequestBody WorkoutDTO workout){
        WorkoutDTO newWorkout = workoutService.createWorkout(workout);
        return new ResponseEntity<>(newWorkout, HttpStatus.CREATED);
    }
    @PutMapping("workout/{id}")
    public ResponseEntity<WorkoutDTO>updateWorkout(@PathVariable int id,@RequestBody WorkoutDTO workout){
        WorkoutDTO updateWorkout = workoutService.updateWorkout(id,workout);
        return new ResponseEntity<>(updateWorkout, HttpStatus.CREATED);
    }
    @GetMapping("workout/{id}")
    public ResponseEntity<WorkoutDTO>getWorkout(@PathVariable int id){
        WorkoutDTO workout = workoutService.getWorkoutById(id);
        return new ResponseEntity<>(workout, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("workout/{id}")
    public ResponseEntity<String>deleteWorkout(@PathVariable int id){
        workoutService.deleteWorkoutById(id);
        return new ResponseEntity<>("Workout deleted successfully", HttpStatus.ACCEPTED);
    }
}
