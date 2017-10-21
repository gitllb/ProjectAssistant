package com.llb.pms.util;

import java.util.List;

public class PageBean {

	public boolean isValid;// PageBean是否可用

	public int index;// 当前页

	public int back;// 上一页

	public int next;// 下一页

	public int first = 1;//首页

	public int last = -1;// 尾页

	public int pages = 0;// 总页数

	public int size=20;// 每页显示行数

	public List result;// 查询结果集
	
	public long total;//查询总数

	public int indexes=10;//页面页码数

	public int begin;// 起绐页码

	public int end;//结束页码
	
	public PageBean(List result, int index, int size) {
		this.index = index;
		this.size = size;
		this.fillData(result,result.size());
	}
	
	public PageBean(List result, int index, int size, int total) {
		this.index = index;
		this.size = size;
		this.total=total;
		this.fillData(result,total);
	}

	public PageBean(List result, int index, int size, int total, int indexes) {
		this.index = index;
		this.size = size;
		this.indexes = indexes;
		this.total=total;
		this.fillData(result,total);
	}
	
	public PageBean( int index, int size,  int indexes,int pages) {
		this.index = index;
		this.size = size;
		this.indexes = indexes;
		this.pages=pages;
	}
	
	public void fillData(List result, long total) {
		this.result = result;
		long pages=total % size == 0 ? total / size : total / size + 1;
		this.pages = Long.valueOf(pages).intValue();
		this.last = this.pages;
		this.next = this.index == this.last ? this.last :this.index + 1;
		this.back = this.index == 1 ? 1 : this.index - 1;
		
		this.begin = this.index % this.size == 0 ? (this.index - this.size + 1) : this.index
				- index % indexes + 1;
		this.end = (this.begin+ indexes - 1) < this.last ? (this.begin
				+ indexes - 1)
				: this.last;
		this.isValid = this.check(result, index, last);
	}

	
	
	public int getCurrent() {
		return index;
	}

	public void setCurrent(int index) {
		this.index = index;
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

	public int getPageCount() {
		return pages;
	}

	public void setPageCount(int pages) {
		this.pages = pages;
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

	public int getPageIndexs() {
		return indexes;
	}

	public void setPageIndexs(int indexes) {
		this.indexes = indexes;
	}

	public int getPageSize() {
		return size;
	}

	public void setPageSize(int size) {
		this.size = size;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}


	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	private boolean check(List result, int index, int last) {
		if (result == null)
			return false;
		if (index < 1 || index > last)
			return false;
		return true;
	}
	
	
}
