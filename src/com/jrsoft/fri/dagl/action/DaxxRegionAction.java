package com.jrsoft.fri.dagl.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.common.utils.BaseFormUtil;
import com.jrsoft.fri.dagl.entity.DaxxRegion;
import com.jrsoft.fri.dagl.from.DaglFrom;
import com.jrsoft.fri.dagl.service.DaxxRegionService;

public class DaxxRegionAction extends DispatchAction {
	private DaxxRegionService regionService;

	public DaxxRegionService getRegionService() {
		return regionService;
	}

	public void setRegionService(DaxxRegionService regionService) {
		this.regionService = regionService;
	}
	
	
	/**
	 * 新增区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DaglFrom daglFrom=(DaglFrom)form;
		DaxxRegion region =daglFrom.getRegion();
		
		regionService.save(region);
		System.out.println("添加成功");
	    return	new ActionForward("/jsp/dagl/region/regionList.jsp");
	}
	
	/**
	 * 新增区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(HttpServletRequest request, HttpServletResponse response,DaxxRegion region )
			throws Exception {
		DaxxRegion daxxRegion=regionService.get(region.getId());
		
		if(daxxRegion!=null){
			daxxRegion.setRegion(region.getRegion());
			daxxRegion.setClientId(region.getClientId());
			regionService.update(daxxRegion);
		}
		return null;
	}
	
	/**
	 * 删除区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(HttpServletRequest request, HttpServletResponse response,DaxxRegion region )
			throws Exception {
		int id=Integer.parseInt(request.getParameter("id"));
		regionService.delete(id);
		return null;
	}
	/**
	 * 批量 删除 区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(HttpServletRequest request, HttpServletResponse response,DaxxRegion region )
			throws Exception {
		String ids=request.getParameter("ids");
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				regionService.delete(Integer.parseInt(arr[i]));
			}
		}
		return null;
		
	}

}
