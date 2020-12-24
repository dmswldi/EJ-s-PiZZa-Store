package controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CentralController
 */
public class CentralController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Handler> map;// 경로, 핸들러 매핑
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CentralController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	map = new HashMap<>(); // ... 왜 여기에?
    	String configFile = getServletConfig().getInitParameter("configFile");
    	String configFilePath = getServletContext().getRealPath(configFile);
    	
    	try (FileReader fr = new FileReader(configFilePath)) {
    		Properties properties = new Properties();
    		properties.load(fr);// Reads a property list
			
    		Set<Object> keySet = properties.keySet();
    		
    		for(Object key : keySet) {
    			Object value = properties.get(key);
    			Object handler = Class.forName((String) value).newInstance();
    			map.put((String)key, (Handler)handler);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	// 모든 *.do에 대해 일하는 servlet
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = getServletContext().getContextPath();
		
		if(uri.startsWith(contextPath)){// contextPath 제거
			uri = uri.substring(contextPath.length());
		}
		
		// map에 uri에 해당하는 .do가 있으면 매칭 핸들러의 process() 실행
		Handler handler = map.get(uri);
		
		if(handler == null) {
			handler = new NullHandler();// SC_BAD_GATEWAY
		}
		
		String view = null;
		view = handler.process(request, response);
		
		if(view != null) {
			request.getRequestDispatcher("/WEB-INF/view/" + view + ".jsp").forward(request, response);
		}
	}

}
