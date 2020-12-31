package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.Customer;
import controller.Handler;
import cscenter.model.PostStatus;
import cscenter.service.ReplyDeleteService;

public class ReplyDeleteHandler implements Handler {
	private ReplyDeleteService replyDelSvc = new ReplyDeleteService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Customer customer = (Customer) req.getSession().getAttribute("user");
		String id = req.getParameter("id");
		String postId = req.getParameter("postId");
		String pageNo = req.getParameter("pageNo");

		PostStatus postStatus = new PostStatus();
		postStatus.setPostId(Integer.parseInt(postId));
		postStatus.setStatus(0);
		
		replyDelSvc.deleteReply(postStatus, Integer.parseInt(id), customer.isManager());
		
		res.sendRedirect(req.getContextPath() + "/cs/read.do?id=" + postId + "&pageNo=" + pageNo);
		return null;
	}
}
