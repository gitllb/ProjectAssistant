package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="permit")
 public class Permit implements Serializable {

    private Integer id;
    private String operation;
    private String description;
    private String tableName;
    private Set<Role> roles=new HashSet<Role>();


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)//strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
    public Integer getId() {

		return this.id;
  	}

    public void setId(Integer id) {

		this.id=id;
  	}

    @Column(name = "operation")
    public String getOperation() {

		return this.operation;
  	}

    public void setOperation(String operation) {

		this.operation=operation;
  	}

    @Column(name = "tableName")
    public String getTableName() {

		return this.tableName;
  	}

    public void setTableName(String tableName) {

		this.tableName=tableName;
  	}
    
    
    @Column(name = "description")
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany( targetEntity=com.llb.pms.hibernate.Role.class, mappedBy = "permits", cascade={CascadeType.PERSIST, CascadeType.MERGE} )
   
    public Set<Role> getRoles() {

		return this.roles;
  	}

    

	public void setRoles(Set<Role> roles) {

		this.roles=roles;
  	}


 }