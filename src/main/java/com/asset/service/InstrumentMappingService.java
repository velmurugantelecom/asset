package com.asset.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.asset.entity.InstrumentMapping;

@Component
public interface InstrumentMappingService {

    public List<InstrumentMapping> findAll();
    public void save(InstrumentMapping instrumentMapping);
    public void deleteByInstrumentId(long instrumentId);
	public void deleteByUserId(long userId);
}
