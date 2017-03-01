package com.jrsoft.fri.xtsz.action;


import java.io.IOException;
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
import com.jrsoft.fri.xtsz.entity.Area;
import com.jrsoft.fri.xtsz.entity.City;
import com.jrsoft.fri.xtsz.entity.Province;
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
	
	/**
	 * 根据查询树形菜单
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public ActionForward queryTree(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ) throws IOException{
		
		String hql="  where 1=1 ";
		List<Province> list=provinceService.queryAll(hql);
		
		//查询所有省
		JSONArray array=new JSONArray();
		for(int i=0;i<list.size();i++){
			JSONObject cell = new JSONObject();
			cell.put("text",list.get(i).getProvince());
			cell.put("icon","glyphicon glyphicon-stop");
			//查询所有市
			String hql1="  where 1=1 and father='"+list.get(i).getProvinceid()+"'";
			List<City> list1=cityService.queryAll(hql1);
			JSONArray array1=new JSONArray();
			for (int j = 0; j < list1.size(); j++) {
				JSONObject cell1 = new JSONObject();
				cell1.put("text",list1.get(j).getCity());
				//查询区县
				String hql2=" where  1=1  and father='"+list1.get(j).getCityid()+"' " ;
				List<Area> list2=areaService.queryAll(hql2);
				JSONArray array2=new JSONArray();
				for (int k = 0; k < list2.size(); k++) {
					JSONObject cell2 = new JSONObject();
					cell2.put("text",list2.get(k).getArea());
					array2.add(cell2);
				}
				cell1.put("nodes",array2);
				array1.add(cell1);
			}
			cell.put("nodes",array1);
			
			array.add(cell);
		}
		System.out.println(array.toString());
		request.setAttribute("array", array);
		return	new ActionForward("/jsp/xtsz/position/ctiy.jsp");
	}

}
