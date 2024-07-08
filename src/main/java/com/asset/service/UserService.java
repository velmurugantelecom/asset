package com.asset.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import com.asset.entity.LoginUsers;
import com.asset.model.respone.LoginUsersResponseVo;
import com.asset.model.respone.UsersMappingVo;

@Component
public interface UserService {

	public List<LoginUsers> findAll();

	public Optional<LoginUsers> findById(long Id);

	public void save(LoginUsers users);

	public boolean update(LoginUsers users, long id);

	public void deleteById(long id);

	public void deleteAll();

	public List<UsersMappingVo> getMappedUserByParentId(long parentId, boolean mapped);

	public LoginUsers getBasedOnUserNamePassword(String username, String password);
	
	public LoginUsers getBasedOnMailPassword(String email, String password);

	public List<LoginUsersResponseVo> getUserByRoleAndOrganizationId(long organisationID, String role);
	
	public List<UsersMappingVo> getUnMappedUserByOganisationIDAndRole(long OrganisationId, String role,boolean mapped);

	public long findTotalOUserService();
	
	public Long getEmailAndPassword(String email, String password);

}
