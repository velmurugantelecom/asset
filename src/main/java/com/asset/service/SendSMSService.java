package com.asset.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.asset.model.respone.SmsResponseVo;


@Component
@Transactional
public interface SendSMSService {
	
	public SmsResponseVo sendSMS(String message);

	public SmsResponseVo sendSMS(String Message,String mobileNumbers);
}
