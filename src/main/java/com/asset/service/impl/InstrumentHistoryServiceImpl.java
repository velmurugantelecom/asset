package com.asset.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.entity.InstrumentHistory;
import com.asset.repositories.InstrumentHistoryRepository;
import com.asset.service.InstrumentHistoryService;

@Service
public class InstrumentHistoryServiceImpl implements InstrumentHistoryService {
	@Autowired
	InstrumentHistoryRepository instrumentHistoryRepository;

	@Override
	public List<InstrumentHistory> findAll() {
		return instrumentHistoryRepository.findAll();
	}

	@Override
	public Optional<InstrumentHistory> findById(long Id) {
		return instrumentHistoryRepository.findById(Id);
	}

	@Override
	public void save(InstrumentHistory instrumentHistory) {
		instrumentHistoryRepository.save(instrumentHistory);
	}

	@Override
	public boolean update(InstrumentHistory instrument, long id) {
		return false;
	}

	@Override
	public void deleteById(long id) {
		instrumentHistoryRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		instrumentHistoryRepository.deleteAll();
	}

	@Override
	public long findTotalInstHist() {
		return instrumentHistoryRepository.count();
	}

}
