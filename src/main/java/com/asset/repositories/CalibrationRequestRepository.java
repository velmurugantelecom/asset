package com.asset.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.entity.CalibrationRequest;
import com.asset.entity.PurchaseRequest;
@Transactional
public interface CalibrationRequestRepository extends JpaRepository<CalibrationRequest, Long> {
	
	@Query("SELECT c FROM CalibrationRequest c WHERE c.createdDate > CURRENT_DATE-1 and c.isSmsSent=false")
	public List<CalibrationRequest> findAllCalibrationRequestByCreatedDate();
	
	@Modifying
	@Query("update CalibrationRequest c set c.isSmsSent=true  where c.calibrationId=:calibrationId")
	void updatePurchaseRequestSmsSent(@Param("calibrationId") String calibrationId);
	
	@Query("SELECT p FROM CalibrationRequest  p WHERE p.userid IN (:userList)")
	public List<CalibrationRequest> findAllByMultipleUserId(@Param("userList") List<Long> userList);
	

	public List<CalibrationRequest> findAllByUserid(long userId);

}
