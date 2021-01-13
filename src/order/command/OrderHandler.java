package order.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import auth.model.Customer;
import controller.Handler;
import order.model.Cart;
import order.model.Menu;
import order.model.Store;
import order.service.CartService;
import order.service.ReadService;

public class OrderHandler implements Handler {
	private static final String FORM_VIEW = "order/newOrder";
	private ReadService readSvc = new ReadService();
	private CartService cartSvc = new CartService();
	
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
		Customer customer = (Customer) req.getSession().getAttribute("user");
		
		List<Menu> menus = readSvc.readMenu();
		List<Store> stores = readSvc.readStore();
		List<Cart> cartList = cartSvc.getCart(customer.getId());
		
		req.setAttribute("dorw", req.getParameter("dorw"));//??? req 공유되는데 왜 설정해줘야 하지?
		req.setAttribute("menus", menus);
		req.setAttribute("stores", stores);
		
		if(cartList != null) {
			String result = new Gson().toJson(cartList);
			req.setAttribute("cartList", result);
		}
		
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		String payment = req.getParameter("payment");
		
		switch(payment) {
		case "Card": 
			// 카드사 선택, 카드 번호 입력 받기
			break;
		case "Phone": 
			// (주문 확인 후) 핸드폰 결제창으로 이동.,,,
			break;
		case "Cash":
			
			break;
		}
		
		return null;
	}
}
