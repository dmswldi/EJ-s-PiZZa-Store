package cscenter.model;

import java.util.List;

public class PostPage {
	private List<APost> list;
	private int total;
	private int pageNum;
	private int size;
	private int startPage;
	private int endPage;
	
	public PostPage(List<APost> list, int total, int pageNum, int size) {
		this.list = list;
		this.total = total;
		this.pageNum = pageNum;
		this.size = size;
		this.startPage = ( (pageNum - 1) / 5 + 1 ) * 5 - size;
		this.endPage = ( (pageNum - 1) / 5 + 1 ) * 5;
	}

	public List<APost> getList() {
		return list;
	}

	public void setList(List<APost> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
}
