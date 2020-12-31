package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import cscenter.model.UpdateReplyReq;
import cscenter.service.ReplyModService;

public class ReplyModifyHandler implements Handler {
	private ReplyModService replyModSvc = new ReplyModService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		UpdateReplyReq updateReq = new UpdateReplyReq();
		String postId = req.getParameter("postId");
		String pageNo = req.getParameter("pageNo");
		updateReq.setId(Integer.parseInt(req.getParameter("commentId")));
		updateReq.setComment(req.getParameter("comments"));
		
		replyModSvc.modifyReply(updateReq);
		
		res.sendRedirect(req.getContextPath() + "/cs/read.do?id=" + postId + "&pageNo=" + pageNo);
		return null;
	}

}
