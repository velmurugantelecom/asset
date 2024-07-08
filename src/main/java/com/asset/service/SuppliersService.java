package com.asset.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.asset.entity.Suppliers;


@Component
public interface SuppliersService {
	public List<Suppliers> findAll();
	public Optional<Suppliers> findById(long Id);
	public void save(Suppliers user);
	public boolean update(Suppliers user, long id);
	public void deleteById(long id);
	public void deleteAll();
	public long findTotalSupplierService();
}