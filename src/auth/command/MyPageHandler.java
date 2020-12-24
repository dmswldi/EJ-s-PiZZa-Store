package auth.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;

public class MyPageHandler implements Handler {
	private static final String FORM_VIEW = "auth/myPage";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		return FORM_VIEW;
	}
	
}
