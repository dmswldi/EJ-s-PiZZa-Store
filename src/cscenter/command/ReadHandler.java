package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import cscenter.model.Post;
import cscenter.service.ReadService;

public class ReadHandler implements Handler {
	private static final String FORM_VIEW = "cs/readPost";
	private ReadService readSvc = new ReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int pageNo = Integer.parseInt(req.getParameter("pageNo"));
		Post post = readSvc.readPost(id);
		
		if(post != null) {
			req.setAttribute("post", post);
			req.setAttribute("pageNo", pageNo);
			return FORM_VIEW;			
		}
		return null;
	}
}
