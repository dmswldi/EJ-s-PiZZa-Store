package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements Handler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.sendError(HttpServletResponse.SC_BAD_GATEWAY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
