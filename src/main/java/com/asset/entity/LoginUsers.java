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
@Table(name="Users")
public class LoginUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String name;
    private String username;
    private String password;
    private String organization_name;
    private String designation;
    private String email;
    private String contact_no;
    private String role;
    private boolean isUserMapped;
    private long organisationId;
    private boolean isDeleted;
    private long parentUserId;
    @JsonFormat(pattern="dd-MM-yyyy")
    @CreatedDate
    @Column(name = "createdDate",updatable = false)
	private Date createdDate;
    @ManyToOne
 	@NotFound(action = NotFoundAction.IGNORE)
 	@JoinColumn(name = "organisationId", referencedColumnName = "ID", insertable = false, updatable = false, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
 	private Organizations organization;
    
}
