package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.Customer;
import controller.Handler;
import cscenter.model.Comment;
import cscenter.service.ReplyAddService;

public class ReplyAddHandler implements Handler {
	private ReplyAddService replyAddSvc = new ReplyAddService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Customer customer = (Customer) req.getSession().getAttribute("user");
		Comment comment = new Comment();
		comment.setInquiryId(Integer.parseInt(req.getParameter("id")));
		comment.setComment(req.getParameter("comments"));
		comment.setCustomerId(customer.getUserId());
		if(customer.isManager()) {
			comment.setStateChangable(true);
		} else {
			comment.setStateChangable(false);
		}
		String pageNo = req.getParameter("pageNo");
		
		replyAddSvc.addReply(comment);// 어떤 게시물에 누가 어떤 코멘트를
		
		res.sendRedirect(req.getContextPath() + "/cs/read.do?id=" + comment.getInquiryId() + "&pageNo=" + pageNo);
		return null;
	}
}
