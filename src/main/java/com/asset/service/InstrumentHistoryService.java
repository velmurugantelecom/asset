package com.asset.service;

import com.asset.entity.Instrument;
import com.asset.entity.InstrumentHistory;

import java.util.List;
import java.util.Optional;

public interface InstrumentHistoryService {
    public List<InstrumentHistory> findAll();
    public Optional<InstrumentHistory> findById(long Id);
    public void save(InstrumentHistory instrumentHistory);
    public boolean update(InstrumentHistory instrumentHistory, long id);
    public void deleteById(long id);
    public void deleteAll();
    public long findTotalInstHist();
}
