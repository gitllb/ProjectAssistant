package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="techdomain")
 public class Techdomain implements Serializable {

    private Integer id;
    private String name;
    private String note;
    private Set<Lib> libs=new HashSet<Lib>();
    private Set<Suite> suites=new HashSet<Suite>();


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)//strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
    public Integer getId() {

		return this.id;
  	}

    public void setId(Integer id) {

		this.id=id;
  	}

    @Column(name = "name")
    public String getName() {

		return this.name;
  	}

    public void setName(String name) {

		this.name=name;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}

    @OneToMany(mappedBy="techdomain")
    public Set<Lib> getLibs() {

		return this.libs;
  	}

    public void setLibs(Set<Lib> libs) {

		this.libs=libs;
  	}

    @OneToMany(mappedBy="techdomain")
    public Set<Suite> getSuites() {

		return this.suites;
  	}

    public void setSuites(Set<Suite> suites) {

		this.suites=suites;
  	}


 }