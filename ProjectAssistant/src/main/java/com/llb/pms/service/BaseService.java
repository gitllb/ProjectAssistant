package com.llb.pms.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.llb.pms.dao.BaseDao;
import com.llb.pms.dao.impl.PageBean;
import com.llb.pms.dao.impl.ProcedureResult;
import com.llb.pms.dao.impl.SqlParameter;

public interface BaseService<T> {
	
	public BaseDao getBaseDao();
	
	public  void insert(T entity);
	/**
	* 修改�?��实体对象（UPDAObjectE�?��记录�?
	* @param entity 实体对象
	* @return void 修改的对象个数，正常情况=1
	*/
	
	public  void update(T entity);
	
	/**
	* 按主键删除记�?
	* @param entity T 实体对象
	* @return void 删除的对象个数，正常情况=1
	*/
	public  void delete(T  entity);
	
	/**
	* 键删除所有记�?
	* 
	*@return void  
	*/
	public  void deleteAll();
	
	/**
	* 按主键取记录
	* @param id 主键�?
	* @return 记录实体对象，如果没有符合主键条件的记录，则返回null
	*/
	public  T get(Serializable id);
	
	/**
	* 按主键取记录
	* @param id 主键�?
	* @return 记录实体对象 JSONString，如果没有符合主键条件的记录，则返回null
	*/
	public  String getJSONS(Serializable id);
	
	
	
	/**
	 * 按主键取记录
	 * @param id  主键�?
	 * @param lockMode 加锁模式
	 * @return
	 * @throws HibernateException
	 */
	public T get( Serializable id,LockMode lockMode);
    
	/**
	* 按主键取记录
	* @param id 主键�?
	* @return 记录实体对象，如果没有符合主键条件的记录�?
	*/
	public T load(Serializable id);
	
	/**
	* 按主键取记录
	* @param id 主键�?
	* @return 记录实体对象 JSONString，如果没有符合主键条件的记录�?
	*/
	public String loadJSON(Serializable id);
	/**
	* 取全部记�?
	* @return List<T><T> 全部记录实体对象的List
	*/
	public  List<T> loadAll();
	
	/**
	* 取全部记�?
	* @return List<T><T> 全部记录实体对象的List JSONString
	*/
	public  String loadAllJSON();
	/**
	* 查询整表总记录数
	* @return long 整表总记录数
	*/
	public  Long count();
	 
	
	/**
	* 批量插入
	* @param Collection 插入实体集合
	* @return void
	*/
	public  void batchInsert( Collection<T> entities);
	/**
	* 批量修改
	* @param Collection 更新实体集合
	* @return void
	*/
	public  void batchUpdate( Collection<T> entities);
	/**
	* 批量删除
	* @param Collection 删除实体集合
	* @return void
	*/
	public  void batchDelete( Collection<T> entities);
	
	/**
	* 批量插入
	* @param Object[] 插入实体集合
	* @return void
	*/
	public  void batchInsert( T[] entities);
	/**
	* 批量修改
	* @param Object[] 更新实体集合
	* @return void
	*/
	public  void batchUpdate( T[] entities);
	/**
	* 批量删除
	* @param Object[] 删除实体集合
	* @return void
	*/
	public  void batchDelete( T[] entities);
	
	/**
	* Hiberante iterator接口,缓存优先
	* @param param 查询条件参数
	* @return Iterator<T> 查询结果
	*/
	public Iterator<T> iterator(SqlParameter param);
	
	/**
	* Hiberante iterator接口,缓存优先
	* @param param 查询条件参数
	* @return Iterator<T> 查询结果 JSONString
	*/
	public String iteratorJSON(SqlParameter param);
	
	/**
	 * Hibernate唯一结果查询
	 * @param param 查询条件参数
	 * @return Object 查询结果
	 */
	public Object uniqueQuery(SqlParameter param);
	

	/**
	 * Hibernate HQL语言查询
	 * @param param	查询条件参数
	 * @return List<T><T> 查询结果
	 */
	public  List<T> query(SqlParameter param);
	
	/**
	 * Hibernate HQL语言查询
	 * @param param	查询条件参数
	 * @return List<T><T> 查询结果 JSONString
	 */
	public  String queryJSON(SqlParameter param);
	
	
	
