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
		String passwd = req.getParameter("passwd");
		String code=req.getParameter("code");
		String checkCode =(String)req.getSession().getAttribute("randCheckCode");
		JSONObject json = new JSONObject();
		int count=0;
		if(code.equalsIgnoreCase(checkCode)){
			String passwd1=StringUtils.encodeBase64(passwd);
			
			XtglUsers user = Users.login(loginName, passwd1);
			if (user != null) {
				Map<String, String> map=new Hashtable<String, String>();
				// �õ�application
				ServletContext application = req.getSession().getServletContext();
				Map<String, String> appMap = (Map<String, String>) application.getAttribute("app");
				if (appMap != null) {
					
					String value = appMap.get(user.getLoginname()
							+ user.getPassword() + "userkey");
					if (appMap.containsKey(user.getLoginname() + user.getPassword()
							+ "userkey")) {
						if (value.equals(req.getRemoteAddr())) {// ����û�����ͬ��ipҲ��ͬ����������
							System.out.println("�û����ڸ�̨�����ϵ�¼��");
							
							json.put("dl", 3);
						} else {// ����û�����ͬ��ip��ͬ��ֹͣ����
							System.out.println("�û��ѵ�¼��");
							json.put("dl", 5);
						}
					} else {
						map.put(loginName + passwd1
								+ "userkey", req.getRemoteAddr());
						/* begin�õ�application����ֵ* */
						application.setAttribute("app", map);
						Map<String, String> aMap=(Map<String, String>)application.getAttribute("app");
						System.out.println("dd");
						/* end�õ�application����ֵ* */
						
						
					}
				}else{
					//Map<String, String> map=new Hashtable<String, String>();
					map.put(loginName + passwd1
							+ "userkey", req.getRemoteAddr());
					/* begin�õ�application����ֵ* */
					application.setAttribute("app", map);
					
				}
				
				
				
				req.getSession().setAttribute("user", user);
				req.getSession().setAttribute("gridHeight", "450");

				// cookie��¼�û���
				int flag = 0;
				Cookie[] cookies = req.getCookies();
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("loginName")) {
						cookies[i].setValue(user.getLoginname());
						cookies[i].setMaxAge(60 * 60 * 24 * 365); // 1��
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
						cookies[i].setMaxAge(60 * 60 * 24 * 365); // 1��
						resp.addCookie(cookies[i]);
						flag = 1;
					}
				}
				if (flag == 0) {
					Cookie loginNameCookie = new Cookie("loginName", user
							.getLoginname());
					loginNameCookie.setMaxAge(60 * 60 * 24 * 365); // 1��
					Cookie loginNamesCookie = new Cookie("loginNames", user
							.getLoginname());
					loginNamesCookie.setMaxAge(60 * 60 * 24 * 365); // 1��
					resp.addCookie(loginNameCookie);
					resp.addCookie(loginNamesCookie);
				}

				Date date = new Date(System.currentTimeMillis());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				count=1;// ��½�ɹ���
			} else {
				count=2;  //�û������������
			}
		}else{
			count=3;//��֤���������
		}
		
		resp.setCharacterEncoding("utf-8");
		System.out.println(json.toString());

		JsonUtil.ajaxOutPutJsonOrCount(resp, count);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
