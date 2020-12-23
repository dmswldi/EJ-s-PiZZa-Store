package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {
	String process(HttpServletRequest req, HttpServletResponse res) throws IOException;
}
