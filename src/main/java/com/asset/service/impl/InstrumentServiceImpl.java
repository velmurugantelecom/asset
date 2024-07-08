package com.asset.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.asset.entity.Instrument;
import com.asset.model.respone.InstrumentVo;
import com.asset.repositories.InstrumentPageRepository;
import com.asset.repositories.InstrumentRepository;
import com.asset.service.InstrumentService;
import org.springframework.data.domain.Sort;


@Service
public class InstrumentServiceImpl implements InstrumentService {
    @Autowired
    InstrumentRepository instrumentRepository;
    
    @Autowired
    InstrumentPageRepository instrumentPageRepository;

    @Override
    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }
    public List<Instrument> findByUserId(long userId)
    {
    	return instrumentRepository.findByUserid(userId);
    }
    public List<Instrument> findByClientAdminId(long clientAdminId)
    {
    	return instrumentRepository.findByClientAdminId(clientAdminId);
    }
    public List<Instrument> findByInstrumentStatus(String instrumentStatus)
    {
    	return instrumentRepository.findByInstrumentStatus(instrumentStatus);
    }
    
    @Override
    public Optional<Instrument> findById(long Id) {
        return instrumentRepository.findById(Id);
    }

    @Override
    public void save(Instrument instrument) {
        instrumentRepository.save(instrument);
    }

    @Override
    public boolean update(Instrument instrument, long id) {
    	Optional<Instrument> instrumentdata = instrumentRepository.findById(id);
		if (instrumentdata.isPresent()) {	
			instrument.setIdNo(id);
			instrumentRepository.save(instrument);
			return true;
		}
		return false;
    }

    @Override
    public void deleteById(long id) {
        instrumentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        instrumentRepository.deleteAll();
    }
    
    public List<InstrumentVo> getInstrumentNameAndId(boolean mapped,long userId) {
    	return instrumentRepository.getQueryMapInstrNameAndID(mapped,userId);
    	
    }

	@Override
	public Long findTotalInst() {
		return instrumentRepository.count();
	}
	
	@Override
	 public List<InstrumentVo> getUnMapInstruments()
	 {
		return instrumentRepository.getQueryUnMapInstruments();
	 }
	
	  public List<Instrument> getAllInstrumentPagination(int pageNo, int pageSize)
	    {
	        Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by("instrumentName").descending());
	 
	        Page<Instrument> pagedResult = instrumentPageRepository.findAll(paging);
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Instrument>();
	        }
	    }
	
	
	
}
