package com.saulo.exercisemvp.services;

import com.saulo.exercisemvp.dto.HistoryDTO;

import java.util.List;

public interface HistoryService {
    List<HistoryDTO> getAllHistory();
    HistoryDTO createHistory(int recordId);//New History only need routine and date
    HistoryDTO getHistoryById(int historyId);
    HistoryDTO getHistoryByRecordId(int recordId);
    void deleteHistoryById(int historyId);
}
