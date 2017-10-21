package com.llb.pms.dao.impl;

import org.springframework.stereotype.Repository;

import com.llb.pms.dao.ProjectDao;
import com.llb.pms.hibernate.Project;
@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao<Project> {

	public ProjectDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	
}