package com.saulo.exercisemvp.repositories;

import com.saulo.exercisemvp.models.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {
    List<Stats> findByRecordId(int recordId);
}
