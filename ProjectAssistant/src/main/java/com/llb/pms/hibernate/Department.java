package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="department")
 public class Department implements Serializable {

    private Integer id;
    private String name;
    private Employee employee;
    private String tel;
    private String note;
    private Set<Employee> employees=new HashSet<Employee>();


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
    @JoinColumn(name="eid")
    public Employee getEmployee() {

		return this.employee;
  	}

    public void setEmployee(Employee employee) {

		this.employee=employee;
  	}

    @Column(name = "tel")
    public String getTel() {

		return this.tel;
  	}

    public void setTel(String tel) {

		this.tel=tel;
  	}

    @Column(name = "note")
    public String getNote() {

		return this.note;
  	}

    public void setNote(String note) {

		this.note=note;
  	}

    @OneToMany(mappedBy="department")
    public Set<Employee> getEmployees() {

		return this.employees;
  	}

    public void setEmployees(Set<Employee> employees) {

		this.employees=employees;
  	}


 }