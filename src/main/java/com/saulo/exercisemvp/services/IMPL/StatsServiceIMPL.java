package com.saulo.exercisemvp.services.IMPL;

import com.saulo.exercisemvp.dto.StatsDTO;
import com.saulo.exercisemvp.exceptions.RecordNotFoundException;
import com.saulo.exercisemvp.exceptions.StatsNotFoundException;
import com.saulo.exercisemvp.models.Record;
import com.saulo.exercisemvp.models.Stats;
import com.saulo.exercisemvp.repositories.RecordRepository;
import com.saulo.exercisemvp.repositories.StatsRepository;
import com.saulo.exercisemvp.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsServiceIMPL implements StatsService {
    private StatsRepository statsRepository;
    private RecordRepository recordRepository;
    @Autowired
    public StatsServiceIMPL(RecordRepository recordRepository,StatsRepository statsRepository) {
        this.recordRepository = recordRepository;
        this.statsRepository = statsRepository;
    }

    @Override
    public StatsDTO getStatsById(int statsId) {
        return mapToDTO(statsRepository.findById(statsId).orElseThrow(()-> new StatsNotFoundException("Stat could not be found by id")));
    }
    @Override
    public StatsDTO addStatToRecord(int recordId, StatsDTO statsDTO) {
        Stats newStat = mapToEntity(statsDTO);
        Record recordTarget = recordRepository.findById(recordId).orElseThrow(()-> new RecordNotFoundException("This Record could not be found by id"));
        newStat.setRecord(recordTarget);
        return mapToDTO(statsRepository.save(newStat));
    }

    @Override
    public StatsDTO updateStatsById(int recordId, int statsId, StatsDTO statsDTO) {
        Record targetRecord = recordRepository.findById(recordId).orElseThrow(()->new RecordNotFoundException("This Record could not be found by id"));
        Stats targetStat = statsRepository.findById(statsId).orElseThrow(()->new StatsNotFoundException("This Stats could not be found by id"));
        if(targetStat.getRecord().getId() != targetRecord.getId()){
            throw new StatsNotFoundException("This stats not belond to a record");
        }
        targetStat.setReps(statsDTO.getReps());
        targetStat.setWeight(statsDTO.getWeight());
        targetStat.setSetNumbre(statsDTO.getSetNumber());
        return mapToDTO(statsRepository.save(targetStat));
    }
    @Override
    public List<StatsDTO> getStatsByRecordId(int recordId) {
        Record targetRecord = recordRepository.findById(recordId).orElseThrow(()->new RecordNotFoundException("This Record could not be found by id"));
        return statsRepository.findByRecordId(recordId).stream().map(stats -> mapToDTO(stats)).collect(Collectors.toList());
    }

    @Override
    public void deleteStatsById(int recordId, int statsId) {
        Record targetRecord = recordRepository.findById(recordId).orElseThrow(()->new RecordNotFoundException("This Record could not be found by id"));
        Stats targetStat = statsRepository.findById(statsId).orElseThrow(()->new StatsNotFoundException("This Stats could not be found by id"));
        if(targetStat.getRecord().getId() != targetRecord.getId()){
            throw new StatsNotFoundException("This stats not belond to a record");
        }
        statsRepository.delete(targetStat);
    }

    private StatsDTO mapToDTO(Stats stats){
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setId(stats.getId());
        statsDTO.setSetNumber(stats.getSetNumbre());
        statsDTO.setReps(stats.getReps());
        statsDTO.setWeight(stats.getWeight());
        return statsDTO;
    }
    private Stats mapToEntity(StatsDTO statsDTO){
        Stats stats = new Stats();
        stats.setReps(statsDTO.getReps());
        stats.setWeight(statsDTO.getWeight());
        stats.setSetNumbre(statsDTO.getSetNumber());
        return stats;
    }
}
