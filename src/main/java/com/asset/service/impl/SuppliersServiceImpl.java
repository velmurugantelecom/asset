package com.asset.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.entity.Suppliers;
import com.asset.repositories.SuppliersRepository;
import com.asset.service.SuppliersService;

@Service
public class SuppliersServiceImpl implements SuppliersService {


	@Autowired
	SuppliersRepository userRepository;
	public List<Suppliers> findAll()
	{
		return userRepository.findAll();
	}
	public Optional<Suppliers> findById(long id)
	{
		return userRepository.findById(id);
	}
	public void save(Suppliers user)
	{
		 userRepository.save(user);
	}
	public boolean update(Suppliers user, long id)
	{
		Optional<Suppliers> userData = userRepository.findById(id);
		if (userData.isPresent()) {	
			user.setID(id);
			userRepository.save(user);
			return true;
		}
		return false;
	}
	public void deleteById(long id)
	{
		 userRepository.deleteById(id);
	}
	public void deleteAll()
	{
		 userRepository.deleteAll();
	}
	@Override
	public long findTotalSupplierService() {
		return userRepository.count();
	}
}
