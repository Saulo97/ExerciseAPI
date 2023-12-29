package com.saulo.exercisemvp.exceptions;

public class WorkoutNotFoundException extends RuntimeException{
    public WorkoutNotFoundException(String message){
        super(message);
    }
}
