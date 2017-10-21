package  com.llb.pms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.llb.pms.util.TreeNode;

public interface FileService{
	
	public Object loadContent(TreeNode node,HttpServletRequest request);

	public boolean createFolder(String name, String path);

	public boolean deleteFile(String path);

	public boolean createRequirementReport(String name, String path,int tpId);
	
	public List<TreeNode> loadProjectsContent();
}