package cscenter.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.Customer;
import controller.Handler;
import cscenter.model.PostPage;
import cscenter.service.ReadMyListService;
import cscenter.service.ReplyReadService;

public class ReadMyListHandler implements Handler {
	private static final String FORM_VIEW = "cs/readMyList";
	private ReadMyListService readMyListSvc = new ReadMyListService();
	private ReplyReadService replyReadSvc = new ReplyReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String pageNo = req.getParameter("pageNo");
		int pageNum = 1;
		if(pageNo != null) {
			pageNum = Integer.parseInt(pageNo);
		}
		
		Customer customer = (Customer) req.getSession().getAttribute("user");
		
		PostPage postPage = readMyListSvc.readPostList(pageNum, customer.getId());
		req.setAttribute("page", postPage);
		if(postPage != null) {
			List<Integer> replyCntList = replyReadSvc.getReplyCntList(postPage.getList());
			req.setAttribute("replyCntList", replyCntList);			
		}
		
		return FORM_VIEW;
	}
}
