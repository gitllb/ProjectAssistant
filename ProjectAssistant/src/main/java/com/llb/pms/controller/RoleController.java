package com.llb.pms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llb.pms.dao.impl.SqlParameter;
import com.llb.pms.hibernate.Role;
import com.llb.pms.service.BaseService;
import com.llb.pms.service.RoleService;
@Controller
public class RoleController extends BaseController{

	@Resource
	private RoleService roleService;
	
	@Override
	public BaseService getBaseService() {
		// TODO Auto-generated method stub
		return roleService;
	}
	
	

	@RequestMapping(value="/role/json/batchUpdate.do")
	@ResponseBody
	public String batchUpdateProjects(@RequestBody  Role[] roles){
		String result= this.batchUpdate(roles);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="/role/json/batchAdd.do")
	@ResponseBody
	public String batchInsertProjects(@RequestBody  Role[] roles){
		
		String result= this.batchInsert(roles);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="/role/json/batchDelete.do")
	@ResponseBody
	public String batchDeleteProjects(@RequestBody Role[] roles){
		String result=this.batchDelete(roles);
		return "{'message':\""+result+"\"}";
	}
	
	
	
	@RequestMapping(value="/role/json/query.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String query(@RequestBody  SqlParameter param,HttpServletResponse response){
		
		//String queryString ="from Project  where id = :value order by :sortName asc";
		this.resetQuerySqlParameter("Role", param);
		String pageJson=this.getRoleService().getPageBeanJSONByQuery(param);
		return pageJson;
		
	}
	
	@RequestMapping(value = "/roleManager.do")
	
	public String toTableManger(
				HttpServletRequest request, HttpServletResponse response) {
			return "tableManager";
	}



	public RoleService getRoleService() {
		return roleService;
	}



	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	
	
}
