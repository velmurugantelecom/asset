package com.asset.controller;

import com.asset.entity.LoginUsers;
import com.asset.entity.PurchaseRequest;
import com.asset.repositories.PurchaseRequestRepository;
import com.asset.repositories.UsersRepository;
import com.asset.service.PurchaseRequestService;
import com.asset.util.Constants;
import com.asset.util.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class PurchaseRequestController {
    @Autowired
    PurchaseRequestService purchaseRequestService;
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    PurchaseRequestRepository purchaseRequestRepository;

    @GetMapping("/purchaserequest")
    public ResponseEntity<List<PurchaseRequest>> getAllPurchaseRequest() {
        try {
            List<PurchaseRequest> purchaseRequestData = new ArrayList<PurchaseRequest>();
            purchaseRequestService.findAll().forEach(purchaseRequestData::add);
//            if (purchaseRequestData.isEmpty()) {
//                return new ResponseEntity<>(purchaseRequestData,HttpStatus.NO_CONTENT);
//            }
            return new ResponseEntity<>(purchaseRequestData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  //Test
    @GetMapping("/purchaserequest/{userId}")
    public ResponseEntity<List<PurchaseRequest>> getAllPurchaseRequestByUserId(@PathVariable("userId") long userId) {
        try {
        	 List<PurchaseRequest> purchaseRequestData = new ArrayList<PurchaseRequest>();
        	Optional<LoginUsers> userData = usersRepository.findById(userId);
          	if (userData.isPresent()) {
        		if (userData.get().getRole().equalsIgnoreCase(Constants.CLIENT_ADMIN)) {        			
        			List<Long> userIdList= usersRepository.getAllUserByParentId(userId);
        			purchaseRequestRepository.findAllByMultipleUserId(userIdList).forEach(purchaseRequestData::add);
    			} else if (userData.get().getRole().equalsIgnoreCase(Constants.INSTRUMENT_INCHARGE)) {
    				purchaseRequestRepository.findAllByUserid(userId).forEach(purchaseRequestData::add);
    			} 
    			else {
    				 purchaseRequestService.findAll().forEach(purchaseRequestData::add);
    			}
    		}
            return new ResponseEntity<>(purchaseRequestData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/purchaserequest/{id}")
    public ResponseEntity<PurchaseRequest> getPurchaseRequestById(@PathVariable("id") long id) {
        Optional<PurchaseRequest> purchaseRequestData = purchaseRequestService.findById(id);
        if (purchaseRequestData.isPresent()) {
            return new ResponseEntity<>(purchaseRequestData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//Test
    @PostMapping("/purchaserequest/{userId}")
    public ResponseEntity<PurchaseRequest> createPurchaseRequest(@RequestBody PurchaseRequest purchaseRequestData,@PathVariable("userId") long userId) {
        try {
        	Optional<LoginUsers> userData = usersRepository.findById(userId);
        	if (userData.isPresent()) {
        		purchaseRequestData.setOrganisationId(userData.get().getOrganisationId());
    		}
        	purchaseRequestData.setPurchaseId("PR"+Utility.uniquePRID());
            purchaseRequestService.save(purchaseRequestData);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/purchaserequest/{id}")
    public ResponseEntity<PurchaseRequest> updatePurchaseRequest(@PathVariable("id") long id, @RequestBody PurchaseRequest purchaseRequestData) {
        if (purchaseRequestService.update(purchaseRequestData, id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/purchaserequest/{id}")
    public ResponseEntity<HttpStatus> deletePurchaseRequest(@PathVariable("id") long id) {
        try {
            purchaseRequestService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/purchaserequest")
    public ResponseEntity<HttpStatus> deleteAllPurchaseRequest() {
        try {
            purchaseRequestService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
