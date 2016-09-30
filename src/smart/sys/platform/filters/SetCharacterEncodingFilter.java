package smart.sys.platform.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import smart.sys.platform.log.Log4jHelper;

/**********************************************************
 * @author  gyf
 * @docRoot 过滤字符集
 * @version 1.0
 **********************************************************/
public class SetCharacterEncodingFilter implements Filter {
	private static final Log4jHelper log = new Log4jHelper(SetCharacterEncodingFilter.class);
	public SetCharacterEncodingFilter() {
	}

	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	protected boolean ignore = true;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * 根据输入参数设置字符集
	 * @param request ServletRequest
	 * @param response ServletResponse
	 * @param chain FilterChain
	 * @throws IOException,ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (ignore || request.getCharacterEncoding() == null) {
			encoding = selectEncoding(request);
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null) {
			ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			ignore = true;
		} else {
			ignore = false;
		}
		log.debug("\u5f00\u542f\u7f16\u7801\u8fc7\u8651===========>\u91cd\u65b0\u7f16\u7801\u4e3a"+this.encoding);	//"开启编码过虑===========>重新编码为"+this.encoding
	}

	protected String selectEncoding(ServletRequest request) {
		return this.encoding;
	}
}
