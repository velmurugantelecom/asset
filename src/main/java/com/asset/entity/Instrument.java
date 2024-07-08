package com.asset.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "Instrument")
public class Instrument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idNo;
	private boolean isInstrumentMapped;
	private String iIN;
	private String instrumentName;
	private String make;
	private long modelNo;
	private String range;
	private String type;
	private String accuracy;
	private String discipline;
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date purchaseDate;
    private long serialNumber;
	private String instrumentLocation;
	private String other;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date calibrationDate;
	private long instrumentId;
	private long userid;
	private boolean isMailSent;
	private long organizationId;
	private String calibrationLaboratory;
	private String labLicenceNo;
	private String locationCalibrated;
	private String scopeOfCalibration;
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date lastCalibrationDate;
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date calibratedDate;
	private String reasonsForCalibration;
	private String nablCertificateNumber;
	private String calibrationInterval;
	private String instrumentStatus;
	
	@ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "organizationId", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private Organizations organization;
	private long clientAdminId;
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "clientAdminId", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private LoginUsers loginClientAdmin;
    @JsonFormat(pattern="dd-MM-yyyy")
	@CreatedDate
	@Column(name = "createdDate",updatable = false)
    private Date createdDate;
	@OneToMany(targetEntity = InstrumentHistory.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idNo", referencedColumnName = "idNo")
	private List<InstrumentHistory> instrumentHistoryList;
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "userid", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private LoginUsers loginUser;
	
	
	
}
