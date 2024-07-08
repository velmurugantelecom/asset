package com.asset.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.entity.PurchaseRequest;
@Transactional
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
	
	@Query("SELECT p FROM PurchaseRequest  p WHERE p.createdDate > CURRENT_DATE-1  and p.isSmsSent=false")
	public List<PurchaseRequest> findAllPurchaseRequestByCreatedDate();
	
	@Modifying
	@Query("update PurchaseRequest p set p.isSmsSent=true where p.purchaseId=:purchaseId")
	void updatePurchaseRequestSmsSent(@Param("purchaseId") String purchaseId);
	
	
	@Query("SELECT p FROM PurchaseRequest  p WHERE p.userid IN (:userList)")
	public List<PurchaseRequest> findAllByMultipleUserId(@Param("userList") List<Long> userList);
	

	public List<PurchaseRequest> findAllByUserid(long userId);
}
