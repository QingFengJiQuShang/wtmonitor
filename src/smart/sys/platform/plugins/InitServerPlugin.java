package smart.sys.platform.plugins;

import java.util.Date;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import smart.sys.platform.converters.DateConverter;
import smart.sys.platform.springUtils.ContextHolder;

public class InitServerPlugin implements PlugIn {
	private static final Log logger = LogFactory.getLog(InitServerPlugin.class);
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		ConvertUtils.register(new DateConverter(), Date.class);
	}
	
}
