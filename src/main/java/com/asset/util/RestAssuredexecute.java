package com.asset.util;

//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;

public class RestAssuredexecute {
	 private static String requestBody = "{\n" +
	            "  \"title\": \"foo\",\n" +
	            "  \"body\": \"baz\",\n" +
	            "  \"userId\": \"1\",\n" +
	            "  \"id\": \"1\" \n}";
	 
	public  String sendSMS()  {
		//RestAssured.baseURI ="https://samples.openweathermap.org/data/2.5/"; 
		// RequestSpecification request = RestAssured.given();
		 
		// Response response = request.queryParam("q", "London,UK") 
		 //                   .queryParam("appid", "2b1fd2d7f77ccf1b7de9b441571b39b8") 
	//	                    .get("/weather");
		 
		// String jsonString = response.asString();
		// System.out.println(response.getStatusCode()); 
//		 Assert.assertEquals(jsonString.contains("London"), true);
		 return null;
	
	}

}
