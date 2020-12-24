package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.Customer;
import auth.model.ModifyRequest;
import auth.service.ModifyInfoService;
import controller.Handler;

public class ModifyInfoHandler implements Handler {
	private static final String FORM_VIEW = "auth/modifyForm";
	private ModifyInfoService modifyInfoSvc = new ModifyInfoService();
	
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
		Customer customer = (Customer) req.getSession().getAttribute("user");
		ModifyRequest modifyReq = new ModifyRequest();
		
		modifyReq.setId(customer.getId());
		modifyReq.setCurPassword(req.getParameter("curPassword"));
		modifyReq.setNewPassword(req.getParameter("newPassword"));
		modifyReq.setPasswordCheck(req.getParameter("passwordCheck"));
		String[] phone = req.getParameterValues("phone");
		modifyReq.setPhone(phone[0] + phone[1] + phone[2]);
		modifyReq.setAddress(req.getParameter("address"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		// 현재 pw가 맞는지
		if(!customer.matchPassword(modifyReq.getCurPassword())) {
			errors.put("pwNotMatch", true);
		}
		// 새 pw 2개가 일치하는지
		modifyReq.validate(errors); 
		
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		modifyInfoSvc.modify(modifyReq);
		
		res.sendRedirect(req.getContextPath() + "/myPage.do");
		return null;
	}
}
