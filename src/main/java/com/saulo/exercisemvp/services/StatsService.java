package com.saulo.exercisemvp.services;

import com.saulo.exercisemvp.dto.StatsDTO;

import java.util.List;

public interface StatsService {
    StatsDTO getStatsById(int statsId);
    StatsDTO addStatToRecord(int recordId, StatsDTO statsDTO);
    StatsDTO updateStatsById(int recordId,int statsId, StatsDTO statsDTO);
    List<StatsDTO> getStatsByRecordId(int recordId);
    void deleteStatsById(int recordId,int statsId);
}
