package com.jrsoft.fri.xtsz.action;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.jrsoft.fri.common.utils.JsonUtil;
import com.jrsoft.fri.xtsz.entity.Area;
import com.jrsoft.fri.xtsz.service.AreaService;
import com.jrsoft.fri.xtsz.service.CityService;
import com.jrsoft.fri.xtsz.service.ProvinceService;

public class AreaAction extends DispatchAction {
	private AreaService areaService;
	private CityService cityService;
	private ProvinceService provinceService;
	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	/**
	 * 根据市 查询区
	 * @param request
	 * @param response
	 */
	public void query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String city=request.getParameter("cityid");
		String hql=" where  1=1  and father='"+city+"' " ;
		List<Area> list=areaService.queryAll(hql);
		JSONArray array=new JSONArray();
		
		for(int i=0;i<list.size();i++){
			JSONObject cell = new JSONObject();
			Area c=list.get(i);
			cell.put("areaid", c.getAreaid());		//
			cell.put("area",c.getArea());		//
			array.add(cell);
		}
		JsonUtil.ajaxOutPutJson(response, array);
	}

}
