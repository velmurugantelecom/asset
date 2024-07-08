package com.asset.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.asset.entity.Instrument;
import com.asset.model.respone.InstrumentVo;

@Component
public interface InstrumentService {

    public List<Instrument> findAll();
    public List<Instrument> findByUserId(long userId);
    public List<Instrument> findByClientAdminId(long clientAdminId);
    public List<Instrument> findByInstrumentStatus(String instrumentStatus);
    public Optional<Instrument> findById(long Id);
    public void save(Instrument instrument);
    public boolean update(Instrument instrument, long id);
    public void deleteById(long id);
    public void deleteAll();
    public List<InstrumentVo> getInstrumentNameAndId(boolean mapped,long userId);
    public Long findTotalInst();
    public List<InstrumentVo> getUnMapInstruments();
    List<Instrument> getAllInstrumentPagination(int pageNo, int pageSize);
}
