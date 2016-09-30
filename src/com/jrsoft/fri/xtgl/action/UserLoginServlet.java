package com.jrsoft.fri.xtgl.action;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import smart.sys.log.main.SmartLog;

import com.jrsoft.fri.common.utils.JsonUtil;
import com.jrsoft.fri.common.utils.StringUtils;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
public class UserLoginServlet extends HttpServlet{
	private static final Log logger = LogFactory.getLog(UserLoginServlet.class);
	private String systemName;
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		systemName = config.getInitParameter("systemName");
		if (systemName == null) {
			systemName = "\u4e1a\u52a1\u7cfb\u7edf";
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String loginName = req.getParameter("username");
		String passwd = req.getParameter("password");
		String passwd1=StringUtils.encodeBase64(passwd);
		JSONObject json = new JSONObject();
		XtglUsers user = Users.login(loginName, passwd1);
		if (user != null) {
			Map<String, String> map=new Hashtable<String, String>();
			// 得到application
			ServletContext application = req.getSession().getServletContext();
			Map<String, String> appMap = (Map<String, String>) application.getAttribute("app");
			if (appMap != null) {
				
				String value = appMap.get(user.getLoginname()
						+ user.getPassword() + "userkey");
				if (appMap.containsKey(user.getLoginname() + user.getPassword()
						+ "userkey")) {
					if (value.equals(req.getRemoteAddr())) {// 如果用户名相同，ip也相同，继续访问
						System.out.println("用户已在该台机器上登录！");

						json.put("dl", 3);
					} else {// 如果用户名相同，ip不同，停止访问
						System.out.println("用户已登录！");
						json.put("dl", 5);
					}
				} else {
					map.put(loginName + passwd1
							+ "userkey", req.getRemoteAddr());
					/* begin得到application并存值* */
					application.setAttribute("app", map);
					Map<String, String> aMap=(Map<String, String>)application.getAttribute("app");
					System.out.println("dd");
					/* end得到application并存值* */
					
					
				}
			}else{
				//Map<String, String> map=new Hashtable<String, String>();
				map.put(loginName + passwd1
						+ "userkey", req.getRemoteAddr());
				/* begin得到application并存值* */
				application.setAttribute("app", map);
				
			}
			
			
			
			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("gridHeight", "450");

			// cookie记录用户名
			int flag = 0;
			Cookie[] cookies = req.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("loginName")) {
					cookies[i].setValue(user.getLoginname());
					cookies[i].setMaxAge(60 * 60 * 24 * 365); // 1年
					resp.addCookie(cookies[i]);
					flag = 1;
				} else if (cookies[i].getName().equals("loginNames")) {
					String loginNames = cookies[i].getValue();
					if (loginNames.indexOf(user.getLoginname()) < 0) {
						if (loginNames.split(",").length >= 6) {
							loginNames = loginNames.substring(0, loginNames
									.lastIndexOf(","));
						}
						loginNames = (user.getLoginname() + ",") + loginNames;
						cookies[i].setValue(loginNames);
					}
					cookies[i].setMaxAge(60 * 60 * 24 * 365); // 1年
					resp.addCookie(cookies[i]);
					flag = 1;
				}
			}
			if (flag == 0) {
				Cookie loginNameCookie = new Cookie("loginName", user
						.getLoginname());
				loginNameCookie.setMaxAge(60 * 60 * 24 * 365); // 1年
				Cookie loginNamesCookie = new Cookie("loginNames", user
						.getLoginname());
				loginNamesCookie.setMaxAge(60 * 60 * 24 * 365); // 1年
				resp.addCookie(loginNameCookie);
				resp.addCookie(loginNamesCookie);
			}

			Date date = new Date(System.currentTimeMillis());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.put("result", 1);
			json.put("msg", "\u767b\u5f55\u6210\u529f\uff01"); // 登陆成功！
		} else {
			json.put("result", 2);
			json.put("msg",
					"\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef\uff01"); // 用户名或密码错误！
		}
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
