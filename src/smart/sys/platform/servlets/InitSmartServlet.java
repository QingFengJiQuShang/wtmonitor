package smart.sys.platform.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import smart.sys.platform.springUtils.ContextHolder;



public class InitSmartServlet extends HttpServlet {
	private static final Log logger = LogFactory.getLog(InitSmartServlet.class);
	@Override
	public void init(ServletConfig config) throws ServletException {
		initContext(config);
		super.init(config);
	}
	
	/**
	 * 
	 * @param servlet
	 */
	protected void initContext(ServletConfig config) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("\u521d\u59cb\u5316 WebApplicationContext");	//"≥ı ºªØ WebApplicationContext"
			}
			
			WebApplicationContext context = WebApplicationContextUtils
					.getWebApplicationContext(config.getServletContext());
			ContextHolder.getInstance().setApplicationContext(context);
		} catch (Exception ex) {
			logger.error(ex.getMessage() + "\n" + ex.getStackTrace());
		}
	}
	
	

}
