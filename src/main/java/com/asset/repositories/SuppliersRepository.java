package com.asset.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asset.entity.Suppliers;

public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {
	
}
