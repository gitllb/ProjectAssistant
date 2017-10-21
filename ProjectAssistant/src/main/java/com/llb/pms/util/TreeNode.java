package com.llb.pms.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	
    public String id;
    public String pid;
    public String style;
    public String path;
    public String url;
    public String text;
    public String loaded="0";
    public String icon;
    public List<TreeNode> children;
    public boolean hidden=true;
    public List<String> includeType;
    public List<String> excludeType;
    public String type="df";//取值 d f df ,d directory only ,f file only ,df both two 
    
    public int nodeWidth=200;
    public boolean checkbox=false;
    public boolean treeLine=true;
    public String iconPath="/Common/lib/ligerUI/skins/icons/";
    
	public TreeNode() {
		// TODO Auto-generated constructor stub
	}
	
	public TreeNode createHidedNode(TreeNode parent){
		TreeNode node=new TreeNode();
		//id: 2, pid: 1, text: 'use for empty folder',style:"display:none"
		node.setPid(parent.getId());
		node.setStyle("display:none");
		return node;
	}
	
	public void addChild(TreeNode node){
		if(this.children==null){
			this.children=new ArrayList();
		}
		this.children.add(node);
	}
	
	public void addChild(int index,TreeNode node){
		if(this.children==null){
			this.children=new ArrayList();
		}
		this.children.add(index, node);;
	}
	
	public TreeNode removeChild(int index){
		if(this.children==null){
			return null;
		}
		return this.children.remove(index);
	}
	
	public boolean removeChild(TreeNode node){
		if(this.children==null){
			return true;
		}
		return this.children.remove(node);
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	

	public String getLoaded() {
		return loaded;
	}

	public void setLoaded(String loaded) {
		this.loaded = loaded;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public List<String> getIncludeType() {
		return includeType;
	}

	public void setIncludeType(List<String> includeType) {
		this.includeType = includeType;
	}

	public List<String> getExcludeType() {
		return excludeType;
	}

	public void setExcludeType(List<String> excludeType) {
		this.excludeType = excludeType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNodeWidth() {
		return nodeWidth;
	}

	public void setNodeWidth(int nodeWidth) {
		this.nodeWidth = nodeWidth;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public boolean isTreeLine() {
		return treeLine;
	}

	public void setTreeLine(boolean treeLine) {
		this.treeLine = treeLine;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	
}
