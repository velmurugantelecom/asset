package com.asset.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="organization")
public class Organizations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String name;
    private String address;
    private String oin;
    private String email;
    private String landline;
    private String mobile;
    private String gst;

    @JsonFormat(pattern="dd-MM-yyyy")
    @CreatedDate
    @Column(name = "createdDate",updatable = false)
	private Date createdDate;
}
