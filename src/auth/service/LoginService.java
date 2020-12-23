package auth.service;

import java.util.Map;

import auth.dao.CustomerDao;

public class LoginService {
	CustomerDao customerDao = new CustomerDao();

	public void validate(Map<String, Boolean> errors) {
		
	}

}
