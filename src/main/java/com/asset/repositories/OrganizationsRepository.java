package com.asset.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asset.entity.Organizations;
import com.asset.model.respone.OrganizationVo;

public interface OrganizationsRepository extends JpaRepository<Organizations, Long> {

	   
	   @Query(value="SELECT new com.asset.model.respone.OrganizationVo(o.ID, o.name) FROM Organizations o")
	   List<OrganizationVo> getOrganizationNameAndId();
	   
	   
}
