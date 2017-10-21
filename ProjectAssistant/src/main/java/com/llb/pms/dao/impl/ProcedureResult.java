package com.llb.pms.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcedureResult {
	
	private List result;//查询结果�?
	private Map output;//查询返回参数
	
	public ProcedureResult(){
		this.output=new HashMap();
		
	}

	/**
	 * 获取返回参数
	 * @param name 参数�?
	 * @return
	 */
	public Object getOutput(String name){
		return this.output.get(name);
	}
	
	/**
	 * 获取返回参数
	 * @param index 参数索引
	 * @return
	 */
	public Object getOutput(int index){
		return this.output.get(index);
	}

	/**
	 * 获取查询结果�?
	 * @return
	 */
	public List getResult() {
		return result;
	}

	/**
	 * 设置查询结果�?
	 * @param result 查询结果
	 */
	public void setResult(List result) {
		this.result = result;
	}

	public Map getOutput() {
		return output;
	}

	public void setOutput(Map output) {
		this.output = output;
	}
	
	
}
