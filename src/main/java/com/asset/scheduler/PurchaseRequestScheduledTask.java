package com.asset.scheduler;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.asset.entity.PurchaseRequest;
import com.asset.repositories.PurchaseRequestRepository;
import com.asset.service.SendSMSService;

@Component
@Transactional
public class PurchaseRequestScheduledTask {

	@Value("${sms.send.status}")
	private String smsSendStatus;

	@Autowired
	private SendSMSService sendSMSService;

	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;

	@Scheduled(cron = "${cron.purchase.request}")
	public void sendSmsPurchaseRequest() {
		if (smsSendStatus.equalsIgnoreCase("on")) {
			List<PurchaseRequest> purchaseRequestList = purchaseRequestRepository.findAllPurchaseRequestByCreatedDate();
			for (int purchaseRequest = 0; purchaseRequest < purchaseRequestList.size(); purchaseRequest++) {
				if (purchaseRequestList.get(purchaseRequest).getOrganization() != null) {
					String message = "New Purchase Request from the organization "
							+ purchaseRequestList.get(purchaseRequest).getOrganization().getName() == null
									? ""
									: purchaseRequestList.get(purchaseRequest).getOrganization().getName()
											+ "\nPurchase ID : "
											+ purchaseRequestList.get(purchaseRequest).getPurchaseId()
											+ "\nInstrument Name : "
											+ purchaseRequestList.get(purchaseRequest).getInstrumentName();
					System.out.println(message);
					sendSMSService.sendSMS(message);
					purchaseRequestRepository
							.updatePurchaseRequestSmsSent(purchaseRequestList.get(purchaseRequest).getPurchaseId());
				}
			}
		}
	}
}
