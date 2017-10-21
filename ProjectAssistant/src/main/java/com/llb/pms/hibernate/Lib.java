package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="lib")
 public class Lib implements Serializable {

    private Integer id;
    private String name;
    private String path;
    private Frame frame;
    private Techdomain techdomain;
    private String note;
    private Set<Suite> suites=new HashSet<Suite>();
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

    @ManyToMany( targetEntity=com.llb.pms.hibernate.Suite.class, cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable( name="libsuite", joinColumns=@JoinColumn(name="lid"), inverseJoinColumns=@JoinColumn(name="sid"))
    public Set<Suite> getSuites() {

		return this.suites;
  	}

    public void setSuites(Set<Suite> suites) {

		this.suites=suites;
  	}

    @ManyToMany( targetEntity=com.llb.pms.hibernate.Project.class, cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable( name="projectlib", joinColumns=@JoinColumn(name="lid"), inverseJoinColumns=@JoinColumn(name="pid"))
    public Set<Project> getProjects() {

		return this.projects;
  	}

    public void setProjects(Set<Project> projects) {

		this.projects=projects;
  	}


 }