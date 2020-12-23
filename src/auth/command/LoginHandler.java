package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.LoginRequest;
import auth.service.LoginService;
import controller.Handler;

public class LoginHandler implements Handler {
	private static final String FORM_VIEW = "auth/login";
	LoginService loginSvc = new LoginService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equals("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equals("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		LoginRequest loginReq = new LoginRequest();
		loginReq.setId(req.getParameter("id"));
		loginReq.setPassword(req.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		loginSvc.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		return "home";
	}

	
}
