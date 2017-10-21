package com.llb.pms.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.LockMode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.llb.pms.dao.BaseDao;
import com.llb.pms.dao.impl.PageBean;
import com.llb.pms.dao.impl.ProcedureResult;
import com.llb.pms.dao.impl.SqlParameter;
import com.llb.pms.service.BaseService;
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private ThreadLocal<PropertyFilter > profilter =new ThreadLocal();
	
	public BaseServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//@Override
	public abstract BaseDao getBaseDao() ;

	//@Override
	public void insert(T entity) {
		// TODO Auto-generated method stub
		this.getBaseDao().insert(entity);
	}

	//@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		this.getBaseDao().update(entity);
	}

	//@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		this.getBaseDao().delete(entity);
	}

	//@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.getBaseDao().deleteAll();
	}

	//@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return (T)this.getBaseDao().get(id);
	}

	//@Override
	public T get(Serializable id, LockMode lockMode) {
		// TODO Auto-generated method stub
		return (T)this.getBaseDao().get(id, lockMode);
	}

	//@Override
	public T load(Serializable id) {
		// TODO Auto-generated method stub
		return (T)this.getBaseDao().load(id);
	}

	//@Override
	public List<T> loadAll() {
		// TODO Auto-generated method stub
		return this.getBaseDao().loadAll();
	}

	//@Override
	public Long count() {
		// TODO Auto-generated method stub
		return this.getBaseDao().count();
	}

	//@Override
	public void batchInsert(Collection<T> entities) {
		// TODO Auto-generated method stub
		this.getBaseDao().batchInsert(entities);
	}

	//@Override
	public void batchUpdate(Collection<T> entities) {
		// TODO Auto-generated method stub
		this.getBaseDao().batchUpdate(entities);
	}

	//@Override
	public void batchDelete(Collection<T> entities) {
		// TODO Auto-generated method stub
		this.getBaseDao().batchDelete(entities);
	}
	
	//@Override
	public void batchInsert(T[] entities) {
		// TODO Auto-generated method stub
		this.getBaseDao().batchInsert(entities);
	}
	
	//@Override
	public void batchUpdate(T[] entities) {
		// TODO Auto-generated method stub
		this.getBaseDao().batchUpdate(entities);
	}

	//@Override
	public void batchDelete(T[] entities) {
		// TODO Auto-generated method stub
		this.getBaseDao().batchDelete(entities);
	}


	//@Override
	public Iterator<T> iterator(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().iterator(param);
	}


	//@Override
	public Object uniqueQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().uniqueQuery(param);
	}

	//@Override
	public List<T> query(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().query(param);
	}

	//@Override
	public List<T> queryPage(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().queryPage(param);
	}
	
	//@Override
	public String queryPageJSON(SqlParameter param) {
		// TODO Auto-generated method stub
		List<T> rows= this.getBaseDao().queryPage(param);
		return JSON.toJSONString(rows,this.getJsonPropertyFilter());
	}

	//@Override
	public Integer update(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().update(param);
	}

	//@Override
	public List<T> queryBySQL(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().queryBySQL(param);
	}



	//@Override
	public List<T> queryPageBySQL(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().queryPageBySQL(param);
	}

	

	//@Override
	public Integer updateBySQL(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().updateBySQL(param);
	}

	//@Override
	public List<T> queryByNamed(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().queryByNamed(param);
	}

	//@Override
	public List<T> queryPageByNamed(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().queryPageByNamed(param);
	}

	//@Override
	public Integer updateByNamed(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().updateByNamed(param);
	}

	//@Override
	public ProcedureResult queryByJdbcProcedure(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().queryByJdbcProcedure(param);
	}

	//@Override
	public ProcedureResult queryByJdbcProcedureWithMap(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().queryByJdbcProcedureWithMap(param);
	}

	
	//@Override
	public ProcedureResult updateByJdbcProcedure(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().updateByJdbcProcedure(param);
	}

	//@Override
	public PageBean getPageBeanByQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().getPageBeanByQuery(param);
	}
	
	//@Override
	public String getPageBeanJSONByQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		PageBean page= this.getBaseDao().getPageBeanByQuery(param);
		return this.getObjectJSONString(page);
	}

	//@Override
	public PageBean getPageBeanBySQLQuery(SqlParameter param
			) {
		// TODO Auto-generated method stub
		return this.getBaseDao().getPageBeanBySQLQuery(param);
	}

	//@Override
	public PageBean getPageBeanByNamedQuery(SqlParameter param
			) {
		// TODO Auto-generated method stub
		return this.getBaseDao().getPageBeanByNamedQuery(param);
	}

	//@Override
	public PageBean getPageBeanByJdbcProcedure(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().getPageBeanByJdbcProcedure(param);
	}

	

	//@Override
	public PageBean getPageBeanByJdbcProcedureWithMap(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getBaseDao().getPageBeanByJdbcProcedureWithMap(param);
	}
	
	//@Override
	public String getObjectJSONString(Object obj){
		if(obj==null){
			return null;
		}
		PropertyFilter filter=this.getJsonPropertyFilter();
		if(filter==null){
			filter=this.getJsonPropertyFilter();
		}
		return JSON.toJSONString(obj,filter);
	}
	
