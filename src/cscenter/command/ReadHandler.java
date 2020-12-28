package cscenter.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import cscenter.model.PostPage;
import cscenter.service.ReadService;

public class ReadHandler implements Handler {
	private static final String FORM_VIEW = "cs/readPostList";
	private ReadService readSvc = new ReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equals("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equals("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		// 목록 보기
		String pageNo = req.getParameter("pageNo");
		int pageNum = 1;
		if(pageNo != null) {
			pageNum = Integer.parseInt(pageNo);
		}
		
		PostPage postPage = readSvc.readPostList(pageNum);
		req.setAttribute("page", postPage);// 사실 여기서 6개 중에 5개 밖에 안 왔어 ㅎ,,,
		if(postPage == null) {
			// 게시물 없음 표시
			System.out.println("No Post");
		}
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		req.getParameter("id");
		return null;
	}
}
