package com.asset.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="InstrumentHistory")
public class InstrumentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sNo;
    private String description;
    private String operation;
	
    @JsonFormat(pattern="dd-MM-yyyy")
    @CreatedDate
    @Column(name = "createdDate",updatable = false)
	private Date createdDate;
    
}
