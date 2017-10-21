package com.llb.pms.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Service;

import com.llb.pms.dao.ProjectDao;

import com.llb.pms.hibernate.Project;
import com.llb.pms.service.FileService;
import com.llb.pms.util.TreeNode;
@Service("contentService")
public class FileServiceImpl implements FileService{
	
	@Resource
	private ProjectDao<Project> projectDao;
	
	
	
	public FileServiceImpl() {
		// TODO Auto-generated constructor stub
		
	}
	
	

	public ProjectDao<Project> getProjectDao() {
		return projectDao;
	}



	public void setProjectDao(ProjectDao<Project> projectDao) {
		this.projectDao = projectDao;
	}
	


	public Object loadContent(TreeNode node,HttpServletRequest request) {
		// TODO Auto-generated method stub
		List nodes=new ArrayList();
		String path=node.getPath();
		String id=node.getId();
		File file= file=new File(path);
		if(file.isFile()){
			StringBuffer sb=new StringBuffer();
			try {
				FileReader fr=new FileReader(file);
				BufferedReader br=new BufferedReader(fr);
				while(br.ready()){
					sb.append(br.readLine());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return sb.toString();
		}
		
		File[] files=null;
		if("root_".equals(path)){
			files=file.listRoots();
		}else{
			if(!file.exists()){
				return null;
			}
			files=file.listFiles();
		}
		
		for(int i=0;i<files.length;i++){
			File f=files[i];
			String fileName=f.getName();
			int index=fileName.lastIndexOf('.');
			if(f.isHidden()&&!"".equals(fileName)){
				if(node.isHidden()){
					continue;
				}
			}
			
			TreeNode n=new TreeNode();
			n.setId(id+i);
			n.setPid(id);
			n.setPath(f.getAbsolutePath());
			
			if("root_".equals(path)){
				fileName=f.getPath();
				if(!f.isDirectory()){
					continue;
				}
			}
			n.setIncludeType(node.getIncludeType());
			n.setExcludeType(node.getExcludeType());
			n.setHidden(node.isHidden());
			n.setIconPath(node.getIconPath());
			n.setType(node.getType());
			n.setText(fileName);
			n.setUrl(node.getUrl());
			
			
			if(f.isDirectory()){
				if(!node.getType().contains("d")){
					continue;
				}
				n.addChild(n.createHidedNode(n));
			}else{
				
				if(!node.getType().contains("f")){
					continue;
				}
				
				String fileType=fileName.substring(index+1).toLowerCase();
				if(index==-1){
					fileType="";
				}
				List<String> includeType=node.getIncludeType();
				
				if(includeType!=null){
					boolean allowed=false;
					for(String t:includeType){
						allowed=false;
						if("null".equals(t)){
							allowed=true;
							continue;
						}
						t=t.toLowerCase();
						if(t.equals(fileType)){
							allowed=true;
							break;
						}
					}
					
					if(!allowed){
						continue;
					}
				}
				
				List<String> excludeType=node.getExcludeType();
				if(excludeType!=null){
					boolean limit=false;
					for(String t:excludeType){
						
						t=t.toLowerCase();
						if(t.equals(fileType)){
							limit=true;
						}
					}
					
					if(limit){
						continue;
					}
				}
				
				
				if(index!=-1){
					
					String classesPath=this.getClass().getResource("/").getPath();
					File classesFile=new File(classesPath);
					String webApps=classesFile.getParentFile().getParentFile().getParent();
					//webApps=webApps.substring(0,webApps.length()-1);
					
					String icon=node.getIconPath()+fileType+".gif";
					String iconPath=webApps+icon;
					
					 if((new File(iconPath)).exists()){
						 n.setIcon(icon);
					 }
				}
			}
			
			nodes.add(n);
		}
		
		return nodes;
	}


	//@Override
	public boolean createFolder(String name, String path) {
		// TODO Auto-generated method stub
		File file=new File(path,name);
		if(file.exists()){
			//return ;
		}
		return file.mkdirs();
		
	}

	//@Override
	public boolean deleteFile(String path) {
		// TODO Auto-generated method stub
		//String sql="delete from Project p where p.path=:path";
		//SqlParameter param=new SqlParameter(sql);
		//param.addParameter("path",path);
		File file=new File(path);
		return file.delete();
	}


//
//	@Override
//	public boolean createRequirementReport(String name, String path,int tpId) {
//		// TODO Auto-generated method stub
//		Templet templet=this.templetDao.get(tpId);
//		String templetPath=templet.getPath();
//		Filetype filetype=templet.getFiletype();
//		String suffix=filetype.getSuffix();
//		String reqPath=path+"/"+name+"."+suffix;
//		try {
//			FileUtil.buildFile(reqPath, templetPath, null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		
//		return true;
//	}



	//@Override
	public List<TreeNode> loadProjectsContent() {
		// TODO Auto-generated method stub
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		String classesPath=this.getClass().getResource("/").getFile();
		File classesFile=new File(classesPath);
		String path=classesFile.getParent()+"/projects";
		TreeNode projectsTreeNode=new TreeNode();
		projectsTreeNode.setText("projects");
		projectsTreeNode.setPid("1");
		projectsTreeNode.setId("2");
		projectsTreeNode.setPath(path);
		projectsTreeNode.setUrl("/Common/loadContent.do");
		projectsTreeNode.addChild(projectsTreeNode.createHidedNode(projectsTreeNode));
		treeNodes.add(projectsTreeNode);
		return treeNodes;
	}



	//@Override
	public boolean createRequirementReport(String name, String path, int tpId) {
		// TODO Auto-generated method stub
		return false;
	}


}
