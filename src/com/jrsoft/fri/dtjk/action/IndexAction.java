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
		 judge( );
		//正常电梯数量
		String hql=" where  1=1 and state='正常' and delflag!='1'  "+Authority.Hql(user) ;

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
		
		//故障电梯数量
		String hql1=" where  1=1 and state='故障'  and delflag!='1'  "+Authority.Hql(user) ;
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
		//离线电梯数量
		String hql2=" where  1=1 and state='离线'  and delflag!='1'  " +Authority.Hql(user) ;
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
		
		//维保电梯
		String hql3=" where  1=1 and state='维保'  and delflag!='1'  " +Authority.Hql(user) ;
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
		//正常电梯数量
		index.setNormalNum(elevators.size());
		//离线电梯数量
		index.setOffLineNum(elevators2.size());
		//故障电梯数量
		index.setFaultNum(elevators1.size());
		//维保电梯数量
		
		index.setMaintenanceNum1(elevators3.size());
		//维保过期电梯数量
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -20);							//20天前日期
		
		String sql="select count(*)  from  ("+Authority.Sql(user) +")  de where  1=1  and delflag!='1'  and maintenance_Time   < to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss')";
		int n=DBEntity.getInstance().queryDataCount(sql);
		index.setMaintenanceNum(n);
		
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);  //加一年
		 sql="select count(*)  from  ("+Authority.Sql(user) +")  de where  1=1  and delflag!='1'  and yearly_Time   < to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss')";
		 n=DBEntity.getInstance().queryDataCount(sql);
		index.setYearlyNum(n);
		request.setAttribute("index", index);
		
		//最新故障列表
		String num=request.getParameter("num");   //当前页
		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setPageSize(5);
		List<GzclFault> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
		//查询服务订单
		 sql="select de.*,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place ,xu.name as username,xuu.name as useUnitName  " +
				" from gzcl_fault de " +
				" left join ("+Authority.Sql(user) +") e on e.id=de.elevator_id "+  //电梯信息
				" left join xtgl_users xu on xu.id=de.duty_id "+  //电梯信息
				" left join xtgl_use_unit xuu on xuu.id=e.use_unit_id "+  //使用单位
				"where  1=1   and de.state='处理中' order by de.happen_Time desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
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
	 * 首页 点击地图标注 查询电梯信息
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
			cell.put("id", elevator.getId());		//电梯id
			cell.put("registerid", elevator.getRegisterid()==null?"":elevator.getRegisterid());		//电梯注册号
			cell.put("distinguishid", elevator.getDistinguishid()==null?"":elevator.getDistinguishid());		//识别码
			cell.put("useUnitName",elevator.getUseUnitId()==null?"":elevator.getUseUnitId().getName());		//使用单位名称
			cell.put("maintenanceUnitName",elevator.getMaintenanceUnitId()==null?"":elevator.getMaintenanceUnitId().getName());		//使用单位名称
			cell.put("brand", elevator.getBrand()==null?"":elevator.getBrand());			//电梯品牌
			cell.put("model", elevator.getModel()==null?"":elevator.getModel());			//电梯型号
			cell.put("installPlace", elevator.getInstallPlace()==null?"":elevator.getInstallPlace());		//安装地点
			
		}
		JsonUtil.ajaxOutPutJson(response, cell);
	}
	
	/**
	 * 判断电梯是否离线
	 * @throws Exception 
	 */
	public void judge( ) throws Exception{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, -3);							//3分钟前
		String sql="update DTJK_ELEVATOR set   state='离线' where ( state='正常' or  state is null ) and ( report_Time is null or report_Time<=to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss')  ) and delflag!='1'  ";
		DBEntity.getInstance().executeSql(sql);
		
	}
	

	/**
	   * 根据注册号 查询 经纬度
	   * @param request
	   * @param respons
	   * @param productList
	   * @param productSeries
	   * @param productImageList
	 * @throws Exception 
	   */
		public void onlyRegisterid(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			String registerid=request.getParameter("registerid");   //用户名
				//生成联系人编号
				String hql=" where 1=1 and   registerid = '"+registerid+"' and delflag!='1'  order by id asc ";
				List<DtjkElevator> content=elevatorService.query(hql);	
				JSONObject cell = new JSONObject();
				if(content.size()>0){
					cell.put("label", content.get(0).getLabel());
				}
				JsonUtil.ajaxOutPutJson(response, cell);
				
	}
	
}
