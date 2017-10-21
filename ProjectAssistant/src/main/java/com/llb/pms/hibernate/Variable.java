package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="variable")
 public class Variable implements Serializable {

    private Integer id;
    private String name;
    private String value;
    private Integer dataType;
    private Template template;
    private String note;


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

    @Column(name = "value")
    public String getValue() {

		return this.value;
  	}

    public void setValue(String value) {

		this.value=value;
  	}

    @Column(name = "dataType")
    public Integer getDataType() {

		return this.dataType;
  	}

    public void setDataType(Integer dataType) {

		this.dataType=dataType;
  	}

    @ManyToOne
    @JoinColumn(name="tid")
    public Template getTemplate() {

		return this.template;
  	}

    public void setTemplate(Template template) {

		this.template=template;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}


 }