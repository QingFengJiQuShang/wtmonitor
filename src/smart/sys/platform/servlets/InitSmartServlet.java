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

import com.jrsoft.fri.quartz.FlowJob;
import com.jrsoft.fri.quartz.MessageJob;
import com.jrsoft.fri.quartz.QuartzJob;
import com.jrsoft.fri.quartz.QuartzManager;
import com.jrsoft.fri.quartz.SafeJob;
import com.jrsoft.fri.quartz.ServiceState;

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
		
		String job_name = "自动生成短信";
		QuartzManager.addJob(job_name, MessageJob.class, "0 40 16 * * ?");  
		String job_name1 = "判断电梯服务状态";
		QuartzManager.addJob(job_name1, ServiceState.class, "0 40 16 * * ?"); 
		String job_name2 = "判断电梯保险状态";
		QuartzManager.addJob(job_name2, SafeJob.class, "0 43 16 * * ?"); 
		String job_name3 = "修改电梯流量";
		QuartzManager.addJob(job_name3, FlowJob.class, "0 0 12 * * ?"); 
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
