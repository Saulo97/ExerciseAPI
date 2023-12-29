package com.saulo.exercisemvp.repositories;

import com.saulo.exercisemvp.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findByWorkoutId(int workout_id);
}
