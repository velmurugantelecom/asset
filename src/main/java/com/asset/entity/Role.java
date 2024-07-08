package com.asset.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name="Menu")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long menuId;
    private String title;
    private String routerLink;
    private String href;
    private String icon;
    private String target;
    private boolean hasSubMenu;
    private int parentId;
    private String role;
}
