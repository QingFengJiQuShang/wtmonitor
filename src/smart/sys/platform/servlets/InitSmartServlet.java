package smart.sys.platform.servlets;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jrsoft.fri.quartz.QuartzJob;
import com.jrsoft.fri.quartz.QuartzManager;

import smart.sys.platform.springUtils.ContextHolder;
import tcpip.MainServer;



public class InitSmartServlet extends HttpServlet {
	private static final Log logger = LogFactory.getLog(InitSmartServlet.class);
	@Override
	public void init(ServletConfig config) throws ServletException {
		initContext(config);
		super.init(config);

		//加载一个新线程，启动tcpIp 服务器端
		FutureTask<String> task = new FutureTask<String>(new Callable<String>(){
		       @Override
		       public String call() throws Exception {		    	
					MainServer mainServer=new MainServer();
					mainServer.TcpServer();
			return "Collection Completed";
		       }
				
			});
		new Thread(task).start();
		
		String job_name = "月初电梯修改流量";
		QuartzManager.addJob(job_name, QuartzJob.class, "0 44 14 1 * ?");  
	}
	
	/**
	 * 
	 * @param servlet
	 */
	protected void initContext(ServletConfig config) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("\u521d\u59cb\u5316 WebApplicationContext");	//"初始化 WebApplicationContext"
			}
			
			WebApplicationContext context = WebApplicationContextUtils
					.getWebApplicationContext(config.getServletContext());
			ContextHolder.getInstance().setApplicationContext(context);
		} catch (Exception ex) {
			logger.error(ex.getMessage() + "\n" + ex.getStackTrace());
		}
	}
	
	

}
