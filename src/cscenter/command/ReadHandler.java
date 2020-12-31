package cscenter.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.Customer;
import controller.Handler;
import cscenter.model.Comment;
import cscenter.model.Post;
import cscenter.service.ReadService;
import cscenter.service.ReplyReadService;

public class ReadHandler implements Handler {
	private static final String FORM_VIEW = "cs/readPost";
	private ReadService readSvc = new ReadService();
	private ReplyReadService replyReadSvc = new ReplyReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int pageNo = Integer.parseInt(req.getParameter("pageNo"));
		Post post = readSvc.readPost(id);
		
		if(post != null) {
			// comments
			List<Comment> comments = replyReadSvc.getReplyList(id);
			req.setAttribute("comments", comments);
			int cnt = replyReadSvc.getReplyCnt(id);
			req.setAttribute("replyCnt", cnt);

			req.setAttribute("post", post);
			req.setAttribute("pageNo", pageNo);
			return FORM_VIEW;			
		}
		return null;
	}
}
