package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import cscenter.model.PostPage;
import cscenter.service.ReadListService;

public class ReadListHandler implements Handler {
	private static final String FORM_VIEW = "cs/readPostList";
	private ReadListService readListSvc = new ReadListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String pageNo = req.getParameter("pageNo");
		int pageNum = 1;
		if(pageNo != null) {
			pageNum = Integer.parseInt(pageNo);
		}
		
		PostPage postPage = readListSvc.readPostList(pageNum);
		req.setAttribute("page", postPage);
		
		return FORM_VIEW;
	}

}
