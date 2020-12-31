package cscenter.model;

import java.util.Date;

public class Comment {
	private int id;
	private int inquiryId;
	private String customerId;
	private String comment;
	private Date date;
	private boolean stateChangable;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getInquiryId() {
		return inquiryId;
	}
	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isStateChangable() {
		return stateChangable;
	}
	public void setStateChangable(boolean stateChangable) {
		this.stateChangable = stateChangable;
	}
	
	
	
}
