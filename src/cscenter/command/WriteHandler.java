package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.Customer;
import controller.Handler;
import cscenter.model.Postf;
import cscenter.service.WriteService;

public class WriteHandler implements Handler {
	private static final String FORM_VIEW = "cs/newPost";
	private WriteService writeSvc = new WriteService();
	
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
		Postf post = new Postf();
		post.setCategory(req.getParameter("category"));
		post.setCustomerId(customer.getUserId());
		post.setTitle(req.getParameter("title"));
		post.setContent(req.getParameter("content"));
		
		writeSvc.write(post);
		
		res.sendRedirect(req.getContextPath() + "/cs/list.do");
		return null;
	}
}
