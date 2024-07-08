package com.asset.service;

import com.asset.entity.PurchaseRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PurchaseRequestService {
    public List<PurchaseRequest> findAll();
    public Optional<PurchaseRequest> findById(long Id);
    public void save(PurchaseRequest purchaseRequest);
    public boolean update(PurchaseRequest purchaseRequest, long id);
    public void deleteById(long id);
    public void deleteAll();
    public long findTotalPurchaseRequest();
}
