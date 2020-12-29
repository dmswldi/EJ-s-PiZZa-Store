package cscenter.model;

import java.util.List;

public class PostPage {
	private List<Post> list;
	private int total;
	private int totalPages;
	private int pageNum;// current page
	private int size;
	private int startPage;
	private int endPage;
	
	public PostPage(List<Post> list, int total, int pageNum, int size) {
		this.list = list;
		this.total = total;
		if(total%size != 0) {
			this.totalPages = total/size + 1;
		} else {
			this.totalPages = total/size;
		}
		this.pageNum = pageNum;
		this.size = size;
		this.startPage = ( (pageNum - 1) / 5 + 1 ) * 5 - size + 1;
		this.endPage = ( (pageNum - 1) / 5 + 1 ) * 5;
	}

	public List<Post> getList() {
		return list;
	}

	public void setList(List<Post> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
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
