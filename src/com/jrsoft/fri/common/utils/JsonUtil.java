package com.jrsoft.fri.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/**
	 * ��ѯ���json
	 * @param response
	 * @param page
	 * @param rows
	 */
	public static void ajaxOutPutJson(HttpServletResponse response, Page page, JSONArray rows ){
		
		JSONObject jsonObj = new JSONObject(); 
        // ����jqGrid��JSON�����ݸ�ʽҪ���jsonObj��ֵ  
        jsonObj.put("page",String.valueOf(page.getPageNum()));     // ��ǰҳ   
        jsonObj.put("total", String.valueOf(page.getPageCount()));    // ��ҳ��   
        jsonObj.put("records", String.valueOf(page.getDataCount()));  // �ܼ�¼��
        jsonObj.put("rows", rows);
        
        // ����������ɾ��
        System.out.println(jsonObj.toString());
        
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
        try {
			response.getWriter().print(jsonObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ���json
	 * @param response
	 * @param page
	 * @param rows
	 */
	public static void ajaxOutPutJson(HttpServletResponse response, JSONObject rows ){
		
		
        // ����������ɾ��
        System.out.println(rows.toString());
        
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
        try {
			response.getWriter().print(rows);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ѯ���json
	 * @param response
	 * @param page
	 * @param rows
	 */
	public static void ajaxSubOutPutJson(HttpServletResponse response, Page page, JSONArray rows ){
		
		JSONObject jsonObj = new JSONObject(); 
        // ����jqGrid��JSON�����ݸ�ʽҪ���jsonObj��ֵ  
        jsonObj.put("rows1", rows);
        
        // ����������ɾ��
        System.out.println(jsonObj.toString());
        
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
        try {
			response.getWriter().print(jsonObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ӡ��޸ĵȲ������ؽ����{success:true}{success:false}
	 * @param response
	 * @param result
	 */
	public static void ajaxOutPutJson(HttpServletResponse response, Boolean result ){
		
		JSONObject jsonObj = new JSONObject(); 
		jsonObj.put("success", result);
		// ����������ɾ��
        System.out.println(jsonObj.toString());
        
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
        try {
			response.getWriter().print(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ��ӡ��޸ĵȲ������ؽ����{success:true}{success:false}
	 * @param response
	 * @param result
	 */
	public static void ajaxOutPutJsonOrCount(HttpServletResponse response,int count){
		
		JSONObject jsonObj = new JSONObject(); 
		jsonObj.put("success", count);
		// ����������ɾ��
        System.out.println(jsonObj.toString());
        
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
        try {
			response.getWriter().print(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ���json��ʽ�ַ���
	 * @param response
	 * @param result
	 */
	public static void ajaxOutPutJson(HttpServletResponse response, String str ){
		
		// ����������ɾ��
        //System.out.println(str);
        
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
        try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ajax����sessionʧЧ���ؽ����{loseSession:true}
	 * @param response
	 * @param result
	 */
	public static void ajaxOutLoseSession(HttpServletResponse response){
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("loseSession", true);
		// ����������ɾ��
        System.out.println(jsonObj.toString());
        
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
        try {
			response.getWriter().print(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
