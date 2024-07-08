package com.asset.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asset.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	public List<Menu> findByRoleOrderByIdAsc(String roleName);
	
	public List<Menu> findAllByOrderByIdAsc();
}
