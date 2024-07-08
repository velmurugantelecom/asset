package com.asset.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.entity.PurchaseRequest;
import com.asset.repositories.PurchaseRequestRepository;
import com.asset.service.PurchaseRequestService;

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {


    @Autowired
    PurchaseRequestRepository purchaseRequestRepository;
    public List<PurchaseRequest> findAll()
    {
        return purchaseRequestRepository.findAll();
    }
    public Optional<PurchaseRequest> findById(long id)
    {
        return purchaseRequestRepository.findById(id);
    }
    public void save(PurchaseRequest Data)
    {
        purchaseRequestRepository.save(Data);
    }
    public boolean update(PurchaseRequest paramData, long id)
    {
        Optional<PurchaseRequest> PurchaseRequestData = purchaseRequestRepository.findById(id);
        if (PurchaseRequestData.isPresent()) {
            paramData.setID(id);
            purchaseRequestRepository.save(paramData);
            return true;
        }
        return false;
    }
    public void deleteById(long id)
    {
        purchaseRequestRepository.deleteById(id);
    }
    public void deleteAll()
    {
        purchaseRequestRepository.deleteAll();
    }
	@Override
	public long findTotalPurchaseRequest() {
		// TODO Auto-generated method stub
		return purchaseRequestRepository.count();
	}
}

