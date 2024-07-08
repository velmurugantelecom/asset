package com.asset.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.asset.entity.LoginUsers;
import com.asset.model.request.LoginUsersRequestVo;
import com.asset.model.request.SMSRequest;
import com.asset.model.respone.LoginUsersResponseVo;
import com.asset.model.respone.UsersMappingVo;
import com.asset.service.UserService;

@RestController
//@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService userService;
	
	
	@GetMapping("/loginusers")
	public ResponseEntity<List<LoginUsers>> getAllLoginUsers() {
		try {
			List<LoginUsers> users = new ArrayList<LoginUsers>();
			userService.findAll().forEach(users::add);
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/loginusers/{id}")
	public ResponseEntity<LoginUsers> getLoginUserById(@PathVariable("id") long id) {
		Optional<LoginUsers> userData = userService.findById(id);
		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/loginusers")
	public ResponseEntity<LoginUsers> createLoginUser(@RequestBody LoginUsers users) {
		try {
			userService.save(users);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/loginusers/{id}")
	public ResponseEntity<LoginUsers> updateLoginUser(@PathVariable("id") long id, @RequestBody LoginUsers users) {

		if (userService.update(users, id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/loginusers/{id}")
	public ResponseEntity<HttpStatus> deleteLoginUser(@PathVariable("id") long id) {
		try {
			// userService.deleteById(id);
			Optional<LoginUsers> userData = userService.findById(id);
			if (userData.isPresent()) {
				userData.get().setDeleted(true);
				if (userService.update(userData.get(), id)) {
					return new ResponseEntity<>(HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/loginusers")
	public ResponseEntity<HttpStatus> deleteAllLoginUsers() {
		try {
			userService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/checkLoginUserDetail")
	public ResponseEntity<LoginUsers> getLoginUserDetails(@RequestBody LoginUsersRequestVo loginUserDetail) {
		LoginUsers userData = userService.getBasedOnMailPassword(loginUserDetail.getUsername(),
				loginUserDetail.getPassword());
		if (userData != null) {
			return new ResponseEntity<>(userData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/loginusersmap/mappeduser/{parentId}")
	public List<UsersMappingVo> getMappedUsersByParentId(@PathVariable("parentId") long parentId) {
		List<UsersMappingVo> loginUser = userService.getMappedUserByParentId(parentId, true);
		return loginUser;
	}

	@GetMapping("/userByRole/{organisationId}/{role}")
	public List<LoginUsersResponseVo> getClientAdminByOrganistionId(@PathVariable("organisationId") long organisationId,
			@PathVariable("role") String role) {
		List<LoginUsersResponseVo> loginUser = userService.getUserByRoleAndOrganizationId(organisationId, role);
		return loginUser;
	}

	@GetMapping("/loginusersmap/unmappeduser/{organisationId}/{role}")
	public List<UsersMappingVo> getUnMappedUsersByOrganisationId(
			@PathVariable("organisationId") long organisationId, @PathVariable("role") String role) {
		List<UsersMappingVo> loginUser = userService.getUnMappedUserByOganisationIDAndRole(organisationId,role, false);
		return loginUser;
	}
	
}
