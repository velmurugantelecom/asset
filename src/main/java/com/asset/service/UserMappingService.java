package com.asset.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.asset.entity.UserMapping;

@Component
public interface UserMappingService {
	public List<UserMapping> findAll();

	public void save(UserMapping userMapping);
	
	public void deleteByUserid(long Userid);
}
