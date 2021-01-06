package order.process;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.Customer;
import order.model.Cart;
import order.service.CartService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cartSvc = new CartService();
		
		String menuId = request.getParameter("menuId");
		String ea = request.getParameter("ea");
		
		Cart cart = new Cart();
		cart.setMenuId(Integer.parseInt(menuId));
		cart.setEa(Integer.parseInt(ea));
		
		Customer customer = (Customer) request.getSession().getAttribute("user");
		
		cartSvc.add(cart, customer.getId());
		List<Cart> cartList = cartSvc.getCart(customer.getId());// return 하고 싶어요
		
		//System.out.println(request.getParameter("abc"));
		//System.out.println(request.getParameter("d"));
	}

}
