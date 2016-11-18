package com.jrsoft.fri.dtjk.action;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.common.utils.JsonUtil;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

public class IndexAction  extends DispatchAction{
	private DtjkElevatorService elevatorService;

	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	
	/**
	 * 查询 首页
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		
		//正常电梯数量
		String hql=" where  1=1 and state='正常' and userid.id='"+user.getId()+"' " ;
		List<DtjkElevator> elevators=elevatorService.queryAll(hql);
		String str="[";
		int i=0;
		for(DtjkElevator elevator:elevators){
			String [] label=elevator.getLabel().split(",");
			str+="["+label[0]+","+label[1]+"]";
			i++;
			if(i!=elevators.size())
				str+=",";
			
		}
		str+="]";
		request.setAttribute("str", str);
		
		//故障电梯数量
		String hql1=" where  1=1 and state='故障' and userid.id='"+user.getId()+"' " ;
		List<DtjkElevator> elevators1=elevatorService.queryAll(hql1);
		String str1="[";
		 i=0;
		for(DtjkElevator elevator:elevators1){
			String [] label=elevator.getLabel().split(",");
			str1+="["+label[0]+","+label[1]+"]";
			i++;
			if(i!=elevators.size())
				str1+=",";
			
		}
		str1+="]";
		request.setAttribute("str1", str1);
		//离线电梯数量
		String hql2=" where  1=1 and state='离线' and userid.id='"+user.getId()+"' " ;
		List<DtjkElevator> elevators2=elevatorService.queryAll(hql2);
		String str2="[";
		 i=0;
		for(DtjkElevator elevator:elevators2){
			String [] label=elevator.getLabel().split(",");
			str2+="["+label[0]+","+label[1]+"]";
			i++;
			if(i!=elevators.size())
				str2+=",";
			
		}
		str2+="]";
		request.setAttribute("str2", str2);
		 return	new ActionForward("/jsp/home/main.jsp");
	}
	/**
	 * 首页 点击地图标注 查询电梯信息
	 * @param request
	 * @param response
	 */
	public void findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String label=request.getParameter("label");
		String hql=" where  1=1 and label='"+label+"'  " ;
		List<DtjkElevator> elevators=elevatorService.queryAll(hql);
		
		JSONObject cell = new JSONObject();
		if(elevators.size()>0){
			DtjkElevator elevator=elevators.get(0);
			cell.put("id", elevator.getId());		//电梯id
			cell.put("registerid", elevator.getRegisterid()==null?"":elevator.getRegisterid());		//电梯注册号
			cell.put("distinguishid", elevator.getDistinguishid()==null?"":elevator.getDistinguishid());		//识别码
			cell.put("useUnitName",elevator.getUseUnitId()==null?"":elevator.getUseUnitId().getName());		//使用单位名称
			cell.put("brand", elevator.getBrand()==null?"":elevator.getBrand());			//电梯品牌
			cell.put("model", elevator.getModel()==null?"":elevator.getModel());			//电梯型号
			cell.put("installPlace", elevator.getInstallPlace()==null?"":elevator.getInstallPlace());		//安装地点
			
		}
		JsonUtil.ajaxOutPutJson(response, cell);
	}
	
	
	
}
