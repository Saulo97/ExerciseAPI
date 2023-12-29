package com.saulo.exercisemvp.controllers;

import com.saulo.exercisemvp.dto.StatsDTO;
import com.saulo.exercisemvp.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class StatsController {
    private StatsService statsService;
    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }
    @GetMapping("/stat/{id}")
    public ResponseEntity<StatsDTO>getStatById(@PathVariable int id){
        StatsDTO stat = statsService.getStatsById(id);
        return new ResponseEntity<>(stat, HttpStatus.ACCEPTED);
    }
    @PostMapping("/stat/record/{record_id}")
    public ResponseEntity<StatsDTO>createStat(@PathVariable int record_id, @RequestBody StatsDTO statsDTO){
        StatsDTO newStat = statsService.addStatToRecord(record_id, statsDTO);
        return new ResponseEntity<>(newStat, HttpStatus.CREATED);
    }
    @PutMapping("/stat/{stat_id}/record/{recod_id}")
    public ResponseEntity<StatsDTO>updateStat(@RequestBody StatsDTO statsDTO, @PathVariable int stat_id, @PathVariable int recod_id){
        StatsDTO updatedStats = statsService.updateStatsById(recod_id, stat_id, statsDTO);
        return  new ResponseEntity<>(updatedStats, HttpStatus.CREATED);
    }
    @GetMapping("/stats/record/{record_id}")
    public ResponseEntity<List<StatsDTO>>getStatsByRecord(@PathVariable int record_id){
        List<StatsDTO> statsList = statsService.getStatsByRecordId(record_id);
        return new ResponseEntity<>(statsList, HttpStatus.OK);
    }
    @DeleteMapping("/stat/{stat_id}/record/{record_id}")
    public ResponseEntity<String>deleteStat(@PathVariable int stat_id, @PathVariable int record_id){
        statsService.deleteStatsById(record_id, stat_id);
        return ResponseEntity.ok("Stat deleted successfully");
    }
}
