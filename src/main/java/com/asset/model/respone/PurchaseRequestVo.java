package com.asset.model.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseRequestVo {

	private String purchaseId;
	private boolean isSmsSent;

}
