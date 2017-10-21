package com.llb.pms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.llb.pms.dao.BaseDao;
import com.llb.pms.dao.UserDao;
import com.llb.pms.hibernate.User;
import com.llb.pms.service.UserService;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService<User>{
	@Resource
	private UserDao userDao;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	protected PropertyFilter getDefaultJsonPropertyFilter(){
		PropertyFilter filter = new PropertyFilter(){  
			   
            public boolean apply(Object object, String name, Object value) {  
                if(name.equalsIgnoreCase("users")){  
                    //false表示filter字段将被排除在外  
                    return false;  
                }  
                return true;  
            }  
              
        };  
		
		return filter;
	}

	@Override
	public BaseDao<User> getBaseDao() {
		// TODO Auto-generated method stub

		return (BaseDao<User>)this.getUserDao();
	}

	public UserDao getUserDao() {
		return this.userDao;
	}

	public void setUserDao(UserDao customerDao) {
		this.userDao = userDao;
	}

	public String getArrayJSONString(Object array) {
		// TODO Auto-generated method stub
		return null;
	}

}
