package order.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Handler;
import order.model.Store;
import order.service.ReadService;

public class ViewStoreHandler implements Handler {
	private static final String FORM_VIEW = "order/viewStore";
	private ReadService readSvc = new ReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<Store> stores = readSvc.readStore();
		req.setAttribute("stores", stores);
		
		return FORM_VIEW;
	}
}
