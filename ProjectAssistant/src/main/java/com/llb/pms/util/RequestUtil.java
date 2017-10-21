package com.llb.pms.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.llb.pms.dao.impl.Parameter;
import com.llb.pms.dao.impl.SqlParameter;

public class RequestUtil {
	
	public static WebApplicationContext getWebApplicationContext(HttpServletRequest request){
		return RequestContextUtils.getWebApplicationContext(request);
	}
	

	public static SqlParameter getSqlParameter(HttpServletRequest request){
		WebApplicationContext context=RequestContextUtils.getWebApplicationContext(request);
		String sqlId=request.getParameter("sqlId_");
		SqlParameter param=(SqlParameter)context.getBean(sqlId);
		
		String index=request.getParameter("index_");
		String size=request.getParameter("size_");
		if(index!=null){
			param.setPage(Integer.valueOf(index));
		}
		
		if(size!=null){
			param.setPagesize(Integer.valueOf(size));
		}
		List<Parameter> ps=param.getParameters();
		for(Parameter p:ps){
			String webName=p.getWebName();
			if(webName==null){
				webName=p.getName();
				if(webName==null){
					webName=p.getIndex()+"";
				}
			}
			String value=request.getParameter(webName);
			int sqlType=p.getType();
			try {
				p.setValue(getObjectValue(value, sqlType));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return param;
	}
	
	protected static  Object getObjectValue(String value,int sqlType) throws Exception{
		
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


}
