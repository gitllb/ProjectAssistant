package com.llb.pms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.llb.pms.dao.BaseDao;
import com.llb.pms.dao.RoleDao;
import com.llb.pms.hibernate.Role;
import com.llb.pms.service.RoleService;
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService<Role>{
	@Resource
	private RoleDao roleDao;

	public RoleServiceImpl() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	@Override
	protected PropertyFilter getDefaultJsonPropertyFilter(){
		PropertyFilter filter = new PropertyFilter(){  
			   
            public boolean apply(Object object, String name, Object value) {  
                if(name.equalsIgnoreCase("users")||name.equals("roles")){  
                    //false表示filter字段将被排除在外  
                    return false;  
                }  
                return true;  
            }  
              
        };  
		
		return filter;
	}
	
	

	@Override
	public BaseDao<Role> getBaseDao() {
		// TODO Auto-generated method stub

		return (BaseDao<Role>)this.getRoleDao();
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


	public String getArrayJSONString(Object array) {
		// TODO Auto-generated method stub
		return null;
	}


}
