package com.jrsoft.fri.xtgl.action;

import smart.sys.platform.springUtils.SpringBeanUtil;

import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.service.XtglAuthorityService;

public class Authority {
	private static XtglAuthorityService authorityService = (XtglAuthorityService)SpringBeanUtil.getBean("authorityService");

	public static XtglAuthorityService getAuthorityService() {
		return authorityService;
	}

	public static void setAuthorityService(XtglAuthorityService authorityService) {
		Authority.authorityService = authorityService;
	}
	
	/**
	 * �鿴�û��Ƿ�ӵ��key��ԴԪ��
	 * @param userId �û�ID
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public static boolean haveRigth(Long userId,String key){
		
		return authorityService.getByUser(userId, key);
	}
	/**
	 * �鿴�û��Ƿ�ӵ��key��ԴԪ��  ����Ȩ�� 
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public static boolean haveMessage(String key){
		
		return authorityService.getByMessage( key);
	}
	/**
	 * �鿴 �Ƿ�ӵ��key��ԴԪ��   ��������Ȩ��
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public static boolean getByPush(String key){
		return authorityService.getByPush( key);
	}
	/**
	 * �����û�Ȩ�� ��ѯ����
	 * @param user
	 * @return
	 */
	public static String Sql(XtglUsers user ) {		
		String sql="select t.*  from dtjk_elevator t  where  1=1  and t.delflag!='1' ";
		if(user.getType().equals("2")){
			if(user.getProvince()!=null&&!user.getProvince().equals("")){
				sql+=" and t.province  ='" + user.getProvince()+ "'";
			}
			if(user.getCity()!=null&&!user.getCity().equals("")){
				sql+=" and t.city ='" + user.getCity()+ "'";
			}
			if(user.getArea()!=null&&!user.getArea().equals("")){
				sql+=" and t.area  ='" + user.getArea()+ "'";
			}
		}else if(user.getType().equals("3")){
			if(user.getUnitType()!=null&&user.getUnitType().equals("1")){
				sql+="  and t.use_Unit_Id='"+user.getUnitId()+"'";
			}else if(user.getUnitType()!=null&&user.getUnitType().equals("2")){
				sql+="  and t.property_Unit_Id='"+user.getUnitId()+"'";
			}else if(user.getUnitType()!=null&&user.getUnitType().equals("3")){
				sql+="  and t.maintenance_Unit_Id='"+user.getUnitId()+"'";
			}else if(user.getUnitType()!=null&&user.getUnitType().equals("4")){
				sql+="  and t.make_Unit_Id='"+user.getUnitId()+"'";
			}else{
				sql+="  and t.use_Unit_Id ='null' ";
			}
			
		}
		return sql;
		
	}
	/**
	 * �����û�Ȩ�� ��ѯ����
	 * @param user
	 * @return
	 */
	public static String Hql(XtglUsers user ) {		
		String sql=" ";
		if(user.getType().equals("2")){
			if(user.getProvince()!=null&&!user.getProvince().equals("")){
				sql+=" and province  ='" + user.getProvince()+ "'";
			}
			if(user.getCity()!=null&&!user.getCity().equals("")){
				sql+=" and 	city ='" + user.getCity()+ "'";
			}
			if(user.getArea()!=null&&!user.getArea().equals("")){
				sql+=" and area  ='" + user.getArea()+ "'";
			}
		}else if(user.getType().equals("3")){
			if(user.getUnitType()!=null&&user.getUnitType().equals("1")){
				sql+="  and use_Unit_Id='"+user.getUnitId()+"'";
			}else if(user.getUnitType()!=null&&user.getUnitType().equals("2")){
				sql+="  and property_Unit_Id='"+user.getUnitId()+"'";
			}else if(user.getUnitType()!=null&&user.getUnitType().equals("3")){
				sql+="  and maintenance_Unit_Id='"+user.getUnitId()+"'";
			}else if(user.getUnitType()!=null&&user.getUnitType().equals("4")){
				sql+="  and make_Unit_Id='"+user.getUnitId()+"'";
			}else{
				sql+="  and use_Unit_Id ='null' ";
			}
			
		}
		return sql;
		
	}
}
