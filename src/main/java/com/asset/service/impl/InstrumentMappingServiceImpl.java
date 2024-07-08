package com.asset.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.entity.InstrumentMapping;
import com.asset.repositories.InstrumentMappingRepository;
import com.asset.repositories.InstrumentRepository;
import com.asset.service.InstrumentMappingService;

@Service
@Transactional
public class InstrumentMappingServiceImpl implements InstrumentMappingService {
	@Autowired
	InstrumentMappingRepository instrumentMappingRepository;
	
	@Autowired
	InstrumentRepository instrumentRepository;

	@Override
	public List<InstrumentMapping> findAll() {
		return instrumentMappingRepository.findAll();
	}

	@Override
	public void save(InstrumentMapping instrumentMapping) {
		instrumentMappingRepository.save(instrumentMapping);
		instrumentRepository.updateUseridForMapping(instrumentMapping.getInstrumentId(), instrumentMapping.getUserid(), true);
	}

	@Override
	public void deleteByInstrumentId(long instrumentId)
	{
		instrumentMappingRepository.deleteByInstrumentId(instrumentId);
		instrumentRepository.updateUseridForMapping(instrumentId, 0, false);
	}
	
	@Override
	public void deleteByUserId(long userId)
	{
		instrumentMappingRepository.deleteByUserid(userId);
		instrumentRepository.updateUseridForUnMapping(userId);
	}
}
