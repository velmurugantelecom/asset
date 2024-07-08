package com.asset.model.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstrumentVo {
	
	private long idNo;
	private String instrumentName;
	private String iIN;
}
