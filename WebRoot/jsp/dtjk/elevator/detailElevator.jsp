<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>电梯数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list_add.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/dtjk_comm.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
	   <script src="<%=path%>/js/dtjk/elevator.js" type="text/javascript" charset="utf-8"></script>
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/lq.datetimepick.css" />
		
	
	</head>

	<body>
		<div class="con">
		<p class="user">电梯列表</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			
		<div class="wrap">
			<!--第一层-->
			<div class="record clearfix">
				<p class="name">电梯基本信息</p>
				<div class="fl fenzhi">
					<p>
						<label for="apply">注册号&nbsp;:&nbsp;</label>${list.registerid}
					</p>
					<p>
						<label for="style">类型&nbsp;:&nbsp;</label>${list.type}
					</p>
					<p >
						<label for="map">地图标注&nbsp;:&nbsp;</label>${list.label}
					</p>
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="cord">识别码&nbsp;:&nbsp;</label>${list.distinguishid}
					</p>
				<p>
						<label for="speed">电梯速度&nbsp;:&nbsp;</label>${list.speed}
					</p>
					
					
					
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="barnd">品牌&nbsp;:&nbsp;</label>${list.brand}
					</p>
					<p>
						<label for="num">总层数&nbsp;:&nbsp;</label>${list.numbers}
					</p>
					
				</div>
				<div class="fl fenzhi">
				<p>
						<label for="model">型号&nbsp;:&nbsp;</label>${list.model}
					</p>
					<p>
						<label for="">注册状态</label>${list.registerState}
					</p>
					
				</div>
			</div>
			<!--第二层-->
			<div class="record clearfix">
				<p class="name">电梯安装信息</p>
				<div class="fl fenzhi">
					<p>
						<label for="user">使用单位&nbsp;:&nbsp;</label>${list.useUnitId.name}
					</p>
					
					<p>
						<label for="mount">安装人员&nbsp;:&nbsp;</label>${list.installUser}
					</p>
					
					<p>
						<label for="datetimepicker4">使用年限&nbsp;:&nbsp;</label>${list.serviceIfe}
					</p>

				</div>
				<div class="fl fenzhi">
					<p onclick="tishi()">
						<label for="weibao_user">维保单位&nbsp;:&nbsp;</label>${list.maintenanceUnitId.name}
					</p>
					
					<p>
						<label for="time">安装时间&nbsp;:&nbsp;</label>
						<fmt:formatDate value="${list.installTime}"  pattern='yyyy-MM-dd'/>
					</p>
					
				</div>
				<div class="fl fenzhi">
					<p onclick="tishi()">
						<label for="men">维保人&nbsp;:&nbsp;</label>${list.maintenanceUsersId.name}
					</p>
					
					<p>
						<label for="place">安装地点&nbsp;:&nbsp;</label>${list.installPlace}
					</p>
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="mount_user">安装单位&nbsp;:&nbsp;</label>${list.installUnit}
					</p>
					
					<p>
						<label for="datetimepicker3">生产日期&nbsp;:&nbsp;</label>
						<fmt:formatDate value="${list.manufactureTime}"  pattern='yyyy-MM-dd'/>
					</p>
					
				</div>
			</div>
			<!--第三层-->
			<div class="maker clearfix">
				<p class="name">电梯制造商信息</p>
				<div class="fl fenzhi">
					<p>
						<label for="maker">制造商&nbsp;:&nbsp;</label>${list.manufacturer}
					</p>
					<p>
						<label for="maker_web">制造商网站&nbsp;:&nbsp;</label>${list.manufacturerUrl}
					</p>
					<p>
						<label for="make_phone">本地分公司电话&nbsp;:&nbsp;</label>${list.filialePhone}
					</p>
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="maker_address">制造商地址&nbsp;:&nbsp;</label>${list.manufacturerAddress}
					</p>
					<p>
						<label for="maker">本地分公司地址&nbsp;:&nbsp;</label>${list.filialeAddress}
					</p>
					
					
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="maker_sure">制作商电话&nbsp;:&nbsp;</label>${list.manufacturerPhone}
					</p>
					<p>
						<label for="make_men">本地分公司联系人&nbsp;:&nbsp;</label>${list.filialeContact}
					</p>
				</div>
			</div>
			<p class="or clearfix">
				<input type="button"  value="修改"   onclick="findById('${list.id}','1');" >
				<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
			</p>
		</div>

		</div>
	</body>
		<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/selectUi.js"></script></head>
	    <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
   
     </script>
</html>
