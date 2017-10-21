package com.llb.pms.controller.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.llb.pms.hibernate.Project;

public class ListModel{
	private List<Project> items;
   
	public  ListModel() {
		// TODO Auto-generated constructor stub
		
	}

	public List<Project> getItems() {
		return items;
	}

	public void setItems(List<Project> items) {
		this.items = items;
	}

	
}
