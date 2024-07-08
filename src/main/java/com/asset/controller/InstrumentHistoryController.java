package com.asset.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.entity.InstrumentHistory;
import com.asset.service.InstrumentHistoryService;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class InstrumentHistoryController {

    @Autowired
    InstrumentHistoryService instrumentHistoryService;

    @PostMapping("/instrumenthistory")
    public ResponseEntity<InstrumentHistory> createInstrumentHistory(@RequestBody InstrumentHistory instrumentHistory) {
        try {
            instrumentHistoryService.save(instrumentHistory);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/instrumentsHistory")
    public ResponseEntity<List<InstrumentHistory>> getAllInstrumentsHistory() {
        try {
            List<InstrumentHistory> InstrumentHistObj = new ArrayList<>();
            instrumentHistoryService.findAll().forEach(InstrumentHistObj::add);
            return new ResponseEntity<>(InstrumentHistObj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
