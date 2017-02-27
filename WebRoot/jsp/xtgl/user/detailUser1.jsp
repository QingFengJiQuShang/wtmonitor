<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%@page import="com.sun.java_cup.internal.runtime.virtual_parse_stack"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglAuthority"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<XtglAuthority> authority=(List<XtglAuthority> )request.getAttribute("authority");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user1.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		<script src="<%=path%>/js/region.js" type="text/javascript" charset="utf-8"></script>
	<style type="text/css">
			.warp {
				padding: 10px 0;
				-webkit-border-radius: 10px;
				-moz-border-radius: 10px;
				border-radius: 10px;
			}
			
			.warp .level_b {
				padding: 10px 0;
			}
			
			.warp .level_b:nth-child(6) {
				border-bottom: 0;
			}
			
			.warp .level_b>p {
				font-size: 16px;
				padding: 10px;
			}
			
			.warp .level_tow {
				padding-left:5%;
			}
			
			.warp .level_tow p {
				height:30px;
				text-indent: 10px;
				line-height: 20px;
			}
			
			.warp .or {
				width: 45%;
				margin: 30px auto 0;
			}
			
			.warp .or button {
				width: 76px;
				height: 32px;
				background-color: #00aaee;
				color: #fff;
				font-size: 14px;
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				border-radius: 5px;
				cursor: pointer;
					}
			 .select p .label_b {
				width:100px;
				color: #535353;
				font-size: 12px;
				text-align: right;
				font-weight: 700;
				}
			ul li ul li ul li{display:inline;}
			/*ul li ul li ul li{display:none;}*/
		</style>

  </head>
  
  <body>
    <div class="con">
			<p class="user">系统用户</p>
			<p class="back" onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">查询用户详情</p>
				<div class="table_con" >
					<p class="fill">
						<label for="name" style="display:inline-block ;">用户类型&nbsp;:&nbsp;</label>
						<c:if test="${list.type==1}">超级管理员</c:if>
						<c:if test="${list.type==2}">区域用户</c:if>
						<c:if test="${list.type==3}">终端用户</c:if>
						
					</p>
					<p class="fill">
						<label for="logn" style="display:inline-block ;">姓名&nbsp;:&nbsp;</label>
						${list.name}
					</p>
					<p class="fill">
						<label for="logn" style="display:inline-block ;">登录名&nbsp;:&nbsp;</label>
						${list.loginname}
					</p>
					<p class="fill"  id="phone">
						<label for="password"style="display:inline-block ;">手机号&nbsp;:&nbsp;</label>
						${list.phone}
					</p>
					<p class="fill">
						<label for="logn" style="display:inline-block ;">省&nbsp;:&nbsp;</label>${list.province}
						
					</p>
					<p class="fill" >
						<label for="logn" style="display:inline-block ;">市&nbsp;:&nbsp;</label>${list.city}
						
					</p>
					<p class="fill">
						<label for="logn" style="display:inline-block ;">区&nbsp;:&nbsp;</label>
						${list.area}
					</p>
					<c:if test="${list.type==2||list.type==3}">
						<p class="fill"  id="quyu" >
						<label for="quyu" style="display:inline-block ;" >区域单位&nbsp;:&nbsp;</label>
						${list.unit}
						</p>
					</c:if>
					<c:if test="${list.type==3}">
					<p class="fill"  id="unitType" >
						<label for="man" style="display:inline-block ;">单位类型&nbsp;:&nbsp;</label>
						<c:if test="${list.unitType==1}">使用单位</c:if>
						<c:if test="${list.unitType==2}">物业单位</c:if>
						<c:if test="${list.unitType==3}">维保单位</c:if>
						<c:if test="${list.unitType==4}">制造单位</c:if>
						<c:if test="${list.unitType==5}">救援单位</c:if>
						
					</p>
					<p class="fill"  id="unitName"  >
						<label for="man" style="display:inline-block ;">单位名称&nbsp;:&nbsp;</label>
						${list.unitName}
						</p>
					</c:if>
			
					<div class="warp" id="warp">
							<ul>
								<li>
									<div class="level_b">
												<p>电梯监控权限分配</p>
												<ul>
													<div class="level_tow clearfix">
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">电梯列表</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_dtlb" value="dtjk_dtlb_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_dtlb" value="dtjk_dtlb_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_dtlb" value="dtjk_dtlb_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_dtlb" value="dtjk_dtlb_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_dtlb" value="dtjk_dtlb_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_dtlb" value="dtjk_dtlb_upload" />
																			<label for="user_xg">上传</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dtjk_dtlb_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>	
															<p class="fl">
																<label for="user_xg">电梯监控</label> <br>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_dtjk" value="dtjk_dtjk_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																</ul>
															</p>
														</li>
														<li>	
															<p class="fl">
																<label for="user_xg">记录回放</label> <br>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_jlhf" value="dtjk_jlhf_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_jlhf" value="dtjk_jlhf_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dtjk_jlhf_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">电梯管理</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_dtgg" value="dtjk_dtgg_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">上报周期</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_sbzq" value="dtjk_sbzq_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_sbzq" value="dtjk_sbzq_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dtjk_sbzq_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">&nbsp;&nbsp;&nbsp;白名单</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_bmd" value="dtjk_bmd_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_bmd" value="dtjk_bmd_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_bmd" value="dtjk_bmd_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_bmd" value="dtjk_bmd_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dtjk_bmd_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">维保记录</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_wbjl" value="dtjk_wbjl_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_wbjl" value="dtjk_wbjl_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_wbjl" value="dtjk_wbjl_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_wbjl" value="dtjk_wbjl_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dtjk_wbjl_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">年检记录</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_njjl" value="dtjk_njjl_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_njjl" value="dtjk_njjl_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_njjl" value="dtjk_njjl_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_njjl" value="dtjk_njjl_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dtjk_njjl_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">&nbsp;&nbsp;&nbsp;服务费</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_fwf" value="dtjk_fwf_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_fwf" value="dtjk_fwf_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_fwf" value="dtjk_fwf_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dtjk_fwf" value="dtjk_fwf_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dtjk_fwf_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
													</div>
												</ul>
								</li>
								<li>
									<div class="level_b">
												<p>故障管理权限分配</p>
												<ul>
													<div class="level_tow clearfix">
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">人工接警</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_rgjj" value="gzgl_rgjj_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_rgjj" value="gzgl_rgjj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_rgjj" value="gzgl_rgjj_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_rgjj" value="gzgl_rgjj_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="gzgl_rgjj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">当前故障</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_dqgz" value="gzgl_dqgz_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_dqgz" value="gzgl_dqgz_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_dqgz" value="gzgl_dqgz_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_dqgz" value="gzgl_dqgz_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="gzgl_dqgz_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">历史故障</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="gzgl_lsgz" value="gzgl_lsgz_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																		
																		<p class="fl">
																			<input type="checkbox"  id="gzgl_lsgz_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
													</div>
												</ul>
								</li>
								<li>
									<div class="level_b">
												<p>单位管理权限分配</p>
												<ul>
													<div class="level_tow clearfix">
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">使用单位</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_sydw" value="dwgl_sydw_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_sydw" value="dwgl_sydw_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_sydw" value="dwgl_sydw_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_sydw" value="dwgl_sydw_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_sydw" value="dwgl_sydw_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_sydw" value="dwgl_sydw_upload" />
																			<label for="user_xg">上传</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dwgl_sydw_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">维保单位</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wbdw" value="dwgl_wbdw_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wbdw" value="dwgl_wbdw_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wbdw" value="dwgl_wbdw_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wbdw" value="dwgl_wbdw_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wbdw" value="dwgl_wbdw_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wbdw" value="dwgl_wbdw_upload" />
																			<label for="user_xg">上传</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dwgl_wbdw_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">救援单位</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_jydw" value="dwgl_jydw_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_jydw" value="dwgl_jydw_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_jydw" value="dwgl_jydw_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_jydw" value="dwgl_jydw_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_jydw" value="dwgl_jydw_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_jydw" value="dwgl_jydw_upload" />
																			<label for="user_xg">上传</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dwgl_jydw_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">物业单位</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wydw" value="dwgl_wydw_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wydw" value="dwgl_wydw_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wydw" value="dwgl_wydw_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wydw" value="dwgl_wydw_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wydw" value="dwgl_wydw_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_wydw" value="dwgl_wydw_upload" />
																			<label for="user_xg">上传</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dwgl_wydw_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">制造单位</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_zzdw" value="dwgl_zzdw_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_zzdw" value="dwgl_zzdw_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_zzdw" value="dwgl_zzdw_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_zzdw" value="dwgl_zzdw_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_zzdw" value="dwgl_zzdw_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_zzdw" value="dwgl_zzdw_upload" />
																			<label for="user_xg">上传</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dwgl_zzdw_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">保险单位</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_bxdw" value="dwgl_bxdw_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_bxdw" value="dwgl_bxdw_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_bxdw" value="dwgl_bxdw_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_bxdw" value="dwgl_bxdw_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_bxdw" value="dwgl_bxdw_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_bxdw" value="dwgl_bxdw_upload" />
																			<label for="user_xg">上传</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dwgl_bxdw_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">区域单位</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_qydw" value="dwgl_qydw_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_qydw" value="dwgl_qydw_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_qydw" value="dwgl_qydw_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_qydw" value="dwgl_qydw_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_qydw" value="dwgl_qydw_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="dwgl_qydw" value="dwgl_qydw_upload" />
																			<label for="user_xg">上传</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="dwgl_qydw_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														
													</div>
												</ul>
								</li>
								<li>
									<div class="level_b">
												<p>保险管理权限分配</p>
												<ul>
													<div class="level_tow clearfix">
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">未保电梯</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_wbdt" value="bxgl_wbdt_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="bxgl_wbdt_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">在保电梯</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_zbdt" value="bxgl_zbdt_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="bxgl_zbdt_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">脱保电梯</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_tbdt" value="bxgl_tbdt_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="bxgl_tbdt_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">保单记录</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_bdjl" value="bxgl_bdjl_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_bdjl" value="bxgl_bdjl_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_bdjl" value="bxgl_bdjl_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_bdjl" value="bxgl_bdjl_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="bxgl_bdjl_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">理赔记录</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_lpjl" value="bxgl_lpjl_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_lpjl" value="bxgl_lpjl_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_lpjl" value="bxgl_lpjl_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_lpjl" value="bxgl_lpjl_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="bxgl_lpjl_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">保险统计</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_bxtj" value="bxgl_bxtj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_bxtj" value="bxgl_bxtj_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="bxgl_bxtj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">保险公司统计</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_bxgs" value="bxgl_bxgs_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="bxgl_bxgs" value="bxgl_bxgs_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="bxgl_bxgs_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
													</div>
												</ul>
								</li>
								<li>
									<div class="level_b">
												<p>系统设置权限分配</p>
												<ul>
													<div class="level_tow clearfix">
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">系统用户</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_xtyh" value="xtsz_xtyh_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_xtyh" value="xtsz_xtyh_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_xtyh" value="xtsz_xtyh_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_xtyh" value="xtsz_xtyh_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_xtyh_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">操作日志</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_czrz" value="xtsz_czrz_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_czrz_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">通信日志</label>
																<ul>
																	
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_txrz" value="xtsz_txrz_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_txrz_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">系统帮助</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_xtbz" value="xtsz_xtbz_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_xtbz" value="xtsz_xtbz_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_xtbz" value="xtsz_xtbz_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_xtbz" value="xtsz_xtbz_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_xtbz_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">短信模板</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxmb" value="xtsz_dxmb_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxmb" value="xtsz_dxmb_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_dxmb_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">短信提醒</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxtx" value="xtsz_dxtx_add" />
																			<label for="user_xg">新增</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxtx" value="xtsz_dxtx_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxtx" value="xtsz_dxtx_del" />
																			<label for="user_xg">删除</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxtx" value="xtsz_dxtx_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_dxtx_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">短信权限</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxqx" value="xtsz_dxqx_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxqx" value="xtsz_dxqx_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_dxqx_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">短信日志</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dxrz" value="xtsz_dxrz_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_dxrz_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">刷新时间</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_sxsj" value="xtsz_sxsj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_sxsj" value="xtsz_sxsj_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_sxsj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">单包流量</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dbll" value="xtsz_dbll_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_dbll" value="xtsz_dbll_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_dbll_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">报警控制</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_bjkz" value="xtsz_bjkz_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="xtsz_bjkz" value="xtsz_bjkz_update" />
																			<label for="user_xg">修改</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="xtsz_bjkz_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														
													</div>
												</ul>
										</li>
										<li>
									<div class="level_b">
												<p>统计分析权限分配</p>
												<ul>
													<div class="level_tow clearfix">
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">故障区域统计</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_gzqytj" value="tjfx_gzqytj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_gzqytj" value="tjfx_gzqytj_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="tjfx_gzqytj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">故障类型统计</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_gzlxtj" value="tjfx_gzlxtj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_gzlxtj" value="tjfx_gzlxtj_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="tjfx_gzlxtj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">救援区域统计</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_jyqytj" value="tjfx_jyqytj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_jyqytj" value="tjfx_jyqytj_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="tjfx_jyqytj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">救援响应统计</label>
																<ul>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_jyxytj" value="tjfx_jyxytj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_jyxytj" value="tjfx_jyxytj_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="tjfx_jyxytj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">维保区域统计</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_wbqytj" value="tjfx_wbqytj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_wbqytj" value="tjfx_wbqytj_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="tjfx_wbqytj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">维保出勤统计</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_wbcqtj" value="tjfx_wbcqtj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_wbcqtj" value="tjfx_wbcqtj_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox"  id="tjfx_wbcqtj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
														<li>
															<p class="fl">
																<label for="user_ll" class="two_e">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																短信统计</label>
																<ul>
																	
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_dxtj" value="tjfx_dxtj_check" />
																			<label for="user_xg">查询</label> <br>
																		</p>
																	</li>
																	<li>
																		<p class="fl">
																			<input type="checkbox" name="authority" id="tjfx_dxtj" value="tjfx_dxtj_exp" />
																			<label for="user_xg">下载</label> <br>
																		</p>
																	</li>
																	<li>	
																		<p class="fl">
																			<input type="checkbox" onclick="all()" id="tjfx_dxtj_all" />
																			<label for="controlAll">全选</label>
																		</p>
																	</li>
																</ul>
															</p>
														</li>
													</div>
												</ul>
										</li>
								</ul>
						</div>
						 
					</div>
			
					<div class="keep clearfix ">
						<p class="or clearfix">
						<button class="fl"  onclick="findById('${list.id}','1');">修改</button>
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				
					</div>
				

			</div>
		</div>
  </body>
  <script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/ssq.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/add_user1.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/user.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
	var obj=document.getElementsByName("authority");
	    <%for(int i=0;i<authority.size();i++){%>
			    	 for(var j=0;j<obj.length;j++){
					    	if(obj[j].value=='<%=authority.get(i).getKey()%>'){
								 obj[j].checked = true;
							}
					}
	  <%  } %>
     </script>
</html>
