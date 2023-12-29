package com.saulo.exercisemvp.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer reps;
    private Integer weight;
    private Integer setNumbre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

}
