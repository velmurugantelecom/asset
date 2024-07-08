package com.asset.service.impl;

import com.asset.entity.CalibrationRequest;
import com.asset.repositories.CalibrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asset.service.CalibrationRequestService;

import java.util.List;
import java.util.Optional;

@Service
public class CalibrationRequestServiceImpl implements CalibrationRequestService{


    @Autowired
    CalibrationRequestRepository calibrationRequestRepository;
    public List<CalibrationRequest> findAll()
    {
        return calibrationRequestRepository.findAll();
    }
    public Optional<CalibrationRequest> findById(long id)
    {
        return calibrationRequestRepository.findById(id);
    }
    public void save(CalibrationRequest Data)
    {
        calibrationRequestRepository.save(Data);
    }
    public boolean update(CalibrationRequest paramData, long id)
    {
        Optional<CalibrationRequest> PurchaseRequestData = calibrationRequestRepository.findById(id);
        if (PurchaseRequestData.isPresent()) {
            paramData.setID(id);
            calibrationRequestRepository.save(paramData);
            return true;
        }
        return false;
    }
    public void deleteById(long id)
    {
        calibrationRequestRepository.deleteById(id);
    }
    public void deleteAll()
    {
        calibrationRequestRepository.deleteAll();
    }
    
    
	public long findTotalCountCalibration() {
		return calibrationRequestRepository.count();
	}


}
