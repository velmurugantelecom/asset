package com.asset.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asset.entity.LoginUsers;
import com.asset.model.respone.LoginUsersResponseVo;
import com.asset.model.respone.UsersMappingVo;
import com.asset.repositories.UsersRepository;
import com.asset.service.UserService;

@Service
@Transactional
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository usersRepository;


	public List<LoginUsers> findAll() {
		return usersRepository.findAllActiveUsers();
	}

	public Optional<LoginUsers> findById(long id) {
		return usersRepository.findById(id);
	}

	public void save(LoginUsers users) {
		usersRepository.save(users);
	}

	public boolean update(LoginUsers users, long id) {
		Optional<LoginUsers> organizationData = usersRepository.findById(id);
		if (organizationData.isPresent()) {
			users.setID(id);
			usersRepository.save(users);
			return true;
		}
		return false;
	}

	public void deleteById(long id) {
		usersRepository.deleteById(id);
	}

	public void deleteAll() {
		usersRepository.deleteAll();
	}

	public List<UsersMappingVo> getMappedUserByParentId(long parentId, boolean mapped) {
		return usersRepository.getMappedUserByParentId(parentId, mapped);

	}

	public LoginUsers getBasedOnUserNamePassword(String username, String password) {
		return usersRepository.findByUsernameAndPassword(username, password);
	}

	public LoginUsers getBasedOnMailPassword(String email, String password) {
		return usersRepository.findByEmailAndPassword(email, password);
	}
	public List<LoginUsersResponseVo> getUserByRoleAndOrganizationId(long organisationID, String role) {
		return usersRepository.getUserByRoleAndOrganizationId(organisationID, role);
	}

	public List<UsersMappingVo> getUnMappedUserByOganisationIDAndRole(long organisationId, String role,
			boolean mapped) {
		return usersRepository.getUnMappedUserByOganisationIDAndRole(organisationId, role, false);

	}

	public long findTotalOUserService() {
		return usersRepository.count();
	}

	
	public Long getEmailAndPassword(String email, String password) {
	
		return usersRepository.findEmailPasswordUser(email, password);
	}


}
