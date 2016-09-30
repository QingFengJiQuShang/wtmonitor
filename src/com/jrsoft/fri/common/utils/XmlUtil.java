package com.jrsoft.fri.common.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
public class XmlUtil {
	/**
	 * ²éÑ¯Êä³öxml
	 * @param response
	 * @param str
	 */
	public static void ajaxOutPutXml(HttpServletResponse response, String str ){
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
        try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Æ´½Ó option
	 * @param select
	 * @param value
	 * @param text
	 */
	public static void appendOption(StringBuffer select, String value, String text ){
		select.append("<option value='").append(value).append("'>").append(text).append("</option>");
	}
}
