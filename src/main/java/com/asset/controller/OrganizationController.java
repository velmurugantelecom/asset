package com.asset.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.asset.entity.Organizations;
import com.asset.entity.Suppliers;
import com.asset.model.respone.OrganizationVo;
import com.asset.service.OrganizationsService;
import com.asset.util.Utility;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class OrganizationController {

	@Autowired
	OrganizationsService organizationsService;

	@GetMapping("/organizations")
	public ResponseEntity<List<Organizations>> getAllOrganization() {
		try {
			List<Organizations> organizations = new ArrayList<Organizations>();
			organizationsService.findAll().forEach(organizations::add);
			return new ResponseEntity<>(organizations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/organizations/{id}")
	public ResponseEntity<Organizations> getUserById(@PathVariable("id") long id) {
		Optional<Organizations> userData = organizationsService.findById(id);
		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @PostMapping("/organizations")
    public ResponseEntity<Organizations> createUser(@RequestBody Organizations organizations) {
        try {
        	organizations.setOin(Utility.uniqueIdOID());
            organizationsService.save(organizations);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


	@PutMapping("/organizations/{id}")
	public ResponseEntity<Suppliers> updateUser(@PathVariable("id") long id, @RequestBody Organizations organizations) {

		if (organizationsService.update(organizations, id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/organizations/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			organizationsService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/organizations")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			organizationsService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/organizations/listnameid")
	public ResponseEntity<List<OrganizationVo>> getAllOrganizationNameAndId() {
		try {
			List<OrganizationVo> organizationsDto = new ArrayList<OrganizationVo>();
			organizationsService.getOrganizationNameAndId().forEach(organizationsDto::add);
			if (organizationsDto.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(organizationsDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
