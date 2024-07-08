package com.asset.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class InstrumentMappingPK implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long organisationId;

    private long clientAdminId;

    private long userid;
    
    private long instrumentId;
    
}
