package com.asset.scheduler;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.asset.entity.CalibrationRequest;
import com.asset.entity.PurchaseRequest;
import com.asset.repositories.CalibrationRequestRepository;
import com.asset.repositories.PurchaseRequestRepository;
import com.asset.service.SendSMSService;

@Component
@Transactional
public class CalibrationRequestScheduledTask {

	@Value("${sms.send.status}")
	private String smsSendStatus;

	@Autowired
	private SendSMSService sendSMSService;

	@Autowired
	private CalibrationRequestRepository calibrationRequestRepository;

	@Scheduled(cron = "${cron.calibration.request}")
    public void sendSmsCalibrationRequest() {
    
    		if (smsSendStatus.equalsIgnoreCase("on")) {
    			List<CalibrationRequest> calibrationRequestList = calibrationRequestRepository.findAllCalibrationRequestByCreatedDate();
    			for (int calibrationRequest = 0; calibrationRequest < calibrationRequestList.size(); calibrationRequest++) {
    				if (calibrationRequestList.get(calibrationRequest).getOrganization() != null) {
    					String message = "New Calibration Request from the organization "
    							+ calibrationRequestList.get(calibrationRequest).getOrganization().getName() == null
    									? ""
    									: calibrationRequestList.get(calibrationRequest).getOrganization().getName()
    											+ "\nCalibration ID : "
    											+ calibrationRequestList.get(calibrationRequest).getCalibrationId()
    											+ "\nInstrument Name : "
    											+ calibrationRequestList.get(calibrationRequest).getInstrumentName();
    					System.out.println(message);
    					sendSMSService.sendSMS(message);
    					calibrationRequestRepository
    							.updatePurchaseRequestSmsSent(calibrationRequestList.get(calibrationRequest).getCalibrationId());
    				}
    			}
    		}
    	}

}
