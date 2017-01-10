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
import com.jrsoft.fri.xtsz.entity.City;
import com.jrsoft.fri.xtsz.service.CityService;
import com.jrsoft.fri.xtsz.service.ProvinceService;

public class CityAction  extends DispatchAction {
	private CityService cityService;
	private ProvinceService provinceService;

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	/**
	 * 根据省 查询市
	 * @param request
	 * @param response
	 */
	public void query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String province=request.getParameter("provinceid");
		String hql=" where  1=1   and father='"+province+"' " ;
		List<City> list=cityService.queryAll(hql);
		JSONArray array=new JSONArray();
		
		for(int i=0;i<list.size();i++){
			JSONObject cell = new JSONObject();
			City c=list.get(i);
			cell.put("cityid", c.getCityid());		//
			cell.put("city",c.getCity());		//
			array.add(cell);
		}
		JsonUtil.ajaxOutPutJson(response, array);
	}


}
