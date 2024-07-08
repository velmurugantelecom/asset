package com.asset.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.entity.InstrumentMapping;
import com.asset.repositories.InstrumentRepository;
import com.asset.service.InstrumentMappingService;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
@Transactional
public class InstrumentMapingController {

    @Autowired
    InstrumentMappingService instrumentMappingService;
    
    @Autowired
    InstrumentRepository instrumentRepository;

    @PostMapping("/instrumentMapping")
    public ResponseEntity<InstrumentMapping> createInstrument(@RequestBody InstrumentMapping instrumentMapping) {
        try {
        	instrumentMappingService.save(instrumentMapping);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/instrumentsMapping")
    public ResponseEntity<List<InstrumentMapping>> getAllInstruments() {
        try {
        List<InstrumentMapping> instrumentMappingList = new ArrayList<>();
        instrumentMappingService.findAll().forEach(instrumentMappingList::add);
//        if (InstrumentObj.isEmpty()) {
//            return new ResponseEntity<>(InstrumentObj,HttpStatus.NO_CONTENT);
//        }
        return new ResponseEntity<>(instrumentMappingList, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    
    @DeleteMapping("/instrumentsMapping/delete/{instrumentId}")
	public ResponseEntity<HttpStatus> deleteSingleRecord(@PathVariable("instrumentId")long instrumentId) {
		try {
			instrumentMappingService.deleteByInstrumentId(instrumentId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
  
	
}
