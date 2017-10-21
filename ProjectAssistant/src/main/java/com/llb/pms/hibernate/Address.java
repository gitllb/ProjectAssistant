package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="address")
 public class Address implements Serializable {

    private Integer id;
    private String site;
    private Employee employee;
    private String note;


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)//strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
    public Integer getId() {

		return this.id;
  	}

    public void setId(Integer id) {

		this.id=id;
  	}

    @Column(name = "site")
    public String getSite() {

		return this.site;
  	}

    public void setSite(String site) {

		this.site=site;
  	}

    @ManyToOne
    @JoinColumn(name="eid")
    public Employee getEmployee() {

		return this.employee;
  	}

    public void setEmployee(Employee employee) {

		this.employee=employee;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}


 }