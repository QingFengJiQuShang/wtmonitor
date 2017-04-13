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
    
    <title>电梯列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
			<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
	   		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
	   
	   <link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<style type="text/css">
.or {
	width: 400px;
	margin: 10px auto;
}

.or button {
	width: 80px;
	height: 35px;
	background-color: #00AAEE;
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
			<div class="warp">
				<div class="select">
				<div class="clearfix">
					<p class="fl">
						<label for="user">注册号&nbsp;:&nbsp;</label>
						<input type="hidden"  id="id" value="${id}">
						<input type="hidden"  id="id1" value="${id1}">
						<input type="hidden"  id="id2" value="${id2}">
						<input type="hidden"  id="id3" value="${id3}">
						<input type="hidden"  id="id4" value="${id4}">
						<input type="hidden"  id="id5" value="${id5}">
						<input type="hidden"  id="id6" value="${id6}">
						<input type="hidden"  id="id7" value="${id7}">
						<input type="text" id="registerid"  value="${registerid}"  placeholder="请输入" />
					</p>
					<p class="fl">
						<label for="man">使用单位&nbsp;:&nbsp;</label>
						<input type="text" id="useUnitName"  value="${useUnitName}" placeholder="请输入" />
					</p>
					<p class="fl">
						<label for="code">安装地址&nbsp;:&nbsp;</label>
						<input type="text" id="installPlace"  value="${installPlace}" />
					</p>
					<button class="fl"  onclick="query1();">查询</button>
				</div>
				
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th class="all">
									<i></i>
								</th>
							<th>序列</th>
							<th>电梯注册号</th>
							<th>识别码</th>
							<th>电梯使用单位</th>
							<th>电梯安装单位</th>
							<th>电梯品牌</th>
							<th>电梯层数</th>
							<th>安装地址</th>
							<th>操作</th>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""  data="${list.id }"  data-a="${list.registerid }"  data-b="${list.distinguishid}"  data-c="${list.useUnitId}" data-d="${list.useUnitName}" data-e="${list.installPlace}"  data-f="${list.maintenanceUnitName1}" data-g="${list.maintenanceUnitName}"   data-h="${list.maintenanceUsersName1}"  data-i="${list.maintenanceUsersName}" ></i>
									</td>
									<td>${s.index + 1 }</td>
									<td>${list.registerid }</td>
									<td>${list.distinguishid }</td>
									<td>${list.useUnitName }</td>
									<td>${list.installUnit }</td>
									<td>${list.brand}</td>
									<td>${list.numbers}</td>
									<td>${list.installPlace}</td>
									<td>
										<img src="<%=path%>/img/content.png" alt=""  onclick="findById('${list.id}','2');"/>
										<img src="<%=path%>/img/compile.png"  onclick="findById('${list.id}','1');"/>
										<img src="<%=path%>/img/del.png" alt="" class="del_one" onclick="del('${list.id}');"/>
									</td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
						<div class="choose">
							<p class="num">当前显示<span><c:if test="${page.pageNum==0}">${(page.pageNum+1)*1 }</c:if><c:if test="${page.pageNum!=0}">${(page.pageNum)*(page.pageSize) }</c:if></span>到<span>${(page.pageNum+1)* (page.pageSize)}</span>条，共<span>${page.count }</span>条记录</p>
							<div class="page">
								<a href="javascript:void(0);"  title="首页" onclick="fenye('0')" style="background-color: #00AAEE;color: #fff;"><<</a>								
								
								<c:if test="${page.pageNum==0}">
										<a href="javascript:void(0);"  title="上一页"   style="background-color: #333;color: #fff;"><</a>
								 </c:if>
							 	 <c:if test="${page.pageNum!=0}">
							 	 		<a href="javascript:void(0);"  title="上一页"  onclick="fenye1('${page.pageNum-1	}')"  style="background-color: #00AAEE;color: #fff;"><</a>
                         		</c:if>
								
								<c:if test="${page.pageNum+1==page.countSize}">
                        				<a href="javascript:void(0);" title="下一页"  style="background-color: #333;color: #fff;">></a>
		                        </c:if>
		                        <c:if test="${page.pageNum+1!=page.countSize}">
		                        		<a href="javascript:void(0);"  title="下一页"  onclick="fenye1('${page.pageNum+1}')"  style="background-color: #00AAEE;color: #fff;">></a>
		                    	</c:if>
								<a href="javascript:void(0);" class="mo" title="尾页"  onclick="fenye1('${page.countSize-1}')"  style="background-color: #00AAEE;color: #fff;">>></a>
							</div>
						</div>
						<p class="or clearfix" >
							<button class="fl"  onclick="onSure();">保存</button>
							<button class="fr" onclick="closeShow();">取消</button>
						</p>
					</div>
				</div>
			
			
		
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/dtjk/elevator.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function onSure(){
		
		var ids="";
		var registerids="";
		var distinguishids="";
		var useUnitIds="";
		var useUnitId1s="";
		var installPlaces="";
		var unitIds="";
		var unitId1s="";
		var userIds="";
		var userId1s="";
		$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				 ids = ids+$(this).find("i").attr("data")+",";
				 registerids =$(this).find("i").attr("data-a");
				 distinguishids =$(this).find("i").attr("data-b");
				 useUnitIds =$(this).find("i").attr("data-a");
				 useUnitId1s=$(this).find("i").attr("data-d");
				 installPlaces=$(this).find("i").attr("data-e");
				 unitIds=$(this).find("i").attr("data-f");
				 unitId1s=$(this).find("i").attr("data-g");
				 userIds=$(this).find("i").attr("data-h");
				 userId1s=$(this).find("i").attr("data-i");
			}
		})
		
	 var id=ids.split(",");
	//	alert(place[0]);
	 if(id.length>2){
	 	alert("只能选择一条数据！");
	 	return;
	 }
	 window.parent.document.getElementById('${id}').value=id[0];
	 window.parent.document.getElementById('${id1}').value=registerids;
	 window.parent.document.getElementById('${id2}').value=distinguishids;
	 window.parent.document.getElementById('${id3}').value=useUnitIds;
	 window.parent.document.getElementById('${id4}').value=useUnitId1s;
	 window.parent.document.getElementById('${id5}').value=installPlaces;
	 window.parent.document.getElementById('${id6}').value=unitIds;
	 window.parent.document.getElementById('${id7}').value=unitId1s;
	 window.parent.JqueryDialog.Close();
 }
	
	</script>

</html>
