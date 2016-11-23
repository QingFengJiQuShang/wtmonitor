package com.jrsoft.fri.dtjk.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.common.utils.JsonUtil;
import com.jrsoft.fri.common.utils.Page;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.dtjk.entity.DtjkPush;
import com.jrsoft.fri.dtjk.service.DtjkPushService;

public class DtjkPushAction  extends DispatchAction{
	
	private DtjkPushService pushService;

	public DtjkPushService getPushService() {
		return pushService;
	}

	public void setPushService(DtjkPushService pushService) {
		this.pushService = pushService;
	}
	
	/**
	 * ���ݱ���
	 * @param request
	 * @param response
	 */
	public void push(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		System.out.println("��������");
		String hql=" where  1=1 and flag='0'  " ;
		List<DtjkPush> elevators=pushService.queryAll(hql);
		JSONArray rows = new JSONArray();
		
		if(elevators.size()>0){
			JSONObject cell = new JSONObject();
			DtjkPush elevator=elevators.get(0);
			cell.put("id", elevator.getId());		//����id
			cell.put("registerid", elevator.getRegisterid()==null?"":elevator.getRegisterid());		//����ע���
			cell.put("distinguishid", elevator.getDistinguishid()==null?"":elevator.getDistinguishid());		//ʶ����
			cell.put("installPlace", elevator.getInstallPlace()==null?"":elevator.getInstallPlace());		//��װ�ص�
			cell.put("faultType", elevator.getFaultType()==null?"":elevator.getFaultType());		//��������
			//�޸�����״̬
			elevator.setFlag("1");
			pushService.update(elevator);
			rows.add(cell);
		}
		JsonUtil.ajaxOutPutJson(response, rows);
	}
	
	/**
	 * �رձ���
	 * @param request
	 * @param response
	 */
	public void updatePush(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String pushId=request.getParameter("pushId");
		String hql=" where  1=1 and id='"+pushId+"'  " ;
		List<DtjkPush> elevators=pushService.queryAll(hql);
		
		if(elevators.size()>0){
			DtjkPush push=elevators.get(0);
			push.setFlag("2");
			pushService.update(push);
		}
	}

}
