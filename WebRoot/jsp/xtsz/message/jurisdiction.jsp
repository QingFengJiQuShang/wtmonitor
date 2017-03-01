<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglAuthority"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<XtglAuthority> authority=(List<XtglAuthority> )request.getAttribute("authority");
XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>短信权限</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />

		<style type="text/css">
			.warp {
				padding: 10px 0;
				border: 2px solid #ccc;
				-webkit-border-radius: 10px;
				-moz-border-radius: 10px;
				border-radius: 10px;
			}

			.level {
				padding: 10px 0;
				border-bottom: 1px solid #ccc;
			}



			.level>p {
				font-size: 16px;
				padding: 10px;
			}

			.level_tow {
				padding-left: 6%;
			}

			.level_tow p {
				height: 20px;
				width: 15%;
				text-indent: 10px;
				line-height: 20px;
			}

			.or {
				width: 45%;
				margin: 30px auto 0;
			}

			.or button {
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
		</style>
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">短信权限</p>
			<div class="warp">
				<form id="form" action="<%=path %>/usersAction.do?method=updateMessage" method="post"  encType="multipart/form-data">

				<div class="level">
					<p>使用单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="sy_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="sy_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="sy_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="sy_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="sy_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="sy_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="sy_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="sy_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="sy_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="sy_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="sy_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="sy_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="sy_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="sy_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="fwf"  value="sy_fwf"/>
							<label for="fwf">服务费到期</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="nj"  value="sy_nj"/>
							<label for="nj">年检到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="ll"  value="sy_ll"/>
							<label for="ll">流量到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="sy_wb"/>
							<label for="wb">维保超期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="sy_bx"/>
							<label for="wb">保险到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="sy_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>物业单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="wy_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="wy_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="wy_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="wy_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="wy_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="wy_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="wy_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="wy_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="wy_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="wy_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="wy_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="wy_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="wy_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="wy_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="fwf"  value="wy_fwf"/>
							<label for="fwf">服务费到期</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="nj"  value="wy_nj"/>
							<label for="nj">年检到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="ll"  value="wy_ll"/>
							<label for="ll">流量到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="wy_wb"/>
							<label for="wb">维保超期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="wy_bx"/>
							<label for="wb">保险到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="wy_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>维保单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="wb_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="wb_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="wb_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="wb_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="wb_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="wb_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="wb_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="wb_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="wb_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="wb_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="wb_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="wb_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="wb_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="wb_10"/>
							<label for="or_stop">非平层开门</label>
						</p>

						<p class="fl">
							<input type="checkbox" name="authority" id="fwf"  value="wb_fwf"/>
							<label for="fwf">服务费到期</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="nj"  value="wb_nj"/>
							<label for="nj">年检到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="ll"  value="wb_ll"/>
							<label for="ll">流量到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="wb_wb"/>
							<label for="wb">维保超期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="wb_bx"/>
							<label for="wb">保险到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="wb_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>维保人权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="wbr_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="wbr_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="wbr_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="wbr_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="wbr_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="wbr_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="wbr_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="wbr_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="wbr_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="wbr_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="wbr_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="wbr_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="wbr_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="wbr_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="fwf"  value="wbr_fwf"/>
							<label for="fwf">服务费到期</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="nj"  value="wbr_nj"/>
							<label for="nj">年检到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="ll"  value="wbr_ll"/>
							<label for="ll">流量到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="wbr_wb"/>
							<label for="wb">维保超期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="wbr_bx"/>
							<label for="wb">保险到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="wbr_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>制造单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="zz_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="zz_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="zz_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="zz_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="zz_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="zz_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="zz_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="zz_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="zz_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="zz_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="zz_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="zz_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="zz_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="zz_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="fwf"  value="zz_fwf"/>
							<label for="fwf">服务费到期</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="nj"  value="zz_nj"/>
							<label for="nj">年检到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="ll"  value="zz_ll"/>
							<label for="ll">流量到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="zz_wb"/>
							<label for="wb">维保超期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="zz_bx"/>
							<label for="wb">保险到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="zz_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>救援单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="jy_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="jy_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="jy_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="jy_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="jy_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="jy_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="jy_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="jy_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="jy_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="jy_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="jy_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="jy_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="jy_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="jy_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="fwf"  value="jy_fwf"/>
							<label for="fwf">服务费到期</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="nj"  value="jy_nj"/>
							<label for="nj">年检到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="ll"  value="jy_ll"/>
							<label for="ll">流量到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="jy_wb"/>
							<label for="wb">维保超期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="jy_bx"/>
							<label for="wb">保险到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="jy_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>区域用户权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="xt_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="xt_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="xt_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="xt_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="xt_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="xt_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="xt_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="xt_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="xt_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="xt_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="xt_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="xt_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="xt_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="xt_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="fwf"  value="xt_fwf"/>
							<label for="fwf">服务费到期</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="nj"  value="xt_nj"/>
							<label for="nj">年检到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="ll"  value="xt_ll"/>
							<label for="ll">流量到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="xt_wb"/>
							<label for="wb">维保超期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="wb"  value="xt_bx"/>
							<label for="wb">保险到期</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="xt_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<p class="or clearfix">
          <%if(Authority.haveRigth(user.getId(),"xtsz_dxqx_update")) {%>
            <input type="button"  value="保存"  onclick="add();">
          <%}%>
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</form>
	</div>
	</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	 <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
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
