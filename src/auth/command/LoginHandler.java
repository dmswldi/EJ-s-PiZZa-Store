package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.model.Customer;
import auth.model.User;
import auth.service.LoginService;
import controller.Handler;

public class LoginHandler implements Handler {
	private static final String FORM_VIEW = "auth/loginForm";
	private LoginService loginSvc = new LoginService();
	
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		User user = new User();
		user.setId(req.getParameter("id"));
		user.setPassword(req.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		Customer customer = loginSvc.login(user, errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		HttpSession session = req.getSession();
		String stay = req.getParameter("StayLoggedIn");
		if(stay != null && stay.equals("on")) {
			session.setMaxInactiveInterval(604800);// second, 7 day
			Cookie[] cookies = req.getCookies();
			
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					cookie.setMaxAge(604800);
					cookie.setHttpOnly(true);
					cookie.setPath(cookie.getPath());
					res.addCookie(cookie);
				}
			}
		}
		session.setAttribute("user", customer);
		
		String requestedURI = (String) session.getAttribute("link");
		if(requestedURI != null) {
			res.sendRedirect(requestedURI);
		} else {
			res.sendRedirect(req.getContextPath() + "/home.jsp");			
		}
		return null;
	}

	
}
