package com.asset.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.entity.LoginUsers;
import com.asset.model.UserDto;
import com.asset.model.respone.LoginUsersResponseVo;
import com.asset.model.respone.UsersMappingVo;
@Transactional
public interface UsersRepository extends JpaRepository<LoginUsers, Long> {

	@Query(value = "SELECT new com.asset.model.respone.UsersMappingVo(s.ID, s.name,s.email) FROM LoginUsers s where s.isUserMapped=:mapped and s.parentUserId=:parentId")
	List<UsersMappingVo> getMappedUserByParentId(@Param("parentId") long parentId, @Param("mapped") boolean mapped);

	public LoginUsers findByUsernameAndPassword(String username, String password);
	
	public LoginUsers findByEmailAndPassword(String email, String password);

	@Query("SELECT u FROM LoginUsers u WHERE u.isDeleted = FALSE")
	public List<LoginUsers> findAllActiveUsers();

	@Query(value = "SELECT new com.asset.model.respone.LoginUsersResponseVo(s.ID, s.name) FROM LoginUsers s where s.organisationId=:organisationID and s.role=:role")
	List<LoginUsersResponseVo> getUserByRoleAndOrganizationId(@Param("organisationID") long organisationID, @Param("role") String role);
	
	@Query(value = "SELECT new com.asset.model.respone.UsersMappingVo(s.ID, s.name,s.email) FROM LoginUsers s where s.isUserMapped=:mapped and s.organisationId=:organisationID and s.role=:role")
	List<UsersMappingVo> getUnMappedUserByOganisationIDAndRole(@Param("organisationID") long organisationID, @Param("role") String role,@Param("mapped") boolean mapped);

	@Modifying
	@Query("update LoginUsers u set u.parentUserId = :parentId, u.isUserMapped=:mapped where u.ID=:userId")
	void updateParentIdForMapping(@Param("parentId") long parentId,@Param("userId") long userId,@Param("mapped") boolean mapped);
	
	@Query(value = "SELECT new com.asset.model.UserDto(email,name) FROM LoginUsers Where ID=:userId")
	UserDto getUserDetails(@Param("userId") long userId);
	
	@Query(value = "SELECT ID FROM LoginUsers WHERE parentUserId = :parentId")
	List<Long> getAllUserByParentId(@Param("parentId") long parentId);
	
	@Query("SELECT COUNT(u) FROM LoginUsers u WHERE u.email=:email and u.password=:password")
	public Long findEmailPasswordUser(@Param("email") String email,@Param("password") String password);


}
