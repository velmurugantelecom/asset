package com.asset.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "dummy")
public class Suppliers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	private String name;
	private String gender;
	private String mailId;

    @JsonFormat(pattern="dd-MM-yyyy")
    @CreatedDate
	@Column(name = "createdDate",updatable = false)
    private Date createdDate;
}
