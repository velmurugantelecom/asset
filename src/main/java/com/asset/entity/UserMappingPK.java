package com.asset.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserMappingPK implements Serializable {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long organisationId;
    private long clientAdminId;
    private long userid;
}
