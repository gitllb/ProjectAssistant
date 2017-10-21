package com.llb.pms.util;

import java.util.HashMap;
import java.util.Map;

public class ControllerParameter {
	
	private String sqlId;
	private Map parameters=new HashMap();
	private String className;
	private int index;
	private int size;
	
	public ControllerParameter() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getSqlId() {
		return sqlId;
	}



	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}



	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
