package com.llb.pms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;

/**
 * 封装查询字符串参数类
 * @author llb
 *
 */
public class SqlParameter {
	
	private String id;
	private Map<String,String> responseUrl;
	private Class entityClass;
	private String queryString;// 查询字符�?
	private List<Parameter> parameters=new ArrayList();//查询字符串参�?
	private LockMode lockMode;//查询加锁模式
	private int page=1;
	private int pagesize=30;
	private String sortname;
	private String sortorder;
	public SqlParameter( ){
	
	}
	/**
	 * 查询参数构�?方法
	 * @param queryString 查询字符�?
	 */
	public SqlParameter(String queryString ){
		this.queryString=queryString;
	}
	
	 /**
	  * 添加查询字符串参�?
	 * @param name 参数�?
	 * @param value 参数�?
	 */
	public void addParameter(String name,Object value){
		 this.parameters.add(new Parameter(name,value));
	 }
	 
	 /**
	  * 添加查询字符串参�?
	 * @param index 参数索引hiberante begin 0,jdbc begin 1
	 * @param value 参数�?
	 */
	 public void addParameter(int index,Object value){
		 this.parameters.add( new Parameter(index,value));
	 }
	 
	 /**
	  * 添加查询字符串参�?
	 * @param parameter 参数�?
	 * 
	 */
	public void addParameter(Parameter parameter){
		this.parameters.add(parameter);
		
	 }
	
	 /**
	  * 添加存储过程参数
	 * @param name 参数�?
	 * @param value 参数�?
	 * @param type  参数类型 java.sql.Types
	 * @param output 存储过程参数 ，output=0 输入参数, output=1输出参数, output=3输入输出参数
	 */
	public void addParameter(String name,Object value,int type,int output){
		 this.parameters.add( new Parameter(name,value,type,output) );
	 }
	 
	 /**
	  * 添加存储过程参数
	 * @param index 参数索引  hiberante begin 0,jdbc begin 1
	 * @param value 参数�?
	 * @param type  参数类型 java.sql.Types
	 * @param output 存储过程参数 ，output=0 输入参数, output=1输出参数, output=3输入输出参数
	 */
	 public void addParameter(int index,Object value,int type,int output){
		 this.parameters.add( new Parameter(index,value,type,output));
	 }
	 
	 
	 
	 /**
	  * 获取SQL语句参数�?
	 * @param name 参数�?
	 * @return   Parameter
	 */
	public Parameter getParameter(String name){
		
		for(Parameter p:this.parameters){
			if(name.equals(p.getName())){
				return p;
			}
		}
		return null;
	 }
	
	/**
	  * 移除SQL语句参数�?
	 * @param name 参数�?
	 * @return   Parameter
	 */
	public Parameter removeParameter(String name){
		for(Parameter p:this.parameters){
			if(name.equals(p.getName())){
				this.parameters.remove(p);
				return p;
			}
		}
		return null;
	 }
	/**
	  * 获取SQL语句参数�?
	 * @param index 参数索引hiberante begin 0,jdbc begin 1
	 * @return   Parameter
	 */
	public Parameter getParameter(int index){
		for(Parameter p:this.parameters){
			if(p.getIndex()==index){
				return p;
			}
		}
		return null;
	 }
	
	/**
	  * 移除SQL语句参数�?
	 * @param index 参数索引hiberante begin 0,jdbc begin 1
	 * @return   Parameter
	 */
	public Parameter removeParameter(int index){
		for(Parameter p:this.parameters){
			if(p.getIndex()==index){
				return this.parameters.remove(index);
			}
		}
		return null;
	 }
	 
	 
	 /**
	  * 获取SQL语句参数�?
	 * @param name 参数�?
	 * @return   Object
	 */
	public Object getParameterValue(String name){
		 Parameter para=this.getParameter(name);
		 return para==null?null:para.getValue();
	 }
	/**
	  * 获取SQL语句参数�?
	 * @param index 参数索引 hiberante begin 0,jdbc begin 1
	 * @return   Object
	 */
	public Object getParameterValue(int index){
		Parameter para=this.getParameter(index);
		 return para==null?null:para.getValue();
	 }
	 
	 /**
	  * 获取存储过程参数类型
	 * @param name 参数�?
	 * @return
	 */
	public int getParameterType(String name){
		Parameter para=this.getParameter(name);
		 return para==null?0:para.getType();
	 }
	/**
	  * 获取存储过程参数类型
	 * @param index 参数索引hiberante begin 0,jdbc begin 1
	 * @return
	 */
	 public int getParameterType(int index){
		 Parameter para=this.getParameter(index);
		 return para==null?0:para.getType();
	 }
	 
	 /**
	  * 获取存储过程是否是输出参�?
	 * @param name 参数�?
	 * @return int  返回1表示是输出参数，否则不是
	 */
	public int getParameterOutput(String name){
		Parameter para=this.getParameter(name);
		 return para==null?0:para.getOutput();
	 }
	/**
	  * 获取存储过程是否是输出参�?
	 * @param index 参数索引hiberante begin 0,jdbc begin 1
	 * @return int  返回1表示是输出参数，否则不是
	 */
	 public int getParameterOutput(int index){
		 
		 Parameter para=this.getParameter(index);
		 return para==null?0:para.getOutput();
	 }
	 
	 
	 
	
	
	 /**
	  * 返回SQL语句参数的个�?
	  * @return
	  */
	 public int getParameterSize(){
		 return this.parameters.size();
	 }
	 
	 public void addUrl(String key,String url){
		 this.responseUrl.put(key, url);
	 }
	 
	 public String getUrl(String key){
		 return this.responseUrl.get(key);
	 }
	 
	 


	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	
	 
	public LockMode getLockMode() {
		return lockMode;
	}

	public void setLockMode(LockMode lockMode) {
		this.lockMode = lockMode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}
	public Map<String, String> getResponseUrl() {
		return responseUrl;
	}
	public void setResponseUrl(Map<String, String> responseUrl) {
		this.responseUrl = responseUrl;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getSortorder() {
		return sortorder;
	}
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	
	
	
}
