package com.llb.pms.dao.impl;

import org.springframework.stereotype.Repository;

import com.llb.pms.dao.UserDao;
import com.llb.pms.hibernate.User;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User> {

	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	
}