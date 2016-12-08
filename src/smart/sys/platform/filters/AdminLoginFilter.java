package smart.sys.platform.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class AdminLoginFilter extends HttpServlet implements Filter {
	private Log4jHelper log = new Log4jHelper(AdminLoginFilter.class);
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws ServletException, IOException {
			HttpServletResponse response = (HttpServletResponse) res;
			HttpServletRequest request = (HttpServletRequest) req;

			res.setContentType("text/html;charset=GBK");
			HttpSession userSession = request.getSession();
			String url = ((HttpServletRequest) req).getRequestURL().toString().trim();
			String url_err = "/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url_err);  
			if (userSession.getAttribute("admin") == null
					&& url.indexOf("login.jsp") < 0) {
				try {
					rd.forward(request, response);  
					//log.error("\u8bbf\u95ee\u540e\u53f0"+url+"\u65f6 \u672a\u53d1\u73b0\u767b\u5f55\u7ba1\u7406\u5458\uff01**********\u5df2\u62e6\u622a \u91cd\u65b0\u767b\u5f55");	//"访问后台"+url+"时 未发现登录管理员！**********已拦截 重新登录"
					return;
				} catch (Exception e) { }
			} else {
				chain.doFilter(req, res);
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
