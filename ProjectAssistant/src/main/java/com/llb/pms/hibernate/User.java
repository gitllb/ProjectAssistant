package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="user")
 public class User implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private Boolean sex;
    private String email;
    private String note;
    private Set<Project> projects=new HashSet<Project>();
    private Set<Role> roles=new HashSet<Role>();


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

    @Column(name = "password")
    public String getPassword() {

		return this.password;
  	}

    public void setPassword(String password) {

		this.password=password;
  	}

    @Column(name = "sex")
    public Boolean getSex() {

		return this.sex;
  	}

    public void setSex(Boolean sex) {

		this.sex=sex;
  	}

    @Column(name = "email")
    public String getEmail() {

		return this.email;
  	}

    public void setEmail(String email) {

		this.email=email;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}

    @OneToMany(mappedBy="user")
    public Set<Project> getProjects() {

		return this.projects;
  	}

    public void setProjects(Set<Project> projects) {

		this.projects=projects;
  	}

    @ManyToMany( targetEntity=com.llb.pms.hibernate.Role.class, mappedBy = "users",  cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    public Set<Role> getRoles() {

		return this.roles;
  	}

    public void setRoles(Set<Role> roles) {

		this.roles=roles;
  	}


 }