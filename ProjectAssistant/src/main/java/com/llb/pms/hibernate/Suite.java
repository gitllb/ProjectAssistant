package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="suite")
 public class Suite implements Serializable {

    private Integer id;
    private String name;
    private Frame frame;
    private Techdomain techdomain;
    private String note;
    private Set<Lib> libs=new HashSet<Lib>();
    private Set<Project> projects=new HashSet<Project>();


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

    @ManyToOne
    @JoinColumn(name="fid")
    public Frame getFrame() {

		return this.frame;
  	}

    public void setFrame(Frame frame) {

		this.frame=frame;
  	}

    @ManyToOne
    @JoinColumn(name="tdId")
    public Techdomain getTechdomain() {

		return this.techdomain;
  	}

    public void setTechdomain(Techdomain techdomain) {

		this.techdomain=techdomain;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}

    @ManyToMany( targetEntity=com.llb.pms.hibernate.Lib.class, mappedBy = "suites",  cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    public Set<Lib> getLibs() {

		return this.libs;
  	}

    public void setLibs(Set<Lib> libs) {

		this.libs=libs;
  	}

    @ManyToMany( targetEntity=com.llb.pms.hibernate.Project.class, mappedBy = "suites",  cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    public Set<Project> getProjects() {

		return this.projects;
  	}

    public void setProjects(Set<Project> projects) {

		this.projects=projects;
  	}


 }