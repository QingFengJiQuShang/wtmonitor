package com.jrsoft.fri.dtjk.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.common.utils.JsonUtil;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.from.Index;
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
	 * ��ѯ ��ҳ
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		
		//������������
		String hql=" where  1=1 and state='����' and userid.id='"+user.getId()+"' " ;
		List<DtjkElevator> elevators=elevatorService.queryAll(hql);
		String str="[";
		int i=0;
		for(DtjkElevator elevator:elevators){
			if(elevator.getLabel()!=null){
				String [] label=elevator.getLabel().split(",");
				str+="["+label[0]+","+label[1]+"]";
				i++;
				if(i!=elevators.size())
					str+=",";
			}
		}
		str+="]";
		request.setAttribute("str", str);
		
		//���ϵ�������
		String hql1=" where  1=1 and state='����' and userid.id='"+user.getId()+"' " ;
		List<DtjkElevator> elevators1=elevatorService.queryAll(hql1);
		String str1="[";
		 i=0;
		for(DtjkElevator elevator:elevators1){
			if(elevator.getLabel()!=null){
				String [] label=elevator.getLabel().split(",");
				str1+="["+label[0]+","+label[1]+"]";
				i++;
				if(i!=elevators.size())
					str1+=",";
			}
		}
		str1+="]";
		request.setAttribute("str1", str1);
		//���ߵ�������
		String hql2=" where  1=1 and state='����' and userid.id='"+user.getId()+"' " ;
		List<DtjkElevator> elevators2=elevatorService.queryAll(hql2);
		String str2="[";
		 i=0;
		for(DtjkElevator elevator:elevators2){
			if(elevator.getLabel()!=null){
				String [] label=elevator.getLabel().split(",");
				str2+="["+label[0]+","+label[1]+"]";
				i++;
				if(i!=elevators.size())
					str2+=",";
			}
		}
		str2+="]";
		request.setAttribute("str2", str2);
		Index index=new Index();
		//������������
		index.setNormalNum(elevators.size());
		//���ߵ�������
		index.setOffLineNum(elevators1.size());
		//���ϵ�������
		index.setFaultNum(elevators2.size());
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -20);							//20��ǰ����
		
		String sql="select count(*)  from Dtjk_Elevator de where  1=1  and maintenance_Time   < to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss')";
		int n=DBEntity.getInstance().queryDataCount(sql);
		index.setMaintenanceNum(n);
		
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);  //��һ��
		 sql="select count(*)  from Dtjk_Elevator de where  1=1  and yearly_Time   < to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss')";
		 n=DBEntity.getInstance().queryDataCount(sql);
		index.setYearlyNum(n);
		request.setAttribute("index", index);
		 return	new ActionForward("/jsp/home/main.jsp");
	}
	/**
	 * ��ҳ �����ͼ��ע ��ѯ������Ϣ
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
			cell.put("id", elevator.getId());		//����id
			cell.put("registerid", elevator.getRegisterid()==null?"":elevator.getRegisterid());		//����ע���
			cell.put("distinguishid", elevator.getDistinguishid()==null?"":elevator.getDistinguishid());		//ʶ����
			cell.put("useUnitName",elevator.getUseUnitId()==null?"":elevator.getUseUnitId().getName());		//ʹ�õ�λ����
			cell.put("brand", elevator.getBrand()==null?"":elevator.getBrand());			//����Ʒ��
			cell.put("model", elevator.getModel()==null?"":elevator.getModel());			//�����ͺ�
			cell.put("installPlace", elevator.getInstallPlace()==null?"":elevator.getInstallPlace());		//��װ�ص�
			
		}
		JsonUtil.ajaxOutPutJson(response, cell);
	}
	
	
	
}
