package com.llb.pms.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.llb.pms.dao.impl.Parameter;
import com.llb.pms.dao.impl.SqlParameter;

public class SqlManager {
    private static Map sqls=loadSqls();
    
    private static Map loadSqls(){
    	Map sqls=new HashMap();
    	InputStream in=SqlManager.class.getResourceAsStream("sql.xml");
    	SAXReader saxReader = new SAXReader(); 
    	
			Document doc;
			try {
				doc = saxReader.read(in);
				Element root=doc.getRootElement();
				Iterator<Element> sqlIterator=root.elementIterator("sql");
				//<sql id="login" class="" >
				//<parameter index="" name="name" value="" sqlType="12"  output="" ></parameter>
				while(sqlIterator.hasNext()){
					Element sql=sqlIterator.next();
					String sqlString=sql.elementTextTrim("sqlString");
					String id=sql.attributeValue("id");
					String className=sql.attributeValue("class");
					SqlParameter sp=new SqlParameter(sqlString);
					sp.setId(id);
					if(className!=null){
						try {
							Class entityClass=null;
							entityClass=Class.forName(className);
							sp.setEntityClass(entityClass);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					List<Element> params=sql.elements("parameter");
					for(int i=0;i<params.size();i++){
						Parameter parameter=new Parameter();
						Element param=params.get(i);
						String index=param.attributeValue("index");
						String name=param.attributeValue("name");
						String sqlType=param.attributeValue("sqlType");
						String output=param.attributeValue("output");
						String webName=param.attributeValue("webName");
						parameter.setWebName(webName);
						if(name!=null){//&&name.trim().length()>0
							parameter.setName(name);
						}else{
							parameter.setIndex(Integer.valueOf(index));
						}
						
						if(sqlType!=null){
							parameter.setType(Integer.valueOf(sqlType));//默认字符串
						}
						
						if(output!=null){
							parameter.setOutput(Integer.valueOf(output));
						}
						
						sp.addParameter(parameter);
						
					}
					sqls.put(id, sp);
				}
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		
    	return sqls;
    }
    
    
    public static SqlParameter getSqlParameter(HttpServletRequest request) throws Exception{
    	SqlParameter param=(SqlParameter)sqls.get(request.getParameter("sqlId_"));
    	
    	Collection<Parameter> ps=param.getParameters();
    	for(Parameter p:ps){
    		String webName=p.getWebName();
    		int sqlType=p.getType();
    		String valueString=request.getParameter(webName);
    		Object value=getObjectValue(valueString, sqlType);
    		p.setValue(value);
    	}
    	return param;
    }
    
    public static Object getObjectValue(String value,int sqlType) throws Exception{
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
    
}
