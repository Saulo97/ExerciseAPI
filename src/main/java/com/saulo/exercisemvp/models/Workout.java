package com.saulo.exercisemvp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String dayOfWeek;
    @OneToMany(mappedBy = "workout", fetch = FetchType.LAZY)
    private List<Record> records = new ArrayList<>();
}
