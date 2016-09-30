package com.jrsoft.fri.common.core.dao;

import com.jrsoft.fri.common.utils.DateUtils;
import com.jrsoft.fri.common.utils.Page;
import com.jrsoft.fri.common.utils.ReflectUtil;
import com.jrsoft.fri.common.utils.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.type.Type;

import com.jrsoft.fri.common.core.bean.JqGridSearchDetailTo;
import com.jrsoft.fri.common.core.bean.JqGridSearchTo;

public class SearchOperationUtil {

	/** 
	 * 根据表名称的别名以及JqGridSearchTo的查询条件组合HQL语句 
	 * @param alias  查询对象对应的别名 
	 * @param to 
	 * @return
	 */
	public static String getCombOperation(Class<? extends Object> clazz, Page page, List<Object> params, List<String> types) {
		String className = clazz.getSimpleName();
		String alias = className.toLowerCase();
		JqGridSearchTo to = page.getTo();
		//types  S:字符串 D:日期  L:Long N:Integer DB:Double FT:Float
		StringBuffer table = new StringBuffer(" ").append(className).append(" ").append(alias);
		StringBuffer where = new StringBuffer(" where ");
		//left join 
		if(page.getJointable()!=null&&!"".equals(page.getJointable())){
			StringBuffer hql=new StringBuffer("  left outer join "+alias+".").append(page.getJointable()).append(" "+page.getJointable().toLowerCase());
			if(page.getJoinHQL()!=null&&!"".equals(page.getJoinHQL())){
				if("projectRelDept".equals(page.getJointable())||"assignList".equals(page.getJointable())){
					where.append(" "+page.getJoinHQL()+" and ");
				}else{
					hql.append(" with "+page.getJoinHQL());
				}
			}
			table.append(hql);
		}
		if(page.getSec() == 0){ // 仅自己部门可见
			
				where.append(" 1=1 ");
		} else if(page.getSec() == 1){ // 公共
			where.append(" 1=1 ");
		} 
//		else if(page.getSec() == 2){ // 授权字段
//			if("1".equals(page.getRank())){
//				where.append("(").append(alias).append(".rootDept.autoId='").append(page.getRootDept()
//						 .getAutoId()).append("' or shareflag='1' or shareflag='2')");
//			} else if("2".equals(page.getRank())){
//				where.append("(").append(alias).append(".rootDept.autoId='").append(page.getRootDept()
//						 .getAutoId()).append("' or shareflag='2')");
//			}
//			
//		}
//		if(page.getDeptIds() != null && page.getDeptIds().size() > 0){
//			where.append(" and ").append(alias).append(".dept.autoId in(:args").append(params.size()).append(")");
//			params.add(page.getDeptIds());
//			types.add("_SIN");
//		}
		if(StringUtils.isNotEmpty(page.getPartHql())){
			where.append(" and(").append(page.getPartHql()).append(")");
		}
		if (page.getSearch() && StringUtils.isNotEmpty(to.getGroupOp())
				&& to.getRules() != null && to.getRules().size() != 0) {
			//多字段的组合查询
			String groupOp = to.getGroupOp();
			List<JqGridSearchDetailTo> rule = to.getRules();
			where.append(" and(");
			for (int i = 0; i < rule.size(); i++) {
				JqGridSearchDetailTo dto = rule.get(i);
				where.append(SearchOperationUtil.getFieldOper(alias, dto, params, types, table, clazz));
				if (i < rule.size() - 1) {
					where.append(" ").append(groupOp).append(" ");
				}
			}
			where.append(")");
		}
		String sidx = page.getSidx();
		int index = sidx.lastIndexOf("_");
		if(index > 0){
			sidx = sidx.substring(0, index);
		}
		where.append(" order by ").append(alias).append(".").append(sidx).append(" ").append(page.getSord());
		
		return table.append(where).toString();
	}
	/** 
	 * 根据表名称的别名以及JqGridSearchTo的查询条件组合HQL语句 
	 * @param alias  查询对象对应的别名 
	 * @param to 
	 * @return 
	 */
	private static String getFieldOper(String alias, JqGridSearchDetailTo dto, List<Object> params, List<String> types, StringBuffer table, Class<? extends Object> clazz) {
		String field = dto.getField();
		int index = field.lastIndexOf("_");
		StringBuffer sb = null;
		// 'eq'等于,'ne'不等于,'lt'小于,'le'小于等于,'gt'大于,'ge'大于等于,'bw'开始于,'bn'不开始于,'in'属于,'ni'不属于,'ew'结束于,'en'不结束于,'cn'包含,'nc'不包含
		//types  _S:字符串 _D:日期  _L:Long _N:Integer _DB:Double _FT:Float
		if (dto.getOp().equals("cn")) {
			sb = new StringBuffer("").append(alias).append(".").append(field.substring(0, index)).append("");
			sb.append(" like:args").append(params.size());
			params.add("%" + dto.getData() + "%");
			types.add("_S");
		} else if (dto.getOp().equals("bw")) {
			sb = new StringBuffer("lower(").append(alias).append(".").append(field.substring(0, index)).append(")");
			sb.append(" like:args").append(params.size());
			params.add(dto.getData() + "%");
			types.add("_S");
		} else if (dto.getOp().equals("ew")) {
			sb = new StringBuffer("lower(").append(alias).append(".").append(field.substring(0, index)).append(")");
			sb.append(" like:args").append(params.size());
			params.add("%" + dto.getData());
			types.add("_S");
		} else if (dto.getOp().equals("eq")) {
			String type = field.substring(index);
			if("_S".equals(type)){
				if(dto.getData().indexOf("ts_")!=-1&&"0".equals(dto.getData().substring(3,4))){
					sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
					sb.append(" =:args").append(params.size());
					types.add(type);
					params.add(dto.getData().substring(3,4));
					sb.append(" or "+alias+".addflag =:args").append(params.size());
					types.add(type);
					params.add(dto.getData().substring(3,4));
				}else if(dto.getData().indexOf("ts_")!=-1&&"2".equals(dto.getData().substring(3,4))){
					sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
					sb.append(" =:args").append(params.size());
					types.add(type);
					params.add(dto.getData().substring(3,4));
					sb.append(" or "+ alias).append(".").append(field.substring(0, index));
					sb.append(" is null");
				}else{
					sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
					sb.append(" =:args").append(params.size());
					types.add(type);
					params.add(dto.getData());
				}
			} else if("_L".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" =:args").append(params.size());
				types.add(type);
				params.add(Long.parseLong(dto.getData()));
			} else if("_D".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >=:args").append(params.size());
				Date d = StringUtils.strToDate(dto.getData());
				types.add(type);
				params.add(d);
				sb.append(" and ").append(field.substring(0, index)).append("<:args").append(params.size());
				types.add(type);
				params.add(DateUtils.addDay(d, 1));
			} else if("_C".equals(type)){
				String f = field.substring(0, index);
				String fClass = ReflectUtil.getFieldClassName(clazz, f);
				String fAlias = fClass.toLowerCase();
				table.append(" left join ").append(fClass).append(" ").append(fAlias).append(" with ").append(alias).append(".")
					.append(f).append(".autoId=").append(fAlias).append(".autoId");
				sb = new StringBuffer(fAlias).append(".autoId");
				sb.append(" =:args").append(params.size());
				types.add(type);
				params.add(dto.getData());
			} else {
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" =:args").append(params.size());
				types.add(type);
				params.add(dto.getData());
			}
			
		} else if (dto.getOp().equals("lt")) {
			String type = field.substring(index);
			if("_L".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" <:args").append(params.size());
				types.add(type);
				params.add(Long.parseLong(dto.getData()));
			} else if("_DB".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" <:args").append(params.size());
				types.add(type);
				params.add(Double.parseDouble(dto.getData()));
			} else if("_D".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" <:args").append(params.size());
				Date d = StringUtils.strToDate(dto.getData());
				types.add(type);
				params.add(d);
			} else {
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" <:args").append(params.size());
				types.add(type);
				params.add(dto.getData());
			}
			
		} else if (dto.getOp().equals("le")) {
			String type = field.substring(index);
			if("_L".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" <=:args").append(params.size());
				types.add(type);
				params.add(Long.parseLong(dto.getData()));
			} else if("_DB".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" <=:args").append(params.size());
				types.add(type);
				params.add(Double.parseDouble(dto.getData()));
			} else if("_D".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" <=:args").append(params.size());
				Date d = StringUtils.strToDate(dto.getData());
				types.add(type);
				params.add(DateUtils.addDay(d, 1));
			} else {
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" <=:args").append(params.size());
				types.add(type);
				params.add(dto.getData());
			}
			
		} else if (dto.getOp().equals("gt")) {
			String type = field.substring(index);
			if("_L".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >:args").append(params.size());
				types.add(type);
				params.add(Long.parseLong(dto.getData()));
			} else if("_DB".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >:args").append(params.size());
				types.add(type);
				params.add(Double.parseDouble(dto.getData()));
			} else if("_D".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >=:args").append(params.size());
				Date d = StringUtils.strToDate(dto.getData());
				types.add(type);
				params.add(DateUtils.addDay(d, 1));
			} else {
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >:args").append(params.size());
				types.add(type);
				params.add(dto.getData());
			}
			
		} else if (dto.getOp().equals("ge")) {
			String type = field.substring(index);
			if("_L".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >=:args").append(params.size());
				types.add(type);
				params.add(Long.parseLong(dto.getData()));
			} else if("_DB".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >=:args").append(params.size());
				types.add(type);
				params.add(Double.parseDouble(dto.getData()));
			} else if("_D".equals(type)){
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >=:args").append(params.size());
				Date d = StringUtils.strToDate(dto.getData());
				types.add(type);
				params.add(d);
			} else {
				sb = new StringBuffer(alias).append(".").append(field.substring(0, index));
				sb.append(" >:args").append(params.size());
				types.add(type);
				params.add(dto.getData());
			}
			
		} else {
			sb = new StringBuffer(" " + field.substring(0, index));
			sb.append(" like ").append(":args").append(params.size());
			params.add(dto.getData());
			types.add("_S");
		}
		return sb.toString();
	}

	public static void setQueryParams(Query query, List<Object> params, List<String> types){
		if(params != null ){
			for(int i = 0; i < params.size(); i++){
				if("_SIN".equals(types.get(i))){
					query.setParameterList("args"+i, (List<String>)params.get(i));
				} else if("_S".equals(types.get(i))){
					query.setString("args"+i, (String)params.get(i));
				}  else if("_L".equals(types.get(i))){
					query.setLong("args"+i, (Long)params.get(i));
				}  else if("_DB".equals(types.get(i))){
					query.setDouble("args"+i, (Double)params.get(i));
				}  else if("_D".equals(types.get(i))){
					query.setDate("args"+i, (Date)params.get(i));
				} else if("_C".equals(types.get(i))){
					query.setString("args"+i, (String)params.get(i));
				} else {
					query.setString("args"+i, (String)params.get(i));
				}
			}
		}
	}
}
