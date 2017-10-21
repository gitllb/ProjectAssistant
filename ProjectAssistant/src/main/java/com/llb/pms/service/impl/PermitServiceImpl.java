package com.llb.pms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.llb.pms.dao.BaseDao;
import com.llb.pms.dao.PermitDao;
import com.llb.pms.hibernate.Permit;
import com.llb.pms.service.PermitService;
@Service("permitService")
public class PermitServiceImpl extends BaseServiceImpl<Permit> implements PermitService<Permit>{
	@Resource
	private PermitDao permitDao;

	public PermitServiceImpl() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	protected PropertyFilter getDefaultJsonPropertyFilter(){
		PropertyFilter filter = new PropertyFilter(){  
			   
            public boolean apply(Object object, String name, Object value) {  
                if(name.equals("roles")){  
                    //false表示filter字段将被排除在外  
                    return false;  
                }  
                return true;  
            }  
              
        };  
		
		return filter;
	}
	
	
	@Override
	public BaseDao<Permit> getBaseDao() {
		// TODO Auto-generated method stub

		return (BaseDao<Permit>)this.getPermitDao();
	}

	public PermitDao getPermitDao() {
		return permitDao;
	}

	public void setPermitDao(PermitDao permitDao) {
		this.permitDao = permitDao;
	}

	public String getArrayJSONString(Object array) {
		// TODO Auto-generated method stub
		return null;
	}

}
