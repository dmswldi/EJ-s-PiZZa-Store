package order.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import order.model.Menu;
import order.model.Store;
import order.service.ReadMenuService;

public class OrderHandler implements Handler {
	private static final String FORM_VIEW = "order/newOrder";
	private ReadMenuService readMenuSvc = new ReadMenuService();
	
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
		int dorw = Integer.parseInt(req.getParameter("dorw"));
		
		List<Menu> menus = readMenuSvc.readMenu();
		List<Store> stores = readMenuSvc.readStore();
		
		req.setAttribute("menus", menus);
		req.setAttribute("stores", stores);
		
		if(dorw == 0) {// 배달
			req.setAttribute("dorw", "delivery");
		} else if(dorw == 1) {// 포장
			req.setAttribute("dorw", "takeout");
		}
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		
		
		
		return null;
	}
}
