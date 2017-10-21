package com.llb.pms.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.dao.DataAccessException;

import com.llb.pms.dao.BaseDao;

public  class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass;
	
	
	/**
	 * 获取当前Hibernate 会话
	 * @return Session  org.hibernate.Session
	 */
	public Session getSession(){
		
		Session session =null;
		try{
			session=this.sessionFactory.getCurrentSession();
		}catch(Exception e){
			session=this.sessionFactory.openSession();
			System.out.println("currentSession closed! create new one!");
		}
		return session;
	}
	
	/**
	 * 创建Hibernate SessionFactory
	 * @param url hibernate.config.xml 配置文件路径
	 * @return SessionFactory
	 */
	
	public SessionFactory buildSessionFactory(String url){
	      
	   	Configuration cfg=new Configuration().configure(url);
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();  
	   	SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);  
	   	//SessionFactory sf =cfg.buildSessionFactory();//过时
	    return sf;
	    	
	}

	public void insert(T entity) {
		// TODO Auto-generated method stub
		this.getSession().save(entity);
	}

	////@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		 this.getSession().update(entity);
	}

	////@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		 this.getSession().delete(entity);
	}
	
	////@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		String sql="delete from "+this.getEntityClass().getSimpleName();
		SqlParameter sp=new SqlParameter(sql);
		 this.update(sp);
	}

	////@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return (T)this.getSession().get(this.getEntityClass(), id);
	}

	////@Override
	public T get(Serializable id, LockMode lockMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		
		return (T)this.getSession().get(this.getEntityClass(), id, lockMode);
	}

	////@Override
	public T load(Serializable id) throws DataAccessException {
		// TODO Auto-generated method stub
		return (T)this.getSession().load(this.getEntityClass(), id);
	}
	
	//@Override
	public long count() {
		// TODO Auto-generated method stub
		String sql="select count(*) from "+this.getEntityClass().getSimpleName();
		SqlParameter param=new SqlParameter(sql);
		return (Long)this.uniqueQuery(param);
	}


	//@Override
	public List<T> loadAll() {
		// TODO Auto-generated method stub
	    String sql="from "+this.getEntityClass().getSimpleName();
		return this.query(new SqlParameter(sql));
	}

	//@Override
	public int update(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.createQuery(param).executeUpdate();
	}
	
	//@Override
	public void batchInsert(Collection<T> entities) {
		// TODO Auto-generated method stub
		Session session=this.getSession();
		Iterator iterator=entities.iterator();
		int count=1;
		while(iterator.hasNext()){
			session.save(iterator.next());
			if(count%30==0){
				session.flush();
				session.clear();
			}
		}
	}

	//@Override
	public void batchUpdate(Collection<T> entities) {
		// TODO Auto-generated method stub
		Session session=this.getSession();
		Iterator iterator=entities.iterator();
		int count=1;
		while(iterator.hasNext()){
			session.update(iterator.next());
			if(count%30==0){
				session.flush();
				session.clear();
			}
		}
	}

	//@Override
	public void batchDelete(Collection<T> entities) {
		// TODO Auto-generated method stub
		Session session=this.getSession();
		Iterator iterator=entities.iterator();
		int count=1;
		while(iterator.hasNext()){
			session.delete(iterator.next());
			if(count%30==0){
				session.flush();
				session.clear();
			}
		}
	}
	
	//@Override
	public void batchInsert(T[] entities) {
		// TODO Auto-generated method stub
		Session session=this.getSession();
		for(int i=0;i<entities.length;i++){
			session.save(entities[i]);
			if(i%30==29){
				session.flush();
				session.clear();
			}
		}
	}

	//@Override
	public void batchUpdate(T[] entities) {
		// TODO Auto-generated method stub
		Session session=this.getSession();
		for(int i=0;i<entities.length;i++){
			session.update(entities[i]);
			if(i%30==29){
				session.flush();
				session.clear();
			}
		}
	}

	//@Override
	public void batchDelete(T[] entities) {
		// TODO Auto-generated method stub
		Session session=this.getSession();
		for(int i=0;i<entities.length;i++){
			session.delete(entities[i]);
			if(i%30==29){
				session.flush();
				session.clear();
			}
		}
		
	}

	
	//@Override
	public Iterator<T> iterator(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.createQuery(param).iterate();
	}

	//@Override
	public Object uniqueQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.createQuery(param).uniqueResult();
	}

	
	//@Override
	public List<T> query(SqlParameter param) {
		// TODO Auto-generated method stub
		
		return this.createQuery(param).list();
	}
	
	//@Override
	public List<T> queryPage(SqlParameter param){
		Query query=this.createQuery(param); 
		int index=param.getPage();
		int size=param.getPagesize();
		int first=(index-1)*size;
		query.setFirstResult(first);
		query.setMaxResults(size);
		return query.list();
	}
	
	
	//@Override
	public List<T> queryByNamed(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.createNameQuery(param).list();
	}
	
	
	//@Override
	public List<T> queryPageByNamed(SqlParameter param){
		Query query=this.createNameQuery(param);
		int index=param.getPage();
		int size=param.getPagesize();
		int first=(index-1)*size;
		query.setFirstResult(first);
		query.setMaxResults(size);
		return query.list();
	}
	
	//@Override
	public int updateByNamed(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.createNameQuery(param).executeUpdate();
	}
	
	protected Query createNameQuery(SqlParameter param){
		Query query=this.getSession().getNamedQuery(param.getQueryString());
		this.setQueryParameter(query, param);
		return query;
	}
	
	protected Query createQuery(SqlParameter param){
		Query query=this.getSession().createQuery(param.getQueryString());
		this.setQueryParameter(query, param);
		return query;
	}
	
	protected void setQueryParameter(Query query,SqlParameter param){
		List<Parameter> params=param.getParameters();
		Iterator<Parameter> iterator=params.iterator();
		while(iterator.hasNext()){
			Parameter p=iterator.next();
			if(p.getName()==null){
				query.setParameter(p.getIndex(), p.getValue());
			}else{
				query.setParameter(p.getName(), p.getValue());
			}
		}
		
		if(param.getLockMode()!=null){
			query.setLockMode(this.getEntityClass().getSimpleName(), param.getLockMode());
		}
	}
	
	
	//@Override
	public ProcedureResult queryByJdbcProcedure(SqlParameter param) {
		// TODO Auto-generated method stub
		Class entityClass_=param.getEntityClass();
		if(entityClass_==null){
			entityClass_=this.getEntityClass();
		}
		return this.executeJdbcProcedure(param, 1,entityClass_);
	}

	//@Override
	public ProcedureResult queryByJdbcProcedureWithMap( SqlParameter param) {
		// TODO Auto-generated method stub
		return this.executeJdbcProcedure(param, 1,null);
	}

	//@Override
	public ProcedureResult updateByJdbcProcedure( SqlParameter param) {
		// TODO Auto-generated method stub
		return this.executeJdbcProcedure(param,0,null);
	}
	
	/**
	 * 执行jdbc存储过程
	 * @param param 存储过程参数
	 * @param type 标识，type=0 执行更新，否则执行查�?
	 * @return
	 */
	protected ProcedureResult executeJdbcProcedure(final SqlParameter param,final int type,final Class entityClass) {
		// TODO Auto-generated method stub
		return this.getSession().doReturningWork(new ReturningWork<ProcedureResult>(){

			//@Override
			public ProcedureResult execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				CallableStatement call=connection.prepareCall(param.getQueryString());
				setProcedureParameter( call,param);
				ProcedureResult pr=new ProcedureResult();
				try{
					if(type==0){
						call.executeUpdate();
					}else{
						ResultSet result=call.executeQuery();
						if(entityClass==null){
							pr.setResult(getMapResultList(result));
						}else{
							pr.setResult(getEntityResultList(result,entityClass));
						}
						
					}
				}catch (Exception e){
					String message="执行Jdbc存储过程:"+param.getQueryString()+" 出错！原因："+e.getMessage();
					throw new ProcedureException(message);
				}
				
				pr.setOutput(getProcedureOutput(call,param));
				return pr;
			}
			
		});
	}
	protected Map getProcedureOutput(CallableStatement call, SqlParameter param) throws SQLException {
		// TODO Auto-generated method stub
		List params=param.getParameters();
		Iterator iterator=params.iterator();
		Map output=new HashMap();
		while(iterator.hasNext()){
			Parameter p=(Parameter)iterator.next();
			if(p.getOutput()==1||p.getOutput()==3){
				if(p.getName()==null){
					output.put(p.getIndex(), call.getObject(p.getIndex()));
				}else{
					output.put(p.getName(), call.getObject(p.getName()));
				}
			}
			
		}
		return output;
	}
	
	protected List getEntityResultList( ResultSet result,Class cls) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
			List dataList =new ArrayList();
			int cols=result.getMetaData().getColumnCount();
			while(result.next()){
				Object entity=cls.newInstance();
				for(int i=1;i<=cols;i++){
					String columnName=result.getMetaData().getColumnName(i);
					Method method=this.getSetMethod(cls,columnName);
					if(method!=null){
						this.invokeSetMethod(entity,method,result,i);
					}
				}
				dataList.add(entity);
			}
			
		  return dataList;
	}
	
	protected Method getSetMethod(Class cls, String columnName) {
		// TODO Auto-generated method stub
		for(Method method:cls.getMethods()){
			if(("SET"+columnName.toUpperCase()).equals(method.getName().toUpperCase())){
				return method;
			}
		}
		
		return null;
	}
	
	protected void invokeSetMethod(Object entity, Method method,
			ResultSet result, int i) throws SQLException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		Object value=this.getValue(method.getParameterTypes()[0],result,i);
		method.invoke(entity, new Object[]{value});
		
	}

	protected Object getValue(Class cls, ResultSet result, int i) throws SQLException {
		// TODO Auto-generated method stub
		if(cls.equals(byte.class)||cls.equals(Byte.class)){
			return result.getByte(i);
		}else if(cls.equals(int.class)||cls.equals(Integer.class)){
			return result.getInt(i);
		}else if(cls.equals(float.class)||cls.equals(Float.class)){
			return result.getFloat(i);
		}else if(cls.equals(double.class)||cls.equals(Double.class)){
			return result.getDouble(i);
		}else if(cls.equals(char.class)||cls.equals(Character.class)){
			return result.getString(i).charAt(0);
		}else if(cls.equals(short.class)||cls.equals(Short.class)){
			return result.getShort(i);
		}else if(cls.equals(long.class)||cls.equals(Long.class)){
			return result.getLong(i);
		}else if(cls.equals(String.class)){
			return result.getString(i);
		}else if(cls.equals(boolean.class)||cls.equals(Boolean.class)){
			return result.getBoolean(i);
		}else if(cls.equals(java.util.Date.class)){
			return new java.util.Date(result.getDate(i).getTime());
		}else if(cls.equals(java.math.BigDecimal.class)){
			return result.getBigDecimal(i);
		}else{
			return result.getObject(i);
		}
	
	}


	protected List<Map> getMapResultList( ResultSet result) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
			List dataList =new ArrayList();
			int cols=result.getMetaData().getColumnCount();
			while(result.next()){
				Map entity=new HashMap();
				for(int i=1;i<=cols;i++){
					String columnName=result.getMetaData().getColumnName(i);
					Object value=result.getObject(i);
					entity.put(columnName, value);
				}
				dataList.add(entity);
			}
			
		  return dataList;
	}


	protected void setProcedureParameter(CallableStatement call,
			SqlParameter param) throws SQLException {
		// TODO Auto-generated method stub
		List params=param.getParameters();
		Iterator iterator=params.iterator();
		while(iterator.hasNext()){
			Parameter p=(Parameter)iterator.next();
			if(p.getOutput()==1||p.getOutput()==3){
				if(p.getName()==null){
					call.registerOutParameter(p.getIndex(), p.getType());
				}else{
					call.registerOutParameter(p.getName(), p.getType());
				}
			}
			
             if(p.getOutput()==0||p.getOutput()==3){
				if(p.getName()==null){
					call.setObject(p.getIndex(), p.getValue());
				}else{
					call.setObject(p.getName(), p.getValue());
				}
			}
			
		}
		
	}


	
	//@Override
	public List<T> queryBySQL(SqlParameter param) {
		// TODO Auto-generated method stub
		SQLQuery query=this.createSQLQuery(param);
		Class entityClass_=param.getEntityClass();
		if(entityClass_==null){
			entityClass_=this.getEntityClass();
		}
		query.addEntity(entityClass_);
		return query.list();
	}
	

	//@Override
	public List<T> queryPageBySQL(SqlParameter param) {
		// TODO Auto-generated method stub
		SQLQuery query=this.createSQLQuery(param).addEntity(getEntityClass());
		Class entityClass_=param.getEntityClass();
		if(entityClass_!=null){
			query.addEntity(entityClass_);
		}
		int index=param.getPage();
		int size=param.getPagesize();
		int first=(index-1)*size;
		query.setFirstResult(first);
		query.setMaxResults(size);
		
		return query.list();
	}
	
	//@Override
	public int updateBySQL(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.createSQLQuery(param).executeUpdate();
	}
	
	protected SQLQuery createSQLQuery(SqlParameter param){
		Session session=this.getSession();
		SQLQuery query=session.createSQLQuery(param.getQueryString());
		this.setQueryParameter(query, param);
		return query;
	}

	
	//@Override
	public PageBean getPageBeanByQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		int index=param.getPage();
		int size=param.getPagesize();
		List<T> result=this.queryPage(param);
		PageBean pageBean=new PageBean(result,index,size);
		return pageBean;
	}

	//@Override
	public PageBean getPageBeanBySQLQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		int index=param.getPage();
		int size=param.getPagesize();
		List<T> result=this.queryPageBySQL(param);
		PageBean pageBean=new PageBean(result,index,size);
		return pageBean;
	}
	
	
	//@Override
	public PageBean getPageBeanByNamedQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		int index=param.getPage();
		int size=param.getPagesize();
		List<T> result=this.queryPageByNamed(param);
		PageBean pageBean=new PageBean(result,index,size);
		return pageBean;
	}

	

	//@Override
	public PageBean getPageBeanByJdbcProcedure(SqlParameter param) {
		// TODO Auto-generated method stub
		ProcedureResult pr=this.queryByJdbcProcedure(param);
		return createPageBean(param,pr);
	}
	
	
	//@Override
	public PageBean getPageBeanByJdbcProcedureWithMap(SqlParameter param) {
		// TODO Auto-generated method stub
		ProcedureResult pr=this.queryByJdbcProcedureWithMap(param);
		return createPageBean(param,pr);
	}
	
	protected PageBean createPageBean(SqlParameter param,ProcedureResult pr){
		List result=pr.getResult();
		int index=(Integer)param.getParameterValue(2);
		int size=(Integer)param.getParameterValue(3);
		int total=(Integer)pr.getOutput().get(4);
		PageBean pageBean=new PageBean(result,index,size,total);
		return pageBean;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 获取实体Class
	 * @return
	 */
	public  Class<T> getEntityClass(){
		
		if(entityClass!=null){
			return entityClass;
		}
		
		Type supType=this.getClass().getGenericSuperclass();
		if(supType instanceof ParameterizedType){
			ParameterizedType pType=(ParameterizedType)supType;
			return (Class<T>)pType.getActualTypeArguments()[0];
		}
		return null;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	
}
