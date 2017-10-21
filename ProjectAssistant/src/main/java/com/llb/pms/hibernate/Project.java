package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="project")
 public class Project implements Serializable {

    private Integer id;
    private String name;
    private String path;
    private Integer display;
    private String feasibleReport;
    private String requirementAnalysis;
    private String generalDesign;
    private String detailDesign;
    private User user;
    private Date createDate;
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

    @Column(name = "path")
    public String getPath() {

		return this.path;
  	}

    public void setPath(String path) {

		this.path=path;
  	}

    @Column(name = "display")
    public Integer getDisplay() {

		return this.display;
  	}

    public void setDisplay(Integer display) {

		this.display=display;
  	}

    @Column(name = "feasibleReport")
    public String getFeasibleReport() {

		return this.feasibleReport;
  	}

    public void setFeasibleReport(String feasibleReport) {

		this.feasibleReport=feasibleReport;
  	}

    @Column(name = "requirementAnalysis")
    public String getRequirementAnalysis() {

		return this.requirementAnalysis;
  	}

    public void setRequirementAnalysis(String requirementAnalysis) {

		this.requirementAnalysis=requirementAnalysis;
  	}

    @Column(name = "generalDesign")
    public String getGeneralDesign() {

		return this.generalDesign;
  	}

    public void setGeneralDesign(String generalDesign) {

		this.generalDesign=generalDesign;
  	}

    @Column(name = "detailDesign")
    public String getDetailDesign() {

		return this.detailDesign;
  	}

    public void setDetailDesign(String detailDesign) {

		this.detailDesign=detailDesign;
  	}

    @ManyToOne
    @JoinColumn(name="uid")
    public User getUser() {

		return this.user;
  	}

    public void setUser(User user) {

		this.user=user;
  	}

    @Column(name = "createDate")
    public Date getCreateDate() {

		return this.createDate;
  	}

    public void setCreateDate(Date createDate) {

		this.createDate=createDate;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}

    @ManyToMany( targetEntity=com.llb.pms.hibernate.Lib.class, mappedBy = "projects",  cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    public Set<Lib> getLibs() {

		return this.libs;
  	}

    public void setLibs(Set<Lib> libs) {

		this.libs=libs;
  	}

    @ManyToMany( targetEntity=com.llb.pms.hibernate.Suite.class, cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable( name="projectsuite", joinColumns=@JoinColumn(name="pid"), inverseJoinColumns=@JoinColumn(name="sid"))
    public Set<Suite> getSuites() {

		return this.suites;
  	}

    public void setSuites(Set<Suite> suites) {

		this.suites=suites;
  	}


 }