package  com.llb.pms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.llb.pms.dao.impl.SqlParameter;
import com.llb.pms.hibernate.Project;
import com.llb.pms.util.TreeNode;

public interface ProjectService<Project> extends BaseService<Project>{

	public List<TreeNode> loadProjectContent(HttpServletRequest request);


}