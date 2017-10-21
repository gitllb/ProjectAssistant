package com.llb.pms.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llb.pms.controller.command.ListModel;
import com.llb.pms.dao.impl.SqlParameter;
import com.llb.pms.hibernate.Project;
import com.llb.pms.service.BaseService;
import com.llb.pms.service.ProjectService;


@Controller

public class ProjectController extends BaseController {
	@Resource
	private ProjectService<Project> projectService;
	
	@RequestMapping(value="/ajax/insertProject.do")
	@ResponseBody
	public Object insertProject(Project project,HttpServletRequest request,HttpServletResponse response){
		return this.insertByAjax(project, request, response);
	}
	
	@RequestMapping(value="/ajax/loadProjectContent.do")
	@ResponseBody
	public Object loadProjects(HttpServletRequest request,HttpServletResponse response){
		return this.projectService.loadProjectContent(request);
	}
	
	@RequestMapping(value="/ajax/loadProjectPage.do")
	@ResponseBody
	public String loadProjectPageJson(int index,int size,HttpServletRequest request,HttpServletResponse response){
		String sql="from project ";
		SqlParameter param=new SqlParameter(sql);
		param.setPage(index);
		param.setPagesize(size);
		List<Project> projects=this.projectService.query(param);
		return this.projectService.getObjectJSONString(projects);
	}
	
	@RequestMapping(value="/project/json/batchUpdate.do")
	@ResponseBody
	public String batchUpdateProjects(@RequestBody  Project[] projects){
		String result= this.batchUpdate(projects);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="/project/json/batchAdd.do")
	@ResponseBody
	public String batchInsertProjects(@RequestBody  Project[] projects){
		
		String result= this.batchInsert(projects);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="/project/json/batchDelete.do")
	@ResponseBody
	public String batchDeleteProjects(@RequestBody Project[] projects){
		String result=this.batchDelete(projects);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="testAjax.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String testAjax(HttpServletRequest request,HttpServletResponse response){
		String test=request.getParameter("test");
		
			return "中文测试！";
		
	}
	
	@RequestMapping(value="/project/json/query.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String query(@RequestBody  SqlParameter param,HttpServletResponse response){
		
		//String queryString ="from Project  where id = :value order by :sortName asc";
		this.resetQuerySqlParameter("Project", param);
		String pageJson=this.getProjectService().getPageBeanJSONByQuery(param);
		return  pageJson;
		
	}
	
	@RequestMapping(value = "/projectManager.do")
	
	public String toTableManger(
				HttpServletRequest request, HttpServletResponse response) {
			return "tableManager";
	}

	public ProjectService<Project> getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService<Project> projectService) {
		this.projectService = projectService;
	}

	@Override
	public BaseService getBaseService() {
		// TODO Auto-generated method stub
		return projectService;
	}

}