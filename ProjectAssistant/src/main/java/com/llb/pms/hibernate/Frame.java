package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="frame")
 public class Frame implements Serializable {

    private Integer id;
    private String name;
    private Frametype frametype;
    private String note;
    private Set<Lib> libs=new HashSet<Lib>();
    private Set<Suite> suites=new HashSet<Suite>();
    private Set<Template> templates=new HashSet<Template>();


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
    @JoinColumn(name="ftId")
    public Frametype getFrametype() {

		return this.frametype;
  	}

    public void setFrametype(Frametype frametype) {

		this.frametype=frametype;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}

    @OneToMany(mappedBy="frame")
    public Set<Lib> getLibs() {

		return this.libs;
  	}

    public void setLibs(Set<Lib> libs) {

		this.libs=libs;
  	}

    @OneToMany(mappedBy="frame")
    public Set<Suite> getSuites() {

		return this.suites;
  	}

    public void setSuites(Set<Suite> suites) {

		this.suites=suites;
  	}

    @OneToMany(mappedBy="frame")
    public Set<Template> getTemplates() {

		return this.templates;
  	}

    public void setTemplates(Set<Template> templates) {

		this.templates=templates;
  	}


 }