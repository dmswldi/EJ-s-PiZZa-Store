package cscenter.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import cscenter.model.PostPage;
import cscenter.service.ReadListService;
import cscenter.service.ReplyReadService;

public class ReadListHandler implements Handler {
	private static final String FORM_VIEW = "cs/readPostList";
	private ReadListService readListSvc = new ReadListService();
	private ReplyReadService replyReadSvc = new ReplyReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String pageNo = req.getParameter("pageNo");
		int pageNum = 1;
		if(pageNo != null) {
			pageNum = Integer.parseInt(pageNo);
		}
		
		PostPage postPage = readListSvc.readPostList(pageNum);
		req.setAttribute("page", postPage);
		if(postPage != null) {
			List<Integer> replyCntList = replyReadSvc.getReplyCntList(postPage.getList());
			req.setAttribute("replyCntList", replyCntList);
		}
		
		return FORM_VIEW;
	}

}
