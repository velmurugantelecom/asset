package com.asset.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asset.entity.UserMapping;
@Transactional
public interface UsersMappingRepository extends JpaRepository<UserMapping, Long> {

	public void deleteByUserid(long Userid);
	
	
}
