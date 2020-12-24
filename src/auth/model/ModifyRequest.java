package auth.model;

import java.util.Map;

public class ModifyRequest {
	private String id;
	private String curPassword;
	private String newPassword;
	private String passwordCheck;
	private String phone;
	private String address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCurPassword() {
		return curPassword;
	}
	public void setCurPassword(String curPassword) {
		this.curPassword = curPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(!newPassword.equals(passwordCheck)) {
			errors.put("passwordNotEqual", true);
		}
	}

}