//	//@Override
//	public String getArrayJSONString(Object array){
//		if(array==null){
//			return null;
//		}
//		JsonConfig jcfg=this.getJsonConfig();
//		if(jcfg==null){
//			jcfg=this.getDefaultJsonConfig();
//		}
//		return JSONArray.fromObject(array,jcfg).toString();
//	}

	//@Override
	public String getJSONS(Serializable id) {
		// TODO Auto-generated method stub
		return this.getObjectJSONString(this.get(id));
	}

	//@Override
	public String loadJSON(Serializable id) {
		// TODO Auto-generated method stub
		return this.getObjectJSONString(this.load(id));
	}

	//@Override
	public String loadAllJSON() {
		// TODO Auto-generated method stub
		return this.getArrayJSONString(this.loadAll());
	}

	//@Override
	public String iteratorJSON(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getArrayJSONString(this.iterator(param));
	}

	//@Override
	public String queryJSON(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getArrayJSONString(this.query(param));
	}

	//@Override
	public String queryJSONBySQL(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getArrayJSONString(this.queryBySQL(param));
	}

	//@Override
	public String queryPageJSONBySQL(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getArrayJSONString(this.queryPageBySQL(param));
	}

	//@Override
	public String queryJSONByNamed(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getArrayJSONString(this.queryByNamed(param));
	}

	//@Override
	public String queryPageJSONByNamed(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getArrayJSONString(this.queryPageByNamed(param));
	}

	//@Override
	public String queryJSONByJdbcProcedure(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getObjectJSONString(this.queryByJdbcProcedure(param));
	}

	//@Override
	public String queryJSONByJdbcProcedureWithMap(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getObjectJSONString(this.queryByJdbcProcedureWithMap(param));
	}

	//@Override
	public String getPageBeanJSONBySQLQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getObjectJSONString(this.getPageBeanBySQLQuery(param));
	}

	//@Override
	public String getPageBeanJSONByNamedQuery(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getObjectJSONString(this.getPageBeanByNamedQuery(param));
	}

	//@Override
	public String getPageBeanJSONByJdbcProcedure(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getObjectJSONString(this.getPageBeanByJdbcProcedure(param));
	}

	//@Override
	public String getPageBeanJSONByJdbcProcedureWithMap(SqlParameter param) {
		// TODO Auto-generated method stub
		return this.getObjectJSONString(this.getPageBeanByJdbcProcedureWithMap(param));
	}
	
	//@Override
	public void setPropertyFilter(PropertyFilter  profilter){
		this.profilter .set(profilter);
	}
	
	public PropertyFilter getJsonPropertyFilter(){
		return this.profilter.get();
	}
	
	protected PropertyFilter getDefaultJsonPropertyFilter(){
		return null;
	}
}
