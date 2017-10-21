package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="template")
 public class Template implements Serializable {

    private Integer id;
    private String name;
    private String path;
    private Frame frame;
    private String className;
    private String note;
    private Set<Variable> variables=new HashSet<Variable>();


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

    @Column(name = "path")
    public String getPath() {

		return this.path;
  	}

    public void setPath(String path) {

		this.path=path;
  	}

    @ManyToOne
    @JoinColumn(name="fid")
    public Frame getFrame() {

		return this.frame;
  	}

    public void setFrame(Frame frame) {

		this.frame=frame;
  	}

    @Column(name = "className")
    public String getClassName() {

		return this.className;
  	}

    public void setClassName(String className) {

		this.className=className;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}

    @OneToMany(mappedBy="template")
    public Set<Variable> getVariables() {

		return this.variables;
  	}

    public void setVariables(Set<Variable> variables) {

		this.variables=variables;
  	}


 }