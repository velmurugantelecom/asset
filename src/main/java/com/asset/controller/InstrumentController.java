package com.asset.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asset.entity.Instrument;
import com.asset.model.respone.InstrumentVo;
import com.asset.service.InstrumentService;
import com.asset.util.Constants;
import com.asset.util.Utility;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class InstrumentController {

	@Autowired
	InstrumentService instrumentService;

	@PostMapping("/instrument")
	public ResponseEntity<Instrument> createInstrument(@RequestBody Instrument instrument) {
		try {

			instrument.setIIN("I" + Utility.uniqueIdIID());
			instrument.setInstrumentStatus(Constants.INSTRUMENT_STATUS_PENDING);
			instrumentService.save(instrument);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/instruments")
	public ResponseEntity<List<Instrument>> getAllInstruments() {
		try {
			List<Instrument> InstrumentObj = new ArrayList<>();
			instrumentService.findAll().forEach(InstrumentObj::add);
			return new ResponseEntity<>(InstrumentObj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/instruments/{role}/{userId}/{instrumentStatus}")
	public ResponseEntity<List<Instrument>> getAllInstrumentsByRoleAndId(@PathVariable("role") String role,
			@PathVariable("userId") long id, @PathVariable("instrumentStatus") String instrumentStatus) {
		try {
			List<Instrument> InstrumentObj = new ArrayList<>();
			if (role.equalsIgnoreCase(Constants.CLIENT_ADMIN)) {
				instrumentService.findByClientAdminId(id).forEach(InstrumentObj::add);
			} else if (role.equalsIgnoreCase(Constants.INSTRUMENT_INCHARGE)) {
				instrumentService.findByUserId(id).forEach(InstrumentObj::add);
			} else if (instrumentStatus.equalsIgnoreCase(Constants.INSTRUMENT_STATUS_APPROVED)) {
				instrumentService.findByInstrumentStatus(instrumentStatus).forEach(InstrumentObj::add);
			} else {
				instrumentService.findAll().forEach(InstrumentObj::add);
			}
			return new ResponseEntity<>(InstrumentObj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/instrument/{id}")
	public ResponseEntity<Instrument> getInstrumentById(@PathVariable("id") long id) {
		Optional<Instrument> instrument = instrumentService.findById(id);
		if (instrument.isPresent()) {
			return new ResponseEntity<>(instrument.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/instrument/{id}")
	public ResponseEntity<Instrument> updateInstrument(@PathVariable("id") long id,
			@RequestBody Instrument instrument) {
		try {
			if (instrumentService.update(instrument, id)) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/instrument/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			instrumentService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/instrumentsAll")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			instrumentService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/instruments/{userId}/{mapped}")
	public List<InstrumentVo> getMappedInstrument(@PathVariable("userId") long userId,
			@PathVariable("mapped") boolean mapped) {
		List<InstrumentVo> instrument = instrumentService.getInstrumentNameAndId(mapped, userId);
		return instrument;
	}

	@GetMapping("/instruments/unmappedInstument")
	public List<InstrumentVo> getUnMappedInstrument() {
		List<InstrumentVo> instrument = instrumentService.getUnMapInstruments();
		return instrument;
	}

	@GetMapping("/instruments/pagination/{pageNo}/{pageSize}")
	public ResponseEntity<List<Instrument>> getAllInstrumentPages(@PathVariable int pageNo,
			@PathVariable int pageSize) {
		List<Instrument> list = instrumentService.getAllInstrumentPagination(pageNo, pageSize);
		return new ResponseEntity<List<Instrument>>(list, new HttpHeaders(), HttpStatus.OK);
	}

}
