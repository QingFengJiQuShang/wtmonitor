package com.jrsoft.fri.xtsz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.common.utils.JsonUtil;
import com.jrsoft.fri.dtjk.entity.DtjkPush;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;
import com.jrsoft.fri.xtsz.entity.XtszMessage;
import com.jrsoft.fri.xtsz.from.XtszFrom;
import com.jrsoft.fri.xtsz.service.XtszDictionaryService;

public class XtszDictionaryAction extends DispatchAction {
	private XtszDictionaryService dictionaryService;

	public XtszDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(XtszDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 *  查看 设置刷新时间
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String flag=request.getParameter("flag");
		String hql=" where flag='"+flag+"'";
		List<XtszDictionary> list=dictionaryService.query(hql);
		if(list.size()>0){
			request.setAttribute("list", list.get(0));
		}else{
			XtszDictionary dictionary=new XtszDictionary();
			if(flag.equals("0")){
				dictionary.setDictionary("0");
				dictionary.setRemarks("刷新时间");
			}else if(flag.equals("1")){
				dictionary.setDictionary("10");
				dictionary.setRemarks("单包流量");
			}
			dictionary.setFlag(flag);
			request.setAttribute("list", dictionary);
		}
		
		if(flag.equals("0")){
			return	new ActionForward("/jsp/xtsz/refresh/refresh.jsp");
		}else if(flag.equals("1")){
			return	new ActionForward("/jsp/xtsz/flow/flow.jsp");
		}else{
			return	new ActionForward("/jsp/xtsz/refresh/refresh.jsp");
		}
		
	}
	/**
	 * 编辑 修改短信内容
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtszFrom from=(XtszFrom)form;
		XtszDictionary entity =from.getDictionary();
		String hql=" where flag='"+entity.getFlag()+"'";
		List<XtszDictionary> list=dictionaryService.query(hql);
		if(list.size()>0){			
			//修改
			XtszDictionary dictionary=list.get(0);
			dictionary.setDictionary(entity.getDictionary());
			dictionaryService.update(dictionary);
		}else{
			//新增
			dictionaryService.save(entity);
		}
		
		return	new ActionForward("/dictionaryAction.do?method=findById&flag="+entity.getFlag());
	}
	
	/**
	 * 编辑 添加短信模板
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addTemplate(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtszFrom from=(XtszFrom)form;
		XtszDictionary entity =from.getDictionary();
		String hql=" where flag='"+entity.getFlag()+"'";
		List<XtszDictionary> list=dictionaryService.query(hql);
		if(list.size()>0){			
			//修改
			XtszDictionary dictionary=dictionaryService.get(entity.getId());
			dictionary.setDictionary(entity.getDictionary());
			dictionaryService.update(dictionary);
		}else{
			//新增
			dictionaryService.save(entity);
		}
		
		return	new ActionForward("/dictionaryAction.do?method=findByTemplate");
	}
	/**
	 * 编辑 查询短信模板
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findByTemplate(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String hql=" where flag='4' order by id ";
		List<XtszDictionary> list=dictionaryService.query(hql);
		request.setAttribute("list", list.get(0));
		List<String> strs=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			XtszDictionary dictionary=list.get(i);
			String str=dictionary.getRemarks();
			strs.add(str);
		}
		request.setAttribute("strs", strs);
		return	new ActionForward("/jsp/xtsz/message/template.jsp");
	}
	
	/**
	 *  根据模板类型 查询短信模板
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	public void template(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ) throws UnsupportedEncodingException{
		String remarks=request.getParameter("remarks");
		remarks=new String(remarks.getBytes("ISO-8859-1"),"UTF-8");
		String hql=" where flag='4' and remarks='"+remarks+"' ";
		List<XtszDictionary> list=dictionaryService.query(hql);
		request.setAttribute("list", list.get(0));
		JSONArray rows = new JSONArray();
		JSONObject cell = new JSONObject();
		cell.put("id", list.get(0).getId());		
		cell.put("remarks",  list.get(0).getRemarks());		
		cell.put("flag",  list.get(0).getFlag());		
		cell.put("dictionary",  list.get(0).getDictionary());		
		rows.add(cell);
		JsonUtil.ajaxOutPutJson(response, rows);
	}
	
}
