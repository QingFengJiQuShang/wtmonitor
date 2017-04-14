package com.jrsoft.fri.dtjk.action;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.xtgl.action.Authority;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.service.XtszDictionaryService;

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
		 judge( );
		//������������
		String hql=" where  1=1 and state='����' and delflag!='1'  "+Authority.Hql(user) ;

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
		String hql1=" where  1=1 and state='����'  and delflag!='1'  "+Authority.Hql(user) ;
				//"and userid.id='"+user.getId()+"' " ;
		List<DtjkElevator> elevators1=elevatorService.queryAll(hql1);
		String str1="[";
		 i=0;
		for(DtjkElevator elevator:elevators1){
			if(elevator.getLabel()!=null){
				String [] label=elevator.getLabel().split(",");
				str1+="["+label[0]+","+label[1]+"]";
				i++;
				if(i!=elevators1.size())
					str1+=",";
			}
		}
		str1+="]";
		request.setAttribute("str1", str1);
		//���ߵ�������
		String hql2=" where  1=1 and state='����'  and delflag!='1'  " +Authority.Hql(user) ;
				//"and userid.id='"+user.getId()+"' " ;
		List<DtjkElevator> elevators2=elevatorService.queryAll(hql2);
		String str2="[";
		 i=0;
		for(DtjkElevator elevator:elevators2){
			if(elevator.getLabel()!=null){
				String [] label=elevator.getLabel().split(",");
				str2+="["+label[0]+","+label[1]+"]";
				i++;
				if(i!=elevators2.size())
					str2+=",";
			}
		}
		str2+="]";
		
		//ά������
		String hql3=" where  1=1 and state='ά��'  and delflag!='1'  " +Authority.Hql(user) ;
		List<DtjkElevator> elevators3=elevatorService.queryAll(hql3);
		String str3="[";
		 i=0;
		for(DtjkElevator elevator:elevators3){
			if(elevator.getLabel()!=null){
				String [] label=elevator.getLabel().split(",");
				str3+="["+label[0]+","+label[1]+"]";
				i++;
				if(i!=elevators3.size())
					str3+=",";
			}
		}
		str3+="]";
		request.setAttribute("str2", str2);
		request.setAttribute("str3", str3);
		Index index=new Index();
		//������������
		index.setNormalNum(elevators.size());
		//���ߵ�������
		index.setOffLineNum(elevators2.size());
		//���ϵ�������
		index.setFaultNum(elevators1.size());
		//ά����������
		
		index.setMaintenanceNum1(elevators3.size());
		//ά�����ڵ�������
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -20);							//20��ǰ����
		
		String sql="select count(*)  from  ("+Authority.Sql(user) +")  de where  1=1  and delflag!='1'  and maintenance_Time   < to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss')";
		int n=DBEntity.getInstance().queryDataCount(sql);
		index.setMaintenanceNum(n);
		
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);  //��һ��
		 sql="select count(*)  from  ("+Authority.Sql(user) +")  de where  1=1  and delflag!='1'  and yearly_Time   < to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss')";
		 n=DBEntity.getInstance().queryDataCount(sql);
		index.setYearlyNum(n);
		request.setAttribute("index", index);
		
		//���¹����б�
		String num=request.getParameter("num");   //��ǰҳ
		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		page.setPageSize(5);
		List<GzclFault> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
		//��ѯ���񶩵�
		 sql="select de.*,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place ,xu.name as username,xuu.name as useUnitName  " +
				" from gzcl_fault de " +
				" left join ("+Authority.Sql(user) +") e on e.id=de.elevator_id "+  //������Ϣ
				" left join xtgl_users xu on xu.id=de.duty_id "+  //������Ϣ
				" left join xtgl_use_unit xuu on xuu.id=e.use_unit_id "+  //ʹ�õ�λ
				"where  1=1   and de.state='������' order by de.happen_Time desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<GzclFault>();
				while(rs.next()){
					GzclFault useUnit=new GzclFault();
					useUnit.setId(rs.getLong("id"));
					useUnit.setFault(rs.getString("fault"));
					useUnit.setHandle(rs.getString("handle"));
					useUnit.setHappenTime(rs.getString("happen_Time")==null?null:df.parse(rs.getString("happen_Time")));
					useUnit.setAlarmTime(rs.getString("alarm_Time")==null?null:df.parse(rs.getString("alarm_Time")));
					useUnit.setArriveTime(rs.getString("arrive_Time")==null?null:df.parse(rs.getString("arrive_Time")));
					useUnit.setSuccessTime(rs.getString("success_Time")==null?null:df.parse(rs.getString("success_Time")));
					useUnit.setRegisterid(rs.getString("registerid"));
					useUnit.setDistinguishid(rs.getString("distinguishid"));
					useUnit.setPlace(rs.getString("place"));
					useUnit.setDutyName(rs.getString("username"));
					useUnit.setNumbers(rs.getString("numbers"));
					useUnit.setState(rs.getString("state"));
					useUnit.setUseUnitName(rs.getString("useUnitName"));
					useUnit.setFaultType(rs.getString("fault_Type"));
					
					list.add(useUnit);
					
				}
				
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/home/main.jsp");
	}
	/**
	 * ��ҳ �����ͼ��ע ��ѯ������Ϣ
	 * @param request
	 * @param response
	 */
	public void findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String label=request.getParameter("label");
		String hql=" where  1=1 and delflag!='1'  and label='"+label+"'  " ;
		List<DtjkElevator> elevators=elevatorService.queryAll(hql);
		
		JSONObject cell = new JSONObject();
		if(elevators.size()>0){
			DtjkElevator elevator=elevators.get(0);
			cell.put("id", elevator.getId());		//����id
			cell.put("registerid", elevator.getRegisterid()==null?"":elevator.getRegisterid());		//����ע���
			cell.put("distinguishid", elevator.getDistinguishid()==null?"":elevator.getDistinguishid());		//ʶ����
			cell.put("useUnitName",elevator.getUseUnitId()==null?"":elevator.getUseUnitId().getName());		//ʹ�õ�λ����
			cell.put("maintenanceUnitName",elevator.getMaintenanceUnitId()==null?"":elevator.getMaintenanceUnitId().getName());		//ʹ�õ�λ����
			cell.put("brand", elevator.getBrand()==null?"":elevator.getBrand());			//����Ʒ��
			cell.put("model", elevator.getModel()==null?"":elevator.getModel());			//�����ͺ�
			cell.put("installPlace", elevator.getInstallPlace()==null?"":elevator.getInstallPlace());		//��װ�ص�
			
		}
		JsonUtil.ajaxOutPutJson(response, cell);
	}
	
	/**
	 * �жϵ����Ƿ�����
	 * @throws Exception 
	 */
	public void judge( ) throws Exception{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, -3);							//3����ǰ
		String sql="update DTJK_ELEVATOR set   state='����' where ( state='����' or  state is null ) and ( report_Time is null or report_Time<=to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss')  ) and delflag!='1'  ";
		DBEntity.getInstance().executeSql(sql);
		
	}
	

	/**
	   * ����ע��� ��ѯ ��γ��
	   * @param request
	   * @param respons
	   * @param productList
	   * @param productSeries
	   * @param productImageList
	 * @throws Exception 
	   */
		public void onlyRegisterid(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			String registerid=request.getParameter("registerid");   //�û���
				//������ϵ�˱��
				String hql=" where 1=1 and   registerid = '"+registerid+"' and delflag!='1'  order by id asc ";
				List<DtjkElevator> content=elevatorService.query(hql);	
				JSONObject cell = new JSONObject();
				if(content.size()>0){
					cell.put("label", content.get(0).getLabel());
				}
				JsonUtil.ajaxOutPutJson(response, cell);
				
	}
	
}
