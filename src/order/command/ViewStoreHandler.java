package order.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import order.model.Menu;
import order.model.Store;
import order.service.ReadMenuService;

public class ViewStoreHandler implements Handler {
	private static final String FORM_VIEW = "order/viewStore";
	private ReadMenuService readMenuSvc = new ReadMenuService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<Store> stores = readMenuSvc.readStore();
		req.setAttribute("stores", stores);
		
		return FORM_VIEW;
	}
}
