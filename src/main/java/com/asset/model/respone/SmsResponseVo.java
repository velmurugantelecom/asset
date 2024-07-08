package com.asset.model.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SmsResponseVo {
	
	  private String request_id;
	  private boolean returnBoolean;
	  private String message;
	 
}
