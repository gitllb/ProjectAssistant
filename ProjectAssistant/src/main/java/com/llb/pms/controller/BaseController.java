package com.llb.pms.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.llb.pms.dao.impl.PageBean;
import com.llb.pms.dao.impl.Parameter;
import com.llb.pms.dao.impl.ProcedureResult;
import com.llb.pms.dao.impl.SqlParameter;
import com.llb.pms.service.BaseService;
import com.llb.pms.util.RequestUtil;

public abstract class BaseController {
	
	public SqlParameter getSqlParameter(HttpServletRequest request) {
		return RequestUtil.getSqlParameter(request);
	}

	// 
	public abstract BaseService getBaseService();

	
	//@RequestMapping(value = "/insert.do")
	public String insert(Object entity, String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		try {
			this.getBaseService( ).insert(entity);
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}

	}

	
	//@RequestMapping(value = "/update.do")
	public String update(Object entity, String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		try {
			this.getBaseService( ).update(entity);
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}
	}

	
	//@RequestMapping(value = "/delete.do")
	public String delete(Object entity, String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		try {
			this.getBaseService( ).delete(entity);
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}

	}

	
	//@RequestMapping(value = "/deleteAll.do")
	public String deleteAll(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		try {
			this.getBaseService( ).deleteAll();
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}
	}

	
	//@RequestMapping(value = "/get.do")
	public String get(Serializable id, String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		try {
			Object result = this.getBaseService( ).get(id);
			request.setAttribute("result", result);
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}
	}

	
	//@RequestMapping(value = "/load.do")
	public String load(Serializable id, String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		try {
			Object result = this.getBaseService( ).load(id);
			request.setAttribute("result", result);
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}
	}

	
	//@RequestMapping(value = "/loadAll.do")
	public String loadAll(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		try {
			List resutl = this.getBaseService( ).loadAll();
			request.setAttribute("result", resutl);
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}
	}

	
	//@RequestMapping(value = "/count.do")
	public String count(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		try {
			long result = this.getBaseService( ).count();
			request.setAttribute("result", result);
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}
	}

	
	//@RequestMapping(value = "/iterator.do")
	public String iterator(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			Iterator result = this.getBaseService( ).iterator(param);
			request.setAttribute("result", result);
			return success_;
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return fail_;
		}
	}

	
	//@RequestMapping(value = "/uniqueQuery.do")
	public String uniqueQuery(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		SqlParameter param = this.getSqlParameter(request);
		try {
			Object result = this.getBaseService( ).uniqueQuery(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/query.do")
	public String query(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		SqlParameter param = this.getSqlParameter(request);
		try {

			List result = this.getBaseService( ).query(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/queryPage.do")
	public String queryPage(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		SqlParameter param = this.getSqlParameter(request);

		try {

			List result = this.getBaseService( ).queryPage(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/update.do")
	public String update(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		SqlParameter param = this.getSqlParameter(request);
		try {
			int result = this.getBaseService( ).update(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/queryBySQL.do")
	public String queryBySQL(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		// SqlParameter param=this.getSqlParameter(request);
		SqlParameter param = this.getSqlParameter(request);
		try {

			List result = this.getBaseService( ).queryBySQL(param);

			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/queryPageBySQL.do")
	public String queryPageBySQL(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = result = this.getBaseService( ).queryPageBySQL(
					param);

			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/updateBySQL.do")
	public String updateBySQL(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			this.getBaseService( ).updateBySQL(param);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/queryByNamed.do")
	public String queryByNamed(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = this.getBaseService( ).queryByNamed(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/queryPageByNamed.do")
	public String queryPageByNamed(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = this.getBaseService( ).queryPageByNamed(
					param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/updateByNamed.do")
	public String updateByNamed(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			this.getBaseService( ).updateByNamed(param);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/queryByJdbcProcedure.do")
	public String queryByJdbcProcedure(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			ProcedureResult result = this.getBaseService( )
					.queryByJdbcProcedure(param);

			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/queryByJdbcProcedureWithMap.do")
	public String queryByJdbcProcedureWithMap(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			ProcedureResult result = this.getBaseService( )
					.queryByJdbcProcedureWithMap(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/updateByJdbcProcedure.do")
	public String updateByJdbcProcedure(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			ProcedureResult result = this.getBaseService( )
					.updateByJdbcProcedure(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/getPageBeanByQuery.do")
	public String getPageBeanByQuery(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( ).getPageBeanByQuery(
					param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/getPageBeanBySQLQuery.do")
	public String getPageBeanBySQLQuery(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( )
					.getPageBeanBySQLQuery(param);

			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/getPageBeanByNamedQuery.do")
	public String getPageBeanByNamedQuery(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( )
					.getPageBeanByNamedQuery(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/getPageBeanByJdbcProcedure.do")
	public String getPageBeanByJdbcProcedure(String success_, String fail_,
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( )
					.getPageBeanByJdbcProcedure(param);

			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/getPageBeanByJdbcProcedureWithMap.do")
	public String getPageBeanByJdbcProcedureWithMap(String success_,
			String fail_, HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( )
					.getPageBeanByJdbcProcedureWithMap(param);
			request.setAttribute("result", result);
			if (success_ != null) {
				return success_;
			}
			return param.getUrl("success");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			if (fail_ != null) {
				return fail_;
			}
			return param.getUrl("fail");
		}
	}

	
	//@RequestMapping(value = "/insertByAjax.do")
	//@ResponseBody
	public Object insertByAjax(Object entity, HttpServletRequest request,
			HttpServletResponse response) {
		//SqlParameter param = this.getSqlParameter(request);
		try {
			this.getBaseService( ).insert(entity);
			return "success_";
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/updateByAjax.do")
	//@ResponseBody
	public Object updateByAjax(Object entity, HttpServletRequest request,
			HttpServletResponse response) {
		//SqlParameter param = this.getSqlParameter(request);
		try {
			this.getBaseService( ).update(entity);
			return "null";
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/deleteByAjax.do")
	//@ResponseBody
	public Object deleteByAjax(Object entity, HttpServletRequest request,
			HttpServletResponse response) {
		//SqlParameter param = this.getSqlParameter(request);
		try {
			this.getBaseService( ).delete(entity);
			return "null";
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/deleteAllByAjax.do")
	//@ResponseBody
	public Object deleteAllByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		//SqlParameter param = this.getSqlParameter(request);
		try {
			this.getBaseService( ).deleteAll();
			return "null";
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/getByAjax.do")
	//@ResponseBody
	public Object getByAjax(Serializable id, HttpServletRequest request,
			HttpServletResponse response) {
		//SqlParameter param = this.getSqlParameter(request);
		try {
			Object result = this.getBaseService( ).get(id);
			// request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/loadByAjax.do")
	//@ResponseBody
	public Object loadByAjax(Serializable id, HttpServletRequest request,
			HttpServletResponse response) {
		//SqlParameter param = this.getSqlParameter(request);
		try {
			Object result = this.getBaseService( ).load(id);
			// request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/loadAllByAjax.do")
	//@ResponseBody
	public Object loadAllByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		//SqlParameter param = this.getSqlParameter(request);
		try {
			
			List result = this.getBaseService( ).loadAll();
			
			// request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/countByAjax.do")
	//@ResponseBody
	public Object countByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		//SqlParameter param = this.getSqlParameter(request);
		try {
			Long result = this.getBaseService( ).count();
			// request.setAttribute("result", result);
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/iteratorByAjax.do")
	//@ResponseBody
	public Object iteratorByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			Iterator result = this.getBaseService( ).iterator(param);
			// request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/uniqueQueryByAjax.do")
	//@ResponseBody
	public Object uniqueQueryByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			Object result = this.getBaseService( ).uniqueQuery(param);
			// request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/queryByAjax.do")
	//@ResponseBody
	public Object queryByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = this.getBaseService( ).query(param);
			request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			//request.setAttribute("message", e.getMessage());
			System.out.println(e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/queryPageByAjax.do")
	//@ResponseBody
	public Object queryPageByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = this.getBaseService( ).queryPage(param);
			// request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/updateByAjax.do")
	//@ResponseBody
	public Object updateByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			int result = this.getBaseService( ).update(param);

			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/queryBySQLByAjax.do")
	//@ResponseBody
	public Object queryBySQLByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = this.getBaseService( ).queryBySQL(param);

			// request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/queryPageBySQLByAjax.do")
	//@ResponseBody
	public Object queryPageBySQLByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = this.getBaseService( ).queryPageBySQL(param);

			// request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;
		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/updateBySQLByAjax.do")
	//@ResponseBody
	public Object updateBySQLByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			int result = this.getBaseService( ).updateBySQL(param);

			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/queryByNamedByAjax.do")
	//@ResponseBody
	public Object queryByNamedByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = this.getBaseService( ).queryByNamed(param);
			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/queryPageByNamedByAjax.do")
	//@ResponseBody
	public Object queryPageByNamedByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			List result = this.getBaseService( ).queryPageByNamed(
					param);
			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/updateByNamedByAjax.do")
	//@ResponseBody
	public Object updateByNamedByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			int result = this.getBaseService( ).updateByNamed(param);

			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/queryByJdbcProcedureByAjax.do")
	//@ResponseBody
	public Object queryByJdbcProcedureByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			ProcedureResult result = this.getBaseService( )
					.queryByJdbcProcedure(param);

			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/queryByJdbcProcedureWithMapByAjax.do")
	//@ResponseBody
	public Object queryByJdbcProcedureWithMapByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			ProcedureResult result = this.getBaseService( )
					.queryByJdbcProcedureWithMap(param);
			request.setAttribute("result", result);
			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/updateByJdbcProcedureByAjax.do")
	//@ResponseBody
	public Object updateByJdbcProcedureByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			ProcedureResult result = this.getBaseService( )
					.updateByJdbcProcedure(param);
			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/getPageBeanByQueryByAjax.do")
	//@ResponseBody
	public String getPageBeanByQueryByAjax(SqlParameter param) {
		try {
			
			return this.getBaseService().getPageBeanJSONByQuery(param);

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}
	
	
	//@RequestMapping(value = "/getPageBeanBySQLQueryByAjax.do")
	//@ResponseBody
	public Object getPageBeanBySQLQueryByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( )
					.getPageBeanBySQLQuery(param);

			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/getPageBeanByNamedQueryByAjax.do")
	//@ResponseBody
	public Object getPageBeanByNamedQueryByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( )
					.getPageBeanByNamedQuery(param);
			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/getPageBeanByJdbcProcedureByAjax.do")
	//@ResponseBody
	public Object getPageBeanByJdbcProcedureByAjax(HttpServletRequest request,
			HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( )
					.getPageBeanByJdbcProcedure(param);

			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}

	
	//@RequestMapping(value = "/getPageBeanByJdbcProcedureWithMapByAjax.do")
	//@ResponseBody
	public Object getPageBeanByJdbcProcedureWithMapByAjax(
			HttpServletRequest request, HttpServletResponse response) {
		SqlParameter param = this.getSqlParameter(request);
		try {
			PageBean result = this.getBaseService( )
					.getPageBeanByJdbcProcedureWithMap(param);
			if (result == null) {
				return "null";
			}
			return result;

		} catch (Exception e) {
			// request.setAttribute("message", e.getMessage());
			return "fail_";
		}
	}
	
	public String update(Object entity){
		try{
			
			this.getBaseService().update(entity);
			return "success_";
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	public String batchUpdate(Object[] entities){
		try{
			
			this.getBaseService().batchUpdate(entities);
			return "success_";
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	public String add(Object entity){
		try{
			
			this.getBaseService().insert(entity);
			return "success_";
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	public String batchInsert(Object[] entities){
		try{
			
			this.getBaseService().batchInsert(entities);
			return "success_";
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	public String delete(Object entity){
		try{
			
			this.getBaseService().delete(entity);
			return "success_";
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	public String batchDelete(Object[] entities){
		try{
			
			this.getBaseService().batchDelete(entities);
			return "success_";
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	protected void resetQuerySqlParameter(String tableName,SqlParameter param){
		
		StringBuffer sb=new StringBuffer();
		//String queryString=param.getQueryString();
		
		sb.append("from ");
		sb.append(tableName);
		if(param.getParameterSize()>0){
			Parameter opParam=param.removeParameter("op");
			String op=(String)opParam.getValue();
			Parameter colParam=param.removeParameter("columnName");
			String columnName=((String)colParam.getValue()).replaceAll("\\s", "");
			//Object value1=param.getParameterValue("value1");
			//Object value2=param.getParameterValue("value2");
			sb.append(" where ");
			sb.append(columnName);
			if("between".equals(op)){
				sb.append(" between :value1 and :value2 ");
			}else if(op.contains("_")){
				sb.append(" like :value1");
			}else if("><".equals(op)){
				sb.append(" >:value1 and ");
				sb.append(columnName);
				sb.append(" <:value2 ");
			}else {
				sb.append(op);
				sb.append(" :value1 ");
			}
			
		}
		
		String sortName=param.getSortname();
		if(sortName==null||"".equals(sortName.trim() )){
			sortName="id";
		}else{
			sortName=sortName.replaceAll("\\s", "");
		}
		
		String sortOrder=param.getSortorder();
		
		if(sortOrder==null||"".equals(sortOrder.trim() )){
			sortOrder="asc";
		}else{
			sortOrder=sortOrder.replaceAll("\\s", "");
		}
		
		sb.append(" order by ");
		sb.append(sortName);
		sb.append(" ");
		sb.append(sortOrder);
		param.setQueryString(sb.toString());
	}
	
	protected WebApplicationContext getWebApplicationContext(HttpServletRequest request){
		return RequestContextUtils.getWebApplicationContext(request);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@ExceptionHandler(IOException.class)
	public String handleIOException(IOException ex, HttpServletRequest request) {
		String message=ex.getMessage();
		ex.printStackTrace();
		request.setAttribute("error", message);
	    return "error";
	}
	
}