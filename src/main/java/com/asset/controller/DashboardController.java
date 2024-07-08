package com.asset.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.asset.entity.Instrument;
import com.asset.model.request.SMSRequest;
import com.asset.service.CalibrationRequestService;
import com.asset.service.DashboardRequestService;
import com.asset.service.InstrumentHistoryService;
import com.asset.service.InstrumentService;
import com.asset.service.MenuService;
import com.asset.service.OrganizationsService;
import com.asset.service.PurchaseRequestService;
import com.asset.service.SuppliersService;
import com.asset.service.UserService;
import com.asset.util.RestAssuredexecute;
import com.asset.util.Utility;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class DashboardController {

	@Autowired
	CalibrationRequestService calibrationRequestService;

	@Autowired
	InstrumentService instrumentService;

	@Autowired
	InstrumentHistoryService instrumentHistoryService;

	@Autowired
	MenuService menuService;

	@Autowired
	OrganizationsService organizationsService;

	@Autowired
	PurchaseRequestService purchaseRequestService;

	
	@Autowired
	UserService userService;
	

	@RequestMapping(value = "/calibrationCount", method = RequestMethod.GET)
	public @ResponseBody Long calibrationCount() {
		return calibrationRequestService.findTotalCountCalibration();
	}

	@RequestMapping(value = "/instrumentCount", method = RequestMethod.GET)
	public @ResponseBody Long instrumentCount() {
		return instrumentService.findTotalInst();
	}

	@RequestMapping(value = "/instrumentHistoryCount", method = RequestMethod.GET)
	public @ResponseBody Long instrumentHistoryCount() {
		return instrumentHistoryService.findTotalInstHist();
	}

	@RequestMapping(value = "/menuCount", method = RequestMethod.GET)
	public @ResponseBody Long menuCount() {
		return menuService.findTotalMenu();
	}

	@RequestMapping(value = "/organizationsCount", method = RequestMethod.GET)
	public @ResponseBody Long organizationsCount() {
		return organizationsService.findTotalOrganization();
	}

	@RequestMapping(value = "/purchaseCount", method = RequestMethod.GET)
	public @ResponseBody Long purchaseCount() {
		return purchaseRequestService.findTotalPurchaseRequest();
	}

//	@RequestMapping(value = "/userCount", method = RequestMethod.GET)
//	public @ResponseBody Long userCount() {
//		return userService.findTotalSupplierService();
//	}

	@RequestMapping(value = "/apiCount", method = RequestMethod.GET)
	@ResponseBody
	public String totalApicount() {
		Long calibrationCount = calibrationRequestService.findTotalCountCalibration();
		Long instrumentCount =instrumentService.findTotalInst();
		Long instrumentHistoryCount =instrumentHistoryService.findTotalInstHist();
		Long menuCount =menuService.findTotalMenu();
		Long organizationnCount =organizationsService.findTotalOrganization();
		Long purchaseRequesnCount =purchaseRequestService.findTotalPurchaseRequest();
		Long usernCount =userService.findTotalOUserService();
		String countString = Utility.totalCountAPI(calibrationCount,instrumentCount,instrumentHistoryCount,menuCount,organizationnCount,purchaseRequesnCount,usernCount);
		return countString;
	}
	
}
