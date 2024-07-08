package com.asset.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.asset.model.request.SMSRequest;
import com.asset.model.respone.SmsResponseVo;
import com.asset.service.SendSMSService;


@Service
@Transactional
@Component
public class SendSMSServiceImpl implements SendSMSService {

    @Autowired
    RestTemplate restTemplate;
	 	
	@Value("${sms.url}")
	private String smsURL;
	
	@Value("${sms.authorization}")
	private String smsAuthorization;
	
	@Value("${sms.route}")
	private String smsRoute;
	
	@Value("${sms.content.type}")
	private String smsContentType;
	
	@Value("${sms.language}")
	private String smsLanguage;
	
	@Value("${sms.flash}")
	private long smsFlash;
	
	@Value("${sms.to.numbers}")
	private String smsTonumbers;

    
	public SmsResponseVo sendSMS(String Message,String mobileNumbers) {
		SmsResponseVo smsResponseVo=null;
		URI uri=null;
		try {
			uri = new URI(smsURL);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.set("authorization", smsAuthorization);    
		headers.set("Content-Type", smsContentType);  
		SMSRequest smsRequest=new SMSRequest();
		smsRequest.setFlash(smsFlash);
		smsRequest.setLanguage(smsLanguage);
		smsRequest.setMessage(Message);
		smsRequest.setNumbers(mobileNumbers);
		smsRequest.setRoute(smsRoute);
		HttpEntity<SMSRequest> httpRequest = new HttpEntity<>(smsRequest, headers);
	    ResponseEntity<String> result = restTemplate.postForEntity(uri, httpRequest, String.class);
	    try {
	    	smsResponseVo=new SmsResponseVo();
	        JSONObject jsonObject = new JSONObject(result.getBody());
	        boolean returnBoolean=jsonObject.getBoolean("return");
	        String referenceId=jsonObject.getString("request_id");
	        String message=jsonObject.getString("message");
	        smsResponseVo.setRequest_id(referenceId);
	        smsResponseVo.setReturnBoolean(returnBoolean);
	        smsResponseVo.setMessage(message);
	        
	   }catch (JSONException err){
		   err.printStackTrace();
	   }
	    
	   return smsResponseVo;

	}

	public SmsResponseVo sendSMS(String Message) {
		SmsResponseVo smsResponseVo=null;
		URI uri=null;
		try {
			uri = new URI(smsURL);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.set("authorization", smsAuthorization);    
		headers.set("Content-Type", smsContentType);  
		SMSRequest smsRequest=new SMSRequest();
		smsRequest.setFlash(smsFlash);
		smsRequest.setLanguage(smsLanguage);
		smsRequest.setMessage(Message);
		smsRequest.setNumbers(smsTonumbers);
		smsRequest.setRoute(smsRoute);
		HttpEntity<SMSRequest> httpRequest = new HttpEntity<>(smsRequest, headers);
	    ResponseEntity<String> result = restTemplate.postForEntity(uri, httpRequest, String.class);
	    try {
	    	smsResponseVo=new SmsResponseVo();
	        JSONObject jsonObject = new JSONObject(result.getBody());
	        boolean returnBoolean=jsonObject.getBoolean("return");
	        String referenceId=jsonObject.getString("request_id");
	        String message=jsonObject.getString("message");
	        smsResponseVo.setRequest_id(referenceId);
	        smsResponseVo.setReturnBoolean(returnBoolean);
	        smsResponseVo.setMessage(message);
	        
	   }catch (JSONException err){
		   err.printStackTrace();
	   }
	    
	   return smsResponseVo;

	}
}
