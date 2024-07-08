package com.asset.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.entity.UserMapping;
import com.asset.repositories.InstrumentMappingRepository;
import com.asset.repositories.InstrumentRepository;
import com.asset.repositories.UsersMappingRepository;
import com.asset.repositories.UsersRepository;
import com.asset.service.UserMappingService;

@Service
@Transactional
public class UserMappingServiceImpl implements UserMappingService {
	@Autowired
	UsersMappingRepository usersMappingRepository;
	
	@Autowired
	InstrumentMappingRepository instrumentMappingRepository;

	@Autowired
	InstrumentRepository instrumentRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	public List<UserMapping> findAll() {
		return usersMappingRepository.findAll();
	}

	public void save(UserMapping userMapping) {
		usersMappingRepository.save(userMapping);
		usersRepository.updateParentIdForMapping(userMapping.getClientAdminId(), userMapping.getUserid(), true);
	}
	public void deleteByUserid(long Userid)
	{
		usersMappingRepository.deleteByUserid(Userid);
		usersRepository.updateParentIdForMapping(0, Userid, false);
		instrumentMappingRepository.deleteByUserid(Userid);
		instrumentRepository.updateUseridForUnMapping(Userid);
	}
	

}
