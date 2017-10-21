package com.llb.pms.dao.impl;

import java.util.List;

public class PageBean {

	public boolean isValid;// PageBean是否可用

	public int page;// 当前�?

	public int back;// 上一�?

	public int next;// 下一�?

	public int first = 1;//首页

	public int last = -1;// 尾页

	public int pages = 0;// 总页�?

	public int pagesize=30;// 每页显示行数

	public List rows;// 查询结果�?
	
	public long total;//查询总数

	public int indexes=10;//页面页码�?

	public int begin;// 起绐页码

	public int end;//结束页码
	
	public PageBean(List rows, int page, int pagesize) {
		this.page = page;
		this.pagesize = pagesize;
		this.fillData(rows,rows.size());
	}
	
	public PageBean(List rows, int page, int pagesize, int total) {
		this.page = page;
		this.pagesize = pagesize;
		this.total=total;
		this.fillData(rows,total);
	}

	public PageBean(List rows, int page, int pagesize, int total, int indexes) {
		this.page = page;
		this.pagesize = pagesize;
		this.indexes = indexes;
		this.total=total;
		this.fillData(rows,total);
	}
	
	public PageBean( int page, int pagesize,  int indexes,int pages) {
		this.page = page;
		this.pagesize = pagesize;
		this.indexes = indexes;
		this.pages=pages;
	}
	
	public void fillData(List rows, long total) {
		this.rows = rows;
		long pages=total % pagesize == 0 ? total / pagesize : total / pagesize + 1;
		this.pages = Long.valueOf(pages).intValue();
		this.last = this.pages;
		this.next = this.page == this.last ? this.last :this.page + 1;
		this.back = this.page == 1 ? 1 : this.page - 1;
		
		this.begin = this.page % this.pagesize == 0 ? (this.page - this.pagesize + 1) : this.page
				- page % indexes + 1;
		this.end = (this.begin+ indexes - 1) < this.last ? (this.begin
				+ indexes - 1)
				: this.last;
		this.isValid = this.check(rows, page, last);
	}



	
	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	
	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getBack() {
		return back;
	}

	public void setBack(int back) {
		this.back = back;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	

	public int getPageSize() {
		return pagesize;
	}

	public void setPageSize(int pagesize) {
		this.pagesize = pagesize;
	}

	
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getIndexes() {
		return indexes;
	}

	public void setIndexes(int indexes) {
		this.indexes = indexes;
	}

	private boolean check(List rows, int page, int last) {
		if (rows == null)
			return false;
		if (page < 1 || page > last)
			return false;
		return true;
	}
	
	
}
