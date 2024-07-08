package com.asset.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
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
@Table(name = "purchaserequest")
public class PurchaseRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;

	private String purchaseId;

	@Column(name = "instrumentName")
	private String instrumentName;

	@Column(name = "discipline")
	private String discipline;

	@Column(name = "groupData")
	private String groupData;

	@Column(name = "other")
	private String other;

	@Column(name = "purchaserequestStatus")
	private String purchaserequestStatus;

	private long organisationId;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "organisationId", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private Organizations organization;


	private long userid;
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "userid", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private LoginUsers instrumentIncharge;

	private boolean isSmsSent;

	private long parentId;

	private boolean isSmsSentSuccess;

	private String smsReferenceId;

	private String smsResponseMessage;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@CreatedDate
	@Column(name = "createdDate", updatable = false)
	private Date createdDate;
}
