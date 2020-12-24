package auth.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;

public class LogoutHandler implements Handler {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.getSession().invalidate();
		
		res.sendRedirect(req.getContextPath() + "/home.jsp");
		return null;
	}
	
}
