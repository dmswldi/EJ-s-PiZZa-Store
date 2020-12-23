package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.JoinRequest;
import auth.service.JoinService;
import controller.Handler;

public class JoinHandler implements Handler {
	private static final String FORM_VIEW = "auth/join";
	private JoinService joinSvc = new JoinService();
	
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
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setPasswordCheck(req.getParameter("passwordCheck"));
		String[] phone = req.getParameterValues("phone");
		joinReq.setPhone(phone[0] + phone[1] + phone[2]);// string으로 저장
		joinReq.setAddress(req.getParameter("address"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		joinReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		// id 중복 시
		joinSvc.join(joinReq, errors);// id 중복 시 중복에러 내고 가입 폼으로 가고 싶음
		
		if(!errors.isEmpty()) {// 코드가 더러워...
			return FORM_VIEW;
		}
		
		return "home";
	}

}
