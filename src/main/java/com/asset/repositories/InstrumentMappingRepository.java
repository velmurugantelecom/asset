package com.asset.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.entity.InstrumentMapping;
@Transactional
public interface InstrumentMappingRepository extends JpaRepository<InstrumentMapping, Long> {
	
	public void deleteByInstrumentId(long instrumentId);
	
	public void deleteByUserid(long userid);

	
	@Modifying
	@Query("update InstrumentMapping u set u.clientAdminId = :clientAdminId where u.userid=:userId")
	void updateClientAdminIdForMapping(@Param("clientAdminId") long clientAdminId,@Param("userId") long userId);
	
	
}
