package smart.sys.platform.filters;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jrsoft.fri.xtgl.entity.XtglUsers;

import net.sf.json.JSONObject;

import smart.sys.platform.log.Log4jHelper;
import smart.sys.platform.utils.StringUtils;



/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class UserLoginFilter extends HttpServlet implements Filter {
	private Log4jHelper log = new Log4jHelper(UserLoginFilter.class);
	private String loginPage;

	public void init(FilterConfig filterConfig) {
		loginPage = filterConfig.getInitParameter("loginPage");
		if(loginPage==null){
			loginPage = "/login.jsp";
		}
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws ServletException, IOException {
			HttpServletResponse response = (HttpServletResponse) res;
			HttpServletRequest request = (HttpServletRequest) req;

			res.setContentType("text/html;charset=GBK");
			HttpSession userSession = request.getSession();
			String url = ((HttpServletRequest) req).getRequestURL().toString().trim();
			String urlCode = StringUtils.encodeBase64(url);
			if (userSession.getAttribute("user")== null
					&& url.indexOf("userLogin") < 0
					&& url.indexOf("login.jsp") < 0
					//||(userSession.getAttribute("user")== null&&((url.substring(url.length()-11,url.length())).equals("/PoliceFri/")||(url.substring(url.length()-10,url.length())).equals("/PoliceFri")))
					) {
				//response.sendRedirect(url_err + "?url=" + urlCode);
				request.getRequestDispatcher(loginPage + "?url=" + urlCode).forward(request, response);
				log.error("\u8bf7\u6c42"+url+"\u65f6 \u7528\u6237\u672a\u767b\u5f55\uff01**********\u91cd\u65b0\u767b\u5f55");	//"请求"+url+"时 用户未登录！**********重新登录"
			} else {
				chain.doFilter(req, res);
				
				
				XtglUsers user=(XtglUsers)request.getSession().getAttribute("user");//得到登入的用户
				JSONObject json = new JSONObject();
				if(user!=null){
				//得到application
				ServletContext application = request.getSession().getServletContext();
				Map<String, String> appMap =(Map<String, String>)application.getAttribute("app");
				if(appMap!=null){
					String value=appMap.get(user.getLoginname()+user.getPassword()+"userkey");
					if(appMap.containsKey(user.getLoginname()+user.getPassword()+"userkey")){
						if(value.equals(req.getRemoteAddr())){//如果用户名相同，ip也相同，继续访问
				    		System.out.println("url-----------------:"+url);
				    		//appMap.remove(key);
				    	}else{//如果用户名相同，ip不同，停止访问
				    		//appMap.remove(user.getLoginname()+user.getPassword()+"userkey");
				    		request.getRequestDispatcher(loginPage + "?url=" + urlCode).forward(request, response);
							log.error("\u8bf7\u6c42"+url+"\u65f6 \u7528\u6237\u672a\u767b\u5f55\uff01**********\u91cd\u65b0\u767b\u5f55");	//"请求"+url+"时 用户未登录！**********重新登录"
				    	}
					}
				}
				}
				
			}
	}

	public FilterConfig getFilterConfig() {
		return null;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
