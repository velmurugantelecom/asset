package com.asset.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

	public synchronized static String uniqueIdOID() {
		String uniqueID1 = java.time.Clock.systemUTC().instant().toString().replace(":", "").replace("-", "")
				.replace(".", "").replace("-", "").replace("Z", "").replace("T", "");
		Random random = new Random();
		long randomInteger = Math.abs(random.nextLong());
		String uniqueId2 = String.valueOf(randomInteger);
		Random random1 = new Random();
		long randomInteger1 = Math.abs(random1.nextLong());
		String uniqueId3 = String.valueOf(randomInteger1);
		String uniqueId = uniqueID1 + uniqueId2.substring(2, 5)+uniqueId3.substring(1,4);
		return uniqueId;
	}

	public synchronized static String uniqueIdIID() {
		String uniqueID1 = java.time.Clock.systemUTC().instant().toString().replace(":", "").replace("-", "")
				.replace(".", "").replace("-", "").replace("Z", "").replace("T", "");
		Random random = new Random();
		long randomInteger = Math.abs(random.nextLong());
		String uniqueId2 = String.valueOf(randomInteger);
		Random random1 = new Random();
		long randomInteger1 = Math.abs(random1.nextLong());
		String uniqueId3 = String.valueOf(randomInteger1);
		String uniqueId = uniqueID1 + uniqueId2.substring(2, 5)+uniqueId3.substring(1,4);
		return uniqueId;
	}
	public synchronized static String uniquePRID() {
		String uniqueID1 = java.time.Clock.systemUTC().instant().toString().replace(":", "").replace("-", "")
				.replace(".", "").replace("-", "").replace("Z", "").replace("T", "");
		Random random = new Random();
		long randomInteger = Math.abs(random.nextLong());
		String uniqueId2 = String.valueOf(randomInteger);
		Random random1 = new Random();
		long randomInteger1 = Math.abs(random1.nextLong());
		String uniqueId3 = String.valueOf(randomInteger1);
		String uniqueId = uniqueID1 + uniqueId2.substring(2, 5)+uniqueId3.substring(1,4);
		return uniqueId;
	}
	public synchronized static String uniqueCRID() {
		String uniqueID1 = java.time.Clock.systemUTC().instant().toString().replace(":", "").replace("-", "")
				.replace(".", "").replace("-", "").replace("Z", "").replace("T", "");
		Random random = new Random();
		long randomInteger = Math.abs(random.nextLong());
		String uniqueId2 = String.valueOf(randomInteger);
		Random random1 = new Random();
		long randomInteger1 = Math.abs(random1.nextLong());
		String uniqueId3 = String.valueOf(randomInteger1);
		String uniqueId = uniqueID1 + uniqueId2.substring(2, 5)+uniqueId3.substring(1,4);
		return uniqueId;
	}
	public static String totalCountAPI(Long calib, Long instrumentCount, Long instrumentHistoryCount, Long menuCount,
			Long organizationnCount, Long purchaseRequesnCount, Long usernCount) {
		JSONObject josonObjCount = new JSONObject();
		try {
			josonObjCount.put("calibrationCount", calib.toString());
			josonObjCount.put("instrumentCount", instrumentCount.toString());
			josonObjCount.put("instrumentHistoryCount", instrumentHistoryCount.toString());
			josonObjCount.put("menuCount", menuCount.toString());
			josonObjCount.put("organizationnCount", organizationnCount.toString());
			josonObjCount.put("purchaseRequesnCount", purchaseRequesnCount.toString());
			josonObjCount.put("usernCount", usernCount.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return josonObjCount.toString();
	}

	public static Date dateFormat(Date dateTime) {
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(dateTime);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			date = formatter1.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String dateFormatType1(Date dateTime) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(dateTime);
	}

}