	/**
	 * Hibernate HQL语言分页查询
	 * @param param	查询条件参数
	 * @param index 分页索引
	 * @param size  分页大小
	 * @return List<T><T> 查询结果
	 */
	public List<T> queryPage(SqlParameter param);
	
	/**
	 * Hibernate HQL语言分页查询
	 * @param param	查询条件参数
	 * @param index 分页索引
	 * @param size  分页大小
	 * @return List<T><T> 查询结果
	 */
	public String queryPageJSON(SqlParameter param);
	
	/**
	 * Hiberante HQL语言更新
	 * @param param 查询条件参数
	 * @return int 影响行数
	 */
	public  Integer update(SqlParameter param);
	

	/**
	 * Hibernate 数据库语�?���?
	 * @param param	查询条件参数
	 * @return List<T><T> 查询结果
	 */
	public  List<T> queryBySQL(SqlParameter param);
	
	/**
	 * Hibernate 数据库语�?���?
	 * @param param	查询条件参数
	 * @return List<T><T> 查询结果 JSONString
	 */
	public  String queryJSONBySQL(SqlParameter param);
	
	
	/**
	 * Hibernate 数据库语�?��页查�?
	 * @param param	查询条件参数
	 * @param index 分页索引
	 * @param size  分页大小
	 * @return List<T><T> 查询结果
	 */
	public List<T> queryPageBySQL(SqlParameter param);
	
	/**
	 * Hibernate 数据库语�?��页查�?
	 * @param param	查询条件参数
	 * @param index 分页索引
	 * @param size  分页大小
	 * @return List<T><T> 查询结果 JSONString
	 */
	public String queryPageJSONBySQL(SqlParameter param);
	
	
	/**
	 * Hiberante 数据库语�?���?
	 * @param param 查询条件参数
	 * @return int 影响行数
	 */
	public  Integer updateBySQL(SqlParameter param);
	
	/**
	 * Hibernate命名查询
	 * @param param	查询条件参数
	 * @return List<T><T> 查询结果
	 */
	public List<T> queryByNamed(SqlParameter param);
	
	/**
	 * Hibernate命名查询
	 * @param param	查询条件参数
	 * @return List<T><T> 查询结果 JSONString
	 */
	public String queryJSONByNamed(SqlParameter param);
	
	/**
	 * Hibernate分页命名查询
	 * @param param	查询条件参数
	 * @param index 分页索引
	 * @param size  分页大小
	 * @return List<T><T> 查询结果
	 */
	public List<T> queryPageByNamed(SqlParameter param);
	
	/**
	 * Hibernate分页命名查询
	 * @param param	查询条件参数
	 * @param index 分页索引
	 * @param size  分页大小
	 * @return List<T> 查询结果 JSONString
	 */
	public String queryPageJSONByNamed(SqlParameter param);

	/**
	 * Hibernate命名更新
	 * @param param 查询条件参数
	 * @return List<T> 查询结果
	 */
	public Integer updateByNamed(SqlParameter param);
	

	/**
	 * 执行存储过程查询，此方法绕过hibernate接口
	 * 没有缓存，用List<默认�?封装表数�?属�?名和查询列名�?��，并且有set方法，否则为null
	 * @param param 查询条件参数
	 * @return ProcedureResult 执行结果
	 */
	
	public ProcedureResult queryByJdbcProcedure(SqlParameter param);
	
	/**
	 * 执行存储过程查询，此方法绕过hibernate接口
	 * 没有缓存，用List<默认�?封装表数�?属�?名和查询列名�?��，并且有set方法，否则为null
	 * @param param 查询条件参数
	 * @return ProcedureResult 执行结果 JSONStrng
	 */
	
	public String queryJSONByJdbcProcedure(SqlParameter param);
	
	/**
	 * 执行存储过程查询，此方法绕过hibernate接口
	 * 没有缓存，用List<Map<key,value>>封装表数�?
	 * @param param 查询条件参数
	 * @return ProcedureResult 执行结果
	 */
	
	
	public ProcedureResult queryByJdbcProcedureWithMap(SqlParameter param);
	
	/**
	 * 执行存储过程查询，此方法绕过hibernate接口
	 * 没有缓存，用List<Map<key,value>>封装表数�?
	 * @param param 查询条件参数
	 * @return ProcedureResult 执行结果 JSONString
	 */
	
	
	public String queryJSONByJdbcProcedureWithMap(SqlParameter param);
	
