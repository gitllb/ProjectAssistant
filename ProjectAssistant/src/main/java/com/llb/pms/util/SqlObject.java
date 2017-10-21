package com.llb.pms.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.List;

import com.llb.pms.dao.impl.Parameter;

public class SqlObject {
	
	private String id;
	private List<Parameter> params;
	//private String format;
    private Class class_;
    private Object value;
    
	public SqlObject() {
		// TODO Auto-generated constructor stub
	}
	
	public Object getObjectValue(String value,int sqlType) throws Exception{
		
		switch(sqlType){
		 case  12: 
			   return value;
		 case  8 :
			  return Double.valueOf(value);
		 case  6 :
			  return Float.valueOf(value);
		 case 4 :
			  return Integer.valueOf(value);
		 case 5 :
			  return Integer.valueOf(value).shortValue();
		 case -5 :
			  return Integer.valueOf(value).longValue();
		 case -7 :
			  return Integer.valueOf(value).intValue();
		 case  3 :
			  return  new BigDecimal(value);
		 case  2 :
			  return Double.valueOf(value);
		 case 16 :
			  return Boolean.valueOf(value);
		 case  1 :
			  return value.charAt(0);
		 case 91 :
			  return DateFormat.getDateInstance().parseObject(value);
		 case 92 :
			  return DateFormat.getTimeInstance().parseObject(value);
		 case  93: 
			  return DateFormat.getDateTimeInstance().parseObject(value);
		 case  0 :
			  return null;

		}
		
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public Class getClass_() {
		return class_;
	}

	public void setClass_(Class class_) {
		this.class_ = class_;
	}


}
