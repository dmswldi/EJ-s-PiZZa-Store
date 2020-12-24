package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.Customer;
import auth.model.User;
import auth.service.WithdrawalService;
import controller.Handler;

public class WithdrawalHandler implements Handler {
	private static final String FORM_VIEW = "auth/withdrawalForm";
	private WithdrawalService withdrawalSvc = new WithdrawalService();
	
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
		Customer customer = (Customer) req.getSession().getAttribute("user");
		user.setId(customer.getId());
		user.setPassword(req.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(withdrawalSvc.withdrawal(user, errors)) {
			req.getSession().invalidate();
			return "auth/withdrawalSuccess";			
		} else {
			return FORM_VIEW;
		}
		
	}
}
