package com.llb.pms.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Service;

import com.llb.pms.dao.BaseDao;
import com.llb.pms.dao.ProjectDao;
import com.llb.pms.dao.impl.SqlParameter;
import com.llb.pms.hibernate.Project;
import com.llb.pms.service.ProjectService;
import com.llb.pms.util.RequestUtil;
import com.llb.pms.util.TreeNode;
@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService<Project>{
	@Resource
	private ProjectDao<Project> projectDao;

	public ProjectServiceImpl() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public BaseDao<Project> getBaseDao() {
		// TODO Auto-generated method stub

		return (BaseDao<Project>)this.getProjectDao();
	}

	public ProjectDao<Project> getProjectDao() {
		return this.projectDao;
	}

	public void setProjectDao(ProjectDao<Project> customerDao) {
		this.projectDao = projectDao;
	}
	
	@Override
	public void insert(Project project) {
		// TODO Auto-generated method stub
		
		String projectName=project.getName();
		//String projectPath=project.getPath()+"/"+projectName;
		String projectPath=this.getClass().getResource("/").getPath()+"/web-inf/projects/"+projectName;
		File projectFile=new File(projectPath);
		if(!projectFile.exists()){
			projectFile.mkdirs();
		}
		project.setPath(projectPath);
		super.insert(project);
	}

	public List<TreeNode> loadProjectContent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List nodes=new ArrayList();
		
		SqlParameter param=RequestUtil.getSqlParameter(request);
		String id="1";
		
			List<Project> projects=this.query(param);
			for(int i=0;i<projects.size();i++){
				Project project=projects.get(i);
				TreeNode n=new TreeNode();
				n.setId(id+i);
				n.setPid(id);
				n.setPath(project.getPath());
				n.setText(project.getName());
				n.setUrl("/Common/loadContent.do");
				n.addChild(n.createHidedNode(n));
				nodes.add(n);
			}
		
		return nodes;
	}

	public String getArrayJSONString(Object array) {
		// TODO Auto-generated method stub
		return null;
	}


}
