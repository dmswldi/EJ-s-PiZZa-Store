package init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
@WebListener
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
    	
    	String url = application.getInitParameter("jdbcUrl");
    	String user = application.getInitParameter("jdbcUser");
    	String pw = application.getInitParameter("jdbcPw");
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");// class loading 1번만!
//    		Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

    	ConnectionProvider.setConnection(url, user, pw);
    	
    	String root = application.getContextPath();
    	application.setAttribute("root", root);
    }
	
}
