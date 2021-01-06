package order.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import order.model.Menu;
import order.service.ReadService;

public class ViewMenuHandler implements Handler {
	private static final String FORM_VIEW = "order/viewMenu";
	private ReadService readSvc = new ReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<Menu> menus = readSvc.readMenu();
		req.setAttribute("menus", menus);
		
		return FORM_VIEW;
	}
}
