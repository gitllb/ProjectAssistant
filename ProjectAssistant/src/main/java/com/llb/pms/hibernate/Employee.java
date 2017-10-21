package com.llb.pms.hibernate;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;


 @Entity
 @Table(name="employee")
 public class Employee implements Serializable {

    private Integer id;
    private String name;
    private Department department;
    private String position;
    private Integer grade;
    private Float salary;
    private Date hired;
    private Date fired;
    private Date birthday;
    private Boolean sex;
    private String tel;
    private String phone;
    private Integer qq;
    private String email;
    private String note;
    private Set<Address> addresss=new HashSet<Address>();
    private Set<Department> departments=new HashSet<Department>();


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
    @JoinColumn(name="did")
    public Department getDepartment() {

		return this.department;
  	}

    public void setDepartment(Department department) {

		this.department=department;
  	}

    @Column(name = "position")
    public String getPosition() {

		return this.position;
  	}

    public void setPosition(String position) {

		this.position=position;
  	}

    @Column(name = "grade")
    public Integer getGrade() {

		return this.grade;
  	}

    public void setGrade(Integer grade) {

		this.grade=grade;
  	}

    @Column(name = "salary")
    public Float getSalary() {

		return this.salary;
  	}

    public void setSalary(Float salary) {

		this.salary=salary;
  	}

    @Column(name = "hired")
    public Date getHired() {

		return this.hired;
  	}

    public void setHired(Date hired) {

		this.hired=hired;
  	}

    @Column(name = "fired")
    public Date getFired() {

		return this.fired;
  	}

    public void setFired(Date fired) {

		this.fired=fired;
  	}

    @Column(name = "birthday")
    public Date getBirthday() {

		return this.birthday;
  	}

    public void setBirthday(Date birthday) {

		this.birthday=birthday;
  	}

    @Column(name = "sex")
    public Boolean getSex() {

		return this.sex;
  	}

    public void setSex(Boolean sex) {

		this.sex=sex;
  	}

    @Column(name = "tel")
    public String getTel() {

		return this.tel;
  	}

    public void setTel(String tel) {

		this.tel=tel;
  	}

    @Column(name = "phone")
    public String getPhone() {

		return this.phone;
  	}

    public void setPhone(String phone) {

		this.phone=phone;
  	}

    @Column(name = "qq")
    public Integer getQq() {

		return this.qq;
  	}

    public void setQq(Integer qq) {

		this.qq=qq;
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

    @OneToMany(mappedBy="employee")
    public Set<Address> getAddresss() {

		return this.addresss;
  	}

    public void setAddresss(Set<Address> addresss) {

		this.addresss=addresss;
  	}

    @OneToMany(mappedBy="employee")
    public Set<Department> getDepartments() {

		return this.departments;
  	}

    public void setDepartments(Set<Department> departments) {

		this.departments=departments;
  	}


 }