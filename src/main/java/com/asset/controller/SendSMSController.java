package com.asset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.model.request.SmsVo;
import com.asset.model.respone.SmsResponseVo;
import com.asset.service.SendSMSService;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class SendSMSController {

	@Autowired
	SendSMSService sendSMSService;

	
	@PostMapping("/sendSms")
	public ResponseEntity<SmsResponseVo> createInstrument(@RequestBody SmsVo smsVo,
			@RequestHeader(name = "authorization", required = true) String authorization,
			@RequestHeader(name = "Content-Type", required = true) String contentType) {
		try {
			SmsResponseVo smsResponseVo=sendSMSService.sendSMS(smsVo.getMessage());
			return new ResponseEntity<>(smsResponseVo,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
