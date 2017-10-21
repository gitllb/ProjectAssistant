package com.llb.pms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llb.pms.dao.impl.SqlParameter;
import com.llb.pms.hibernate.Permit;
import com.llb.pms.service.BaseService;
import com.llb.pms.service.PermitService;
@Controller
public class PermitController extends BaseController{

	@Resource
	private PermitService permitService;
	
	@Override
	public BaseService getBaseService() {
		// TODO Auto-generated method stub
		return permitService;
	}
	
	

	@RequestMapping(value="/permit/json/batchUpdate.do")
	@ResponseBody
	public String batchUpdateProjects(@RequestBody  Permit[] permits){
		String result= this.batchUpdate(permits);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="/permit/json/batchAdd.do")
	@ResponseBody
	public String batchInsertProjects(@RequestBody  Permit[] permits){
		
		String result= this.batchInsert(permits);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="/permit/json/batchDelete.do")
	@ResponseBody
	public String batchDeleteProjects(@RequestBody Permit[] permits){
		String result=this.batchDelete(permits);
		return "{'message':\""+result+"\"}";
	}
	
	
	
	@RequestMapping(value="/permit/json/query.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String query(@RequestBody  SqlParameter param,HttpServletResponse response){
		
		//String queryString ="from Project  where id = :value order by :sortName asc";
		this.resetQuerySqlParameter("Permit", param);
		String pageJson=this.getPermitService().getPageBeanJSONByQuery(param);
		return pageJson;
		
	}
	
	@RequestMapping(value = "/permitManager.do")
	
	public String toTableManger(
				HttpServletRequest request, HttpServletResponse response) {
			return "tableManager";
	}



	public PermitService getPermitService() {
		return permitService;
	}



	public void setPermitService(PermitService permitService) {
		this.permitService = permitService;
	}



	
	
}
