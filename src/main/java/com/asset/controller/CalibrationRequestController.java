package com.asset.controller;

import com.asset.entity.CalibrationRequest;
import com.asset.entity.LoginUsers;
import com.asset.entity.PurchaseRequest;
import com.asset.repositories.CalibrationRequestRepository;
import com.asset.repositories.UsersRepository;
import com.asset.service.CalibrationRequestService;
import com.asset.util.Constants;
import com.asset.util.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class CalibrationRequestController {

    @Autowired
    CalibrationRequestService calibrationRequestService;
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    CalibrationRequestRepository calibrationRequestRepository;

    @GetMapping("/calibrationrequest")
    public ResponseEntity<List<CalibrationRequest>> getAllCalibrationRequest() {
        try {
            List<CalibrationRequest> calibrationRequestData = new ArrayList<CalibrationRequest>();
            calibrationRequestService.findAll().forEach(calibrationRequestData::add);
//            if (calibrationRequestData.isEmpty()) {
//                return new ResponseEntity<>(calibrationRequestData,HttpStatus.NO_CONTENT);
//            }
            return new ResponseEntity<>(calibrationRequestData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/calibrationrequest/{id}")
    public ResponseEntity<CalibrationRequest> getCalibrationRequestById(@PathVariable("id") long id) {
        Optional<CalibrationRequest> calibrationRequestData = calibrationRequestService.findById(id);
        if (calibrationRequestData.isPresent()) {
            return new ResponseEntity<>(calibrationRequestData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/calibrationrequest")
    public ResponseEntity<CalibrationRequest> createCalibrationRequest(@RequestBody CalibrationRequest calibrationRequestData) {
        try {
        	calibrationRequestData.setCalibrationId("CR"+Utility.uniqueCRID());
            calibrationRequestService.save(calibrationRequestData);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/calibrationrequest/{id}")
    public ResponseEntity<CalibrationRequest> updateCalibrationRequest(@PathVariable("id") long id, @RequestBody CalibrationRequest calibrationRequestData) {
        if (calibrationRequestService.update(calibrationRequestData, id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/calibrationrequest/{id}")
    public ResponseEntity<HttpStatus> deleteCalibrationRequest(@PathVariable("id") long id) {
        try {
            calibrationRequestService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/calibrationrequest")
    public ResponseEntity<HttpStatus> deleteAllCalibrationRequest() {
        try {
            calibrationRequestService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    
    @GetMapping("/calibrationerequest/{userId}")
    public ResponseEntity<List<CalibrationRequest>> getAllCalibrationRequestByUserId(@PathVariable("userId") long userId) {
        try {
        	 List<CalibrationRequest> calibrationRequestData = new ArrayList<CalibrationRequest>();
        	Optional<LoginUsers> userData = usersRepository.findById(userId);
          	if (userData.isPresent()) {
        		if (userData.get().getRole().equalsIgnoreCase(Constants.CLIENT_ADMIN)) {        			
        			List<Long> userIdList= usersRepository.getAllUserByParentId(userId);
        			calibrationRequestRepository.findAllByMultipleUserId(userIdList).forEach(calibrationRequestData::add);
    			} else if (userData.get().getRole().equalsIgnoreCase(Constants.INSTRUMENT_INCHARGE)) {
    				calibrationRequestRepository.findAllByUserid(userId).forEach(calibrationRequestData::add);
    			} 
    			else {
    				calibrationRequestService.findAll().forEach(calibrationRequestData::add);
    			}
    		}
            return new ResponseEntity<>(calibrationRequestData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
  //Test
    @PostMapping("/calibrationerequest/{userId}")
    public ResponseEntity<CalibrationRequest> createCalibrationRequest(@RequestBody CalibrationRequest calibrationRequestData,@PathVariable("userId") long userId) {
        try {
        	Optional<LoginUsers> userData = usersRepository.findById(userId);
        	if (userData.isPresent()) {
        		calibrationRequestData.setOrganisationId(userData.get().getOrganisationId());
    		}
        	calibrationRequestData.setCalibrationId("PR"+Utility.uniqueCRID());
        	calibrationRequestService.save(calibrationRequestData);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
