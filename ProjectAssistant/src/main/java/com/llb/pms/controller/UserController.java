package com.llb.pms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llb.pms.dao.impl.SqlParameter;
import com.llb.pms.hibernate.Project;
import com.llb.pms.hibernate.User;
import com.llb.pms.service.BaseService;
import com.llb.pms.service.UserService;
import com.llb.pms.util.RequestUtil;
@Controller
public class UserController extends BaseController{

	@Resource
	private UserService userService;
	
	@Override
	public BaseService getBaseService() {
		// TODO Auto-generated method stub
		return userService;
	}
	
	
	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		//String sql="from User u where u.name=:name and u.password=:password";
		SqlParameter param=this.getSqlParameter(request);
		
		List<User> users=userService.query(param);
		//Object result=userService.uniqueQuery(param);
		if(users.size()==0){
			request.setAttribute("message", "用户名或密码错误！");
			//return param.getUrl("fail");
		}
		request.getSession().setAttribute("user",users.get(0) );
		return  "manager";//param.getUrl("success");
	}
	
	@RequestMapping(value="/user/json/batchUpdate.do")
	@ResponseBody
	public String batchUpdateProjects(@RequestBody  User[] users){
		String result= this.batchUpdate(users);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="/user/json/batchAdd.do")
	@ResponseBody
	public String batchInsertProjects(@RequestBody  User[] users){
		
		String result= this.batchInsert(users);
		return "{'message':\""+result+"\"}";
	}
	
	@RequestMapping(value="/user/json/batchDelete.do")
	@ResponseBody
	public String batchDeleteProjects(@RequestBody User[] users){
		String result=this.batchDelete(users);
		return "{'message':\""+result+"\"}";
	}
	
	
	
	@RequestMapping(value="/user/json/query.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String query(@RequestBody  SqlParameter param,HttpServletResponse response){
		
		//String queryString ="from Project  where id = :value order by :sortName asc";
		this.resetQuerySqlParameter("User", param);
		String pageJson=this.getUserService().getPageBeanJSONByQuery(param);
		return pageJson;
		
	}
	
	@RequestMapping(value="/user/json/defaultQuery.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String defaultQuery(HttpServletResponse response){
		
		String queryString ="from User order by id asc";
		SqlParameter param=new SqlParameter(queryString);
		//param.setPage(page);
		//param.setPagesize(pagesize);
		String pageJson=this.getUserService().queryJSON(param);
		return pageJson;
		
	}
	
	@RequestMapping(value = "/userManager.do")
	
	public String toTableManger(
				HttpServletRequest request, HttpServletResponse response) {
			return "tableManager";
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
