package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import cscenter.service.RemoveService;

public class RemoveHandler implements Handler {
	private RemoveService removeSvc = new RemoveService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		removeSvc.removePost(id);
		
		res.sendRedirect(req.getContextPath() + "/cs/list.do");
		return null;
	}
}
