package com.asset.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
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
@IdClass(InstrumentMappingPK.class)
@Table(name = "InstrumentMapping")
public class InstrumentMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idNo;
	
    @Id
    private long organisationId;
    @OneToOne
   	@NotFound(action = NotFoundAction.IGNORE)
   	@JoinColumn(name = "organisationId", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
   	private Organizations organization;
   
    @Id
    private long clientAdminId;
    @OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "clientAdminId", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private LoginUsers clientAdmin;
   
    @Id
    private long userid;
    @OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "userid", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private LoginUsers instrumentIncharge;
    
    @Id
    private long instrumentId;
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "instrumentId", referencedColumnName = "idNo", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private Instrument instrument;
	
    @JsonFormat(pattern="dd-MM-yyyy")
	@CreatedDate
	@Column(name = "createdDate",updatable = false)
    private Date createdDate;
    
}
