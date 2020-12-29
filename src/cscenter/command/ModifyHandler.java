package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import cscenter.model.ModifyReq;
import cscenter.service.ModifyService;

public class ModifyHandler implements Handler {
	private ModifyService modifySvc = new ModifyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ModifyReq modifyReq = new ModifyReq();
		String id = req.getParameter("id");
		modifyReq.setId(Integer.parseInt(id));
		modifyReq.setCategory(req.getParameter("category"));
		modifyReq.setTitle(req.getParameter("title"));
		modifyReq.setContent(req.getParameter("content"));
		
		modifySvc.modifyPost(modifyReq);
		
		String pageNo = req.getParameter("pageNo");
		res.sendRedirect(req.getContextPath() + "/cs/read.do?id=" + id + "&pageNo=" + pageNo);
		return null;
	}
}
