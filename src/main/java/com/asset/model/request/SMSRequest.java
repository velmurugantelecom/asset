package com.asset.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SMSRequest {
	
	public String route;
	public String message;
	public String language;
	public long flash;
	public String numbers;
	

}
