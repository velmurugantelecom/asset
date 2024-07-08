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

import com.asset.entity.UserMapping;
import com.asset.repositories.UsersRepository;
import com.asset.service.UserMappingService;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
@Transactional
public class UserMappingController {
	@Autowired
	UserMappingService userMappingService;
	
	@Autowired
	UsersRepository usersRepository;
	
	

	@GetMapping("/usersMapping")
	public ResponseEntity<List<UserMapping>> getAllLoginUsersMappings() {
		try {
			List<UserMapping> userMapping = new ArrayList<UserMapping>();
			userMappingService.findAll().forEach(userMapping::add);
			return new ResponseEntity<>(userMapping, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/usersMapping")
	public ResponseEntity<UserMapping> mapUserClientAdmin(@RequestBody UserMapping userMapping) {
		try {
			userMappingService.save(userMapping);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/usersMapping/delete/{userId}")
	public ResponseEntity<HttpStatus> deleteSingleRecord(@PathVariable("userId")long Userid) {
		try {
			userMappingService.deleteByUserid(Userid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
