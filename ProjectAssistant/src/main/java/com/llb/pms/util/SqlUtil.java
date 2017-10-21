package com.llb.pms.util;

import java.util.Collection;

public class SqlUtil {

	public SqlUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static String createSqlStringWithMap(String entityName,Collection<String> propertyNames,String where){
		StringBuffer sb=new StringBuffer();
		sb.append("select new Map(");
		int index=0;
		for(String name:propertyNames){
			sb.append(name +" as "+name);
			index++;
			if(index<propertyNames.size()){
				sb.append(",");
			}
		}
		sb.append(") from "+entityName +" ");
		if(where!=null){
			sb.append(where);
		}
		return sb.toString();
	}
	
	public static String createSqlStringWithList(String entityName,Collection<String> propertyNames,String where){
		StringBuffer sb=new StringBuffer();
		sb.append("select new List(");
		int index=0;
		for(String name:propertyNames){
			sb.append(name);
			index++;
			if(index<propertyNames.size()){
				sb.append(",");
			}
		}
		sb.append(") from "+entityName);
		if(where!=null){
			sb.append(" where "+where);
		}
		return sb.toString();
	}

}
