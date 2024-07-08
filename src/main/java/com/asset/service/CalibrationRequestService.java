package com.asset.service;

import com.asset.entity.CalibrationRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CalibrationRequestService {
    public List<CalibrationRequest> findAll();
    public Optional<CalibrationRequest> findById(long Id);
    public void save(CalibrationRequest calibrationRequest);
    public boolean update(CalibrationRequest calibrationRequest, long id);
    public void deleteById(long id);
    public void deleteAll();
    public long findTotalCountCalibration();
}
