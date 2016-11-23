package com.jrsoft.fri.dtjk.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkYearlyInspectionService;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.from.Page;


public class DtjkYearlyInspectioAction extends DispatchAction {
	
	private DtjkYearlyInspectionService inspectionService;
	private DtjkElevatorService elevatorService;
	public DtjkYearlyInspectionService getInspectionService() {
		return inspectionService;
	}

	public void setInspectionService(DtjkYearlyInspectionService inspectionService) {
		this.inspectionService = inspectionService;
	}
	
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	/**
	 * ��ת ����¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findByAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String elevatorId=request.getParameter("elevatorId");
		DtjkElevator list=elevatorService.get(Long.parseLong(elevatorId));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/dtjk/yearly/addYearly.jsp");
	}
	/**
	 * ���� ����¼��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkYearlyInspection elevator =DtjkFrom.getInspection();
		elevator.setTime(df.parse(time));
		inspectionService.save(elevator);
		//�޸ĵ��ݵ����״̬�����ʱ��
		DtjkElevator entity =elevatorService.get(elevator.getElevatorId().getId());
		entity.setYearlyTime(new Date());
		entity.setYearlyState(elevator.getResult());
		elevatorService.update(entity);
	    return	new ActionForward("/inspectionAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	/**
	 * ��ѯ ����¼�б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String elevatorId=request.getParameter("elevatorId");
		String time=request.getParameter("time");
		String useUnitId=request.getParameter("useUnitId");
		String useUnitId1=request.getParameter("useUnitId1");
		String inspectionUnit=request.getParameter("inspectionUnit");
		
		String num=request.getParameter("num");   //��ǰҳ
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

		if(useUnitId1!=null){
			useUnitId1=new String(useUnitId1.getBytes("iso-8859-1"),"utf-8");
		 }
		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(elevatorId!=null&&!elevatorId.equals("")){
			hql+=" and elevatorId  ='"+elevatorId+"'";
		}
		if(time!=null&&!time.equals("")){
			hql+=" and time  =to_date('" + time+ "','yyyy-MM-dd')";
		}
		if(useUnitId!=null&&!useUnitId.equals("")){
			hql+=" and useUnitId  ='"+useUnitId+"'";
		}
		if(inspectionUnit!=null&&!inspectionUnit.equals("")){
			hql+=" and inspectionUnit  ='"+inspectionUnit+"'";
		}
		
		hql+="order by time desc ";
		List<DtjkYearlyInspection> DtjkYearlyInspections=inspectionService.queryAll(hql);
		
		page.setPageSize(3);	//ÿҳ��ʾ��
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		page.setCount(DtjkYearlyInspections.size());//�ܼ�¼��
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
		
		List<DtjkYearlyInspection> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*,e.install_place as place, e.registerid as registerid,e.distinguishid as distinguishid, xuu.name as userunitname  " +
						" from dtjk_yearly_inspection de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
						" where  1=1 " ;
				
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_Id = '"+elevatorId+"'";
				}
				if(time!=null&&!time.equals("")){
					sql+=" and de.time = to_date('" + time+ "','yyyy-MM-dd')";
				}
				if(useUnitId!=null&&!useUnitId.equals("")){
					sql+=" and de.use_unit_id  ='"+useUnitId+"'";
				}
				if(inspectionUnit!=null&&!inspectionUnit.equals("")){
					sql+=" and de.inspection_unit  ='"+inspectionUnit+"'";
				}
				sql+=" order by time desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkYearlyInspection>();
				while(rs.next()){
					DtjkYearlyInspection useUnit=new DtjkYearlyInspection();
					useUnit.setId(rs.getLong("id"));
					useUnit.setRegisterid(rs.getString("registerid"));
					useUnit.setDistinguishid(rs.getString("distinguishid"));
					useUnit.setPlace(rs.getString("place"));
					useUnit.setUseUnitName(rs.getString("userunitname"));
					useUnit.setInspectionUnit(rs.getString("inspection_unit"));
					useUnit.setTime(df.parse(rs.getString("time")));
					useUnit.setResult(rs.getString("result"));
					list.add(useUnit);
					
				}
				
				
				request.setAttribute("useUnitId", useUnitId);
				request.setAttribute("useUnitId1", useUnitId1);
				request.setAttribute("inspectionUnit", inspectionUnit);
				request.setAttribute("elevatorId", elevatorId);
				if(time!=null){
					request.setAttribute("time", df.parse(time));
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/yearly/yearlyList.jsp");
		}
	
	/**
	 * �༭ �鿴 ����¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		DtjkYearlyInspection list=inspectionService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/dtjk/yearly/updateYearly.jsp");
		}else{
			return	new ActionForward("/jsp/dtjk/yearly/dateilYearly.jsp");
		}
	}
	
	/**
	 * �޸�����¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkYearlyInspection unit =DtjkFrom.getInspection();
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		unit.setTime(df.parse(time));
			
		inspectionService.update(unit);
		return	new ActionForward("/inspectionAction.do?method=query&elevatorId="+unit.getElevatorId().getId());
	}
	
	
	
	
	/**
	 * ɾ������¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		String elevatorId=request.getParameter("elevatorId");
		inspectionService.delete(id);
		 return	new ActionForward("/inspectionAction.do?method=query&elevatorId="+elevatorId);
	}
	/**
	 * ���� ɾ�� ����¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		String elevatorId=request.getParameter("elevatorId");

		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				inspectionService.delete(Long.parseLong(arr[i]));
			}
		}
		 return	new ActionForward("/inspectionAction.do?method=query&elevatorId="+elevatorId);
		
	}
	
	/**
	 * ���� ����¼��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String elevatorId=request.getParameter("elevatorId");
		String time=request.getParameter("time");
		String useUnitId=request.getParameter("useUnitId");
		String inspectionUnit=request.getParameter("inspectionUnit");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ	
		DtjkYearlyInspection inspection=new DtjkYearlyInspection();
		 XtglUseUnit useUnit=new XtglUseUnit();			//ʹ�õ�λid
		 DtjkElevator elevator=new DtjkElevator();//ά������Id
		 if(useUnitId!=null){
			 useUnit.setId(Long.parseLong(useUnitId));
		 }
		 if(elevatorId!=null){
			 elevator.setId(Long.parseLong(elevatorId));
		 }
		 if(time!=null){
			 inspection.setTime(df.parse(time));
		 }
		 inspection.setUseUnitId(useUnit);
		 inspection.setElevatorId(elevator);
		 inspection.setInspectionUnit(inspectionUnit);
	
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "����¼��Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			inspectionService.export(filePath, inspection);

			// ����excel
			BufferedOutputStream bos = null;
			StringBuffer sb = new StringBuffer(50);
			sb.append("attachment;   filename=");
			sb.append(fileName);
			// System.out.println("----------sb="+sb);

			if (null != filePath && fileName != null) {
				response.setContentType("application/x-msdownload;charset=GBK");
				response.setHeader("Content-Disposition", new String(sb
						.toString().getBytes(), "ISO8859-1"));
				FileInputStream fis = new FileInputStream(filePath);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buffer = new byte[2048];
				while (fis.read(buffer) != -1) {
					bos.write(buffer);
				}
				bos.write(buffer, 0, buffer.length);
				fis.close();
				bos.close();
			}
			File fs = new File(filePath);
			if (fs.isFile() && fs.exists()) {
				fs.delete();
				System.out.println("ɾ�������ļ�" + fileName + "�ɹ���");
			} else {
				System.out.println("ɾ�������ļ�" + fileName + "ʧ�ܣ�");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
