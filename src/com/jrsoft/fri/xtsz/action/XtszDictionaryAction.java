package com.jrsoft.fri.xtsz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

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
			dictionary.setDictionary("0");
			dictionary.setFlag(flag);
			dictionary.setRemarks("刷新时间");
			request.setAttribute("list", dictionary);
		}
		
		return	new ActionForward("/jsp/xtsz/refresh/refresh.jsp");
		
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
	

}
