package com.jrsoft.fri.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/**
	 * 查询输出json
	 * @param response
	 * @param page
	 * @param rows
	 */
	public static void ajaxOutPutJson(HttpServletResponse response, Page page, JSONArray rows ){
		
		JSONObject jsonObj = new JSONObject(); 
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值  
        jsonObj.put("page",String.valueOf(page.getPageNum()));     // 当前页   
        jsonObj.put("total", String.valueOf(page.getPageCount()));    // 总页数   
        jsonObj.put("records", String.valueOf(page.getDataCount()));  // 总记录数
        jsonObj.put("rows", rows);
        
        // 输出，部署后删除
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
	 * 查询输出json
	 * @param response
	 * @param page
	 * @param rows
	 */
	public static void ajaxOutPutJson(HttpServletResponse response, JSONObject rows ){
		
		
        // 输出，部署后删除
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
	 * 查询输出json
	 * @param response
	 * @param page
	 * @param rows
	 */
	public static void ajaxSubOutPutJson(HttpServletResponse response, Page page, JSONArray rows ){
		
		JSONObject jsonObj = new JSONObject(); 
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值  
        jsonObj.put("rows1", rows);
        
        // 输出，部署后删除
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
	 * 添加、修改等操作返回结果：{success:true}{success:false}
	 * @param response
	 * @param result
	 */
	public static void ajaxOutPutJson(HttpServletResponse response, Boolean result ){
		
		JSONObject jsonObj = new JSONObject(); 
		jsonObj.put("success", result);
		// 输出，部署后删除
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
	 * 添加、修改等操作返回结果：{success:true}{success:false}
	 * @param response
	 * @param result
	 */
	public static void ajaxOutPutJsonOrCount(HttpServletResponse response,int count){
		
		JSONObject jsonObj = new JSONObject(); 
		jsonObj.put("success", count);
		// 输出，部署后删除
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
	 * 输出json格式字符串
	 * @param response
	 * @param result
	 */
	public static void ajaxOutPutJson(HttpServletResponse response, String str ){
		
		// 输出，部署后删除
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
	 * ajax请求session失效返回结果：{loseSession:true}
	 * @param response
	 * @param result
	 */
	public static void ajaxOutLoseSession(HttpServletResponse response){
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("loseSession", true);
		// 输出，部署后删除
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
