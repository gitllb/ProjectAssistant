package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="role")
 public class Role implements Serializable {

    private Integer id;
    private String name;
    private String note;
    private Set<Permit> permits=new HashSet<Permit>();
    private Set<User> users=new HashSet<User>();


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

    @ManyToMany( targetEntity=com.llb.pms.hibernate.Permit.class,  cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable( name="rolepermit", joinColumns=@JoinColumn(name="rid"), inverseJoinColumns=@JoinColumn(name="pid"))
    public Set<Permit> getPermits() {

		return this.permits;
  	}

    public void setPermits(Set<Permit> permits) {

		this.permits=permits;
  	}

    @ManyToMany( targetEntity=com.llb.pms.hibernate.User.class, cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable( name="userrole", joinColumns=@JoinColumn(name="rid"), inverseJoinColumns=@JoinColumn(name="uid"))
    public Set<User> getUsers() {

		return this.users;
  	}

    public void setUsers(Set<User> users) {

		this.users=users;
  	}


 }