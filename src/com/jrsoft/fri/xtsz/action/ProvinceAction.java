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
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.xtsz.entity.Province;
import com.jrsoft.fri.xtsz.service.ProvinceService;

public class ProvinceAction  extends DispatchAction {
	private ProvinceService provinceService;

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
	/**
	 * 省 列表
	 * @param request
	 * @param response
	 */
	public void query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String hql=" where  1=1   " ;
		List<Province> list=provinceService.queryAll(hql);
		JSONArray array=new JSONArray();
		
		for(int i=0;i<list.size();i++){
			JSONObject cell = new JSONObject();
			Province province=list.get(i);
			cell.put("provinceid", province.getProvinceid());		//
			cell.put("province",province.getProvince());		//电梯注册号
			array.add(cell);
		}
		JsonUtil.ajaxOutPutJson(response, array);
	}

}
