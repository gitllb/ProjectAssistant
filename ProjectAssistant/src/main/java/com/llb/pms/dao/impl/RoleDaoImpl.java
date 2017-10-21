package com.llb.pms.dao.impl;

import org.springframework.stereotype.Repository;

import com.llb.pms.dao.RoleDao;
import com.llb.pms.hibernate.Role;
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao<Role> {

	public RoleDaoImpl() {
		// TODO Auto-generated constructor stub
	}

}