	/**
	 * 执行存储过程更新，此方法绕过hibernate接口
	 * 没有缓存
	 * @param param 查询条件参数
	 * @return  ProcedureResult 执行结果
	 */
	public ProcedureResult updateByJdbcProcedure(SqlParameter param);
	
	
	
	/**
	 * 用Hibernate Query查询分页
	 * @param param 查询参数
	 * @param index  索引
	 * @param size  分页大小
	 * @return PageBean  分页Bean
	 */
	public  PageBean getPageBeanByQuery(SqlParameter param);
	
	/**
	 * 用Hibernate Query查询分页
	 * @param param 查询参数
	 * @param index  索引
	 * @param size  分页大小
	 * @return PageBean  json 字符串
	 */
	public  String getPageBeanJSONByQuery(SqlParameter param);
	
	
	/**
	 * 用数据库查询语言查询分页
	 * @param param 查询参数
	 * @param index  索引
	 * @param size  分页大小
	 * @return PageBean  分页Bean
	 */
	public  PageBean getPageBeanBySQLQuery(SqlParameter param);
	
	/**
	 * 用数据库查询语言查询分页
	 * @param param 查询参数
	 * @param index  索引
	 * @param size  分页大小
	 * @return PageBean  分页Bean JSONString 
	 */
	public  String getPageBeanJSONBySQLQuery(SqlParameter param);
	
	
	/**
	 * 用Hibernate命名查询分页
	 * @param param 查询参数
	 * @param index  索引
	 * @param size  分页大小
	 * @return PageBean  分页Bean
	 */
	public  PageBean getPageBeanByNamedQuery(SqlParameter param);
	
	/**
	 * 用Hibernate命名查询分页
	 * @param param 查询参数
	 *  @return pageBean 分页Bean JSONString 
	 */
	public  String getPageBeanJSONByNamedQuery(SqlParameter param);
	
	
	
	/**
	 * 用jdbc存储过程查询分页
	 * 查询参数约定�?
	 *  第一个查询参数为查询字符串，第二个为索引，第三个为分页大小，第四个为查询行数,
	 *用List<默认>封装表数�?
	 * @param param  查询参数
	 *  @return pageBean 分页Bean
	 */
	public  PageBean getPageBeanByJdbcProcedure(SqlParameter param);
	
	/**
	 * 用jdbc存储过程查询分页
	 * 查询参数约定�?
	 *  第一个查询参数为查询字符串，第二个为索引，第三个为分页大小，第四个为查询行数,
	 *用List<默认>封装表数�?
	 * @param param  查询参数
	 *   @return pageBean 分页Bean JSONString 
	 */
	public  String getPageBeanJSONByJdbcProcedure(SqlParameter param);
	
	/**
	 * 用jdbc存储过程查询分页
	 * 查询参数约定�?
	 *  第一个查询参数为查询字符串，第二个为索引，第三个为分页大小，第四个为查询行数
	 *用List<Map<key,value>>封装表数�?
	 * @param param  查询参数
	 *  @return pageBean 分页Bean
	 */
	public PageBean getPageBeanByJdbcProcedureWithMap(SqlParameter param) ;
	
	/**
	 * 用jdbc存储过程查询分页
	 * 查询参数约定�?
	 *  第一个查询参数为查询字符串，第二个为索引，第三个为分页大小，第四个为查询行数
	 *用List<Map<key,value>>封装表数�?
	 * @param param  查询参数
	 *  @return pageBean 分页Bean JSONString 
	 */
	public String getPageBeanJSONByJdbcProcedureWithMap(SqlParameter param) ;
	
    /**
     * 把java对象转换为JOSN字符串
     * @param obj java对象
     * @return String
     */
	public String getObjectJSONString(Object obj);
	
	/**
     * 把java数组对象转换为JOSN字符串
     * @param array java数组对象（List,Set,Map,[],）
     * @return String
     */
	public String getArrayJSONString(Object array);
	/**
     * 把java数组对象转换为JOSN字符串
     * @param JsonConfig jcfg 过滤不需要转换成json的属性
     * 
     */
	public void setPropertyFilter (PropertyFilter  jcfg);
	
}
