package order.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import order.model.Menu;
import order.service.ReadMenuService;

public class ViewMenuHandler implements Handler {
	private static final String FORM_VIEW = "order/viewMenu";
	private ReadMenuService readMenuSvc = new ReadMenuService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<Menu> menus = readMenuSvc.readMenu();
		req.setAttribute("menus", menus);
		
		return FORM_VIEW;
	}
}
