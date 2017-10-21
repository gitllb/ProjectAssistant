package com.llb.pms.dao.impl;

import java.math.BigDecimal;
import java.text.DateFormat;

public class Parameter{
	
	private String name;//参数�?
	
	private String webName;//网页传递参数名
	
	private int index=-1;//参数索引
	
	private Object value;//参数�?
	
	private String valueString;//参数值字符串表现形式
	
	private int type=12;//参数数据类型 java.sql.Types
	
	private int output=0;//输出参数 ，output=1,是输出参数，否则不是
	
	public Parameter (){
		
	}
	
	/**
	 * SQL字符串查询参数构造方�?
	 * @param name 参数�?
	 * @param value 参数�?
	 */
	public Parameter (String name,Object value){
		this.name=name;
		this.value=value;
	}
	
	/**
	 * SQL字符串查询参数构造方�?
	 * @param index 参数�?
	 * @param value 参数�?
	 */
	public Parameter (int  index,Object value){
		this.index=index;
		this.value=value;
	}
	
	/**
	 * 存储过程参数构�?方法
	 * @param name 参数�?
	 * @param value 参数�?
	 * @param type  参数数据类型 java.sql.Types
	 * @param output 输出参数  output=1,是输出参数，否则不是
	 */
	
	public Parameter (String name,Object value,int type,int output){
		this.name=name;
		this.value=value;
		this.type=type;
		this.output=output;
	}
	
	/**
	 * 存储过程参数构�?方法
	 * @param index 参数�?
	 * @param value 参数�?
	 * @param type  参数数据类型 java.sql.Types
	 * @param output 输出参数  output=1,是输出参数，否则不是
	 */
	public Parameter (int  index,Object value,int type,int output){
		this.index=index;
		this.value=value;
		this.type=type;
		this.output=output;
	}
	
	private  Object getObject(String value,int sqlType) throws Exception{
		if(value==null){
			return null;
		}
		switch(sqlType){
			case  2003 :// ARRAY 2003
				return value.split(";");
			case  -5 :// BIGINT -5 
				return Long.valueOf(value);
			case  -2 :// BINARY -2 
				return value.getBytes();
			case   7:// BIT -7 
				return Integer.valueOf(value).intValue();
			//case   :// BLOB 2004 
			case   16:// BOOLEAN 16 
				return Boolean.valueOf(value);
			case   1:// CHAR 1 
				return value.charAt(0);
			case   2005:// CLOB 2005 
				return value.getBytes();
			//case   :// DATALINK 70 
			case   91:// DATE 91 
				return DateFormat.getDateInstance().parseObject(value);
			case   3:// DECIMAL 3 
				return  new BigDecimal(value);
			//case   :// DISTINCT 2001 
			case   8:// DOUBLE 8 
				return Double.valueOf(value);
			case  6 :// FLOAT 6 
				return Float.valueOf(value);
			case   4:// INTEGER 4 
				return Integer.valueOf(value);
			case   2000:// JAVA_OBJECT 2000 
				return value;
			case   -4:// LONGVARBINARY -4 
				value.getBytes();
			case   -1:// LONGVARCHAR -1 
				return value;
			case  0 :// NULL 0 
				return null;
			case   2:// NUMERIC 2 
				return Double.valueOf(value);
			//case   :// OTHER 1111 
			//case   :// REAL 7 
			//case   :// REF 2006 
			case   5:// SMALLINT 5 
				return Integer.valueOf(value).shortValue();
			//case   :// STRUCT 2002 
			case   92:// TIME 92 
				return DateFormat.getTimeInstance().parseObject(value);
			case   93:// TIMESTAMP 93 
				return DateFormat.getDateTimeInstance().parseObject(value);
			case   -6:// TINYINT -6 
				return Integer.valueOf(value);
			//case   :// VARBINARY -3 
			case   12:// VARCHAR 12 
			   return value;
		 
		}
		
		return value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
		this.valueString=value.toString();
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getOutput() {
		return output;
	}
	public void setOutput(int output) {
		this.output = output;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
		try {
			this.value=this.getObject(valueString, this.type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.value=valueString;
		}
	}
	
	
}
