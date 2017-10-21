package com.llb.pms.dao.impl;

import org.springframework.stereotype.Repository;

import com.llb.pms.dao.PermitDao;
import com.llb.pms.hibernate.Permit;
@Repository("permitDao")
public class PermitDaoImpl extends BaseDaoImpl<Permit> implements PermitDao<Permit> {

	public PermitDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	
}