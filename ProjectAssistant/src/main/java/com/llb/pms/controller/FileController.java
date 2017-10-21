package com.llb.pms.controller;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llb.pms.service.FileService;
import com.llb.pms.util.TreeNode;


@Controller
public class FileController {
	
	@Resource
	private FileService fileService;
	
	
	public FileService getContentService() {
		return fileService;
	}

	public void setContentService(FileService fileService) {
		this.fileService = fileService;
	}

	@RequestMapping(value="/goContent.do")
	public String goContent(TreeNode node,HttpServletRequest request,HttpServletResponse response){
		try{
			node.setIconPath("/Common/lib/ligerUI/skins/icons/");
			String url=node.getUrl();
			if(url==null){
				url="/Common/loadContent.do";
			}
			node.setUrl(url);
			request.setAttribute("message", "test");
			request.setAttribute("node", node);
			return "content";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "Service is budy. Try again later!");
			return "error";
		}
		
	}
	
	@RequestMapping(value="/loadContent.do")
	@ResponseBody
	public Object loadContent(TreeNode node,HttpServletRequest request,HttpServletResponse response){
		try{
			return this.fileService.loadContent(node,request);
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList();
		}
		
	}
	
	@RequestMapping(value="/loadProjectsContent.do")
	@ResponseBody
	public Object loadProjectsContent(TreeNode node,HttpServletRequest request,HttpServletResponse response){
		try{
			return this.fileService.loadProjectsContent();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList();
		}
		
	}
	
	@RequestMapping(value="/createFolder.do")
	@ResponseBody
	public Object createFolder(String name,String path,HttpServletRequest request,HttpServletResponse response){
		try{
			boolean result=this.fileService.createFolder(name,path);
			if(result){
				return "success_";
			}else{
				return "exists_";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail_";
		}
		
	}
	
	@RequestMapping(value="/deleteFile.do")
	@ResponseBody
	public Object deleteFile(String name,String path,HttpServletRequest request,HttpServletResponse response){
		try{
			boolean result=this.fileService.deleteFile(path);
			if(result){
				return "success_";
			}else{
				return "fail_";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "fail_";
		}
		
	}
	
	@RequestMapping(value="/createRequirementReport.do")
	@ResponseBody
	public Object createRequirementReport(String name,String path,int tpId,HttpServletRequest request,HttpServletResponse response){
		try{
			boolean result=this.fileService.createRequirementReport(name,path,tpId);
			
			if(result){
				return "success_";
			}else{
				return java.net.URLEncoder.encode("新建需求分析失败！", "utf-8");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "fail_";
		}
		
	}
	
	@RequestMapping(value="/goService.do")
	
	public String goService(HttpServletRequest request,HttpServletResponse response){
		String classesPath=this.getClass().getResource("/").getFile();
		File classesFile=new File(classesPath);
		String path=classesFile.getParentFile().getParentFile().getPath();
		return "forward:/goContent.do?path="+path;
	}
	
	
}