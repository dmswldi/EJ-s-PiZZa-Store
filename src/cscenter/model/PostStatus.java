package cscenter.model;

public class PostStatus {
	private int postId;
	private int status;// 0: 답변대기, 1: 답변완료
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
