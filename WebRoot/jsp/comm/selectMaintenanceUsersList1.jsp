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
    
    <title>使用单位</title>
    
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
				<div class="select clearfix">
					<p class="fl">
						<label for="user">维保人姓名&nbsp;:&nbsp;</label>
						<input type="hidden"  id="unitId"  value="${unitId}"/>
						<input type="text" id="name" placeholder="请输入"   value="${name}"/>
					</p>
					<p class="fl">
						<label for="phone">维保人电话&nbsp;:&nbsp;</label>
						<input type="text" id="phone"  name="phone"  value="${phone}"/>
					</p>
					<p class="fl">
						<label for="unit">维保证编号&nbsp;:&nbsp;</label>
						<input type="text" id="numbers" placeholder="请输入"  value="${numbers}"/>
					</p>
					<p class="fl">
						<label for="">维保卡号&nbsp;:&nbsp;</label>
						<input type="text" id="cardNumber" placeholder="请输入" value="${cardNumber}"/>
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
								<th>维保人名称</th>
								<th>维保人电话</th>
								<th>维保证编号</th>
								<th>维保证有效期限</th>
								<th>维保卡号</th>
							
								
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""  data-e="${list.id }" data="${list.name }"  data-b="${list.phone }"  data-c="${list.cardNumber }"></i>
										
									</td>
									<td>${s.index + 1 }</td>
									<td>${list.name }</td>
									<td>${list.phone }</td>
									<td>${list.numbers }</td>
									<td>${list.validity }</td>
									<td>${list.cardNumber}</td>
									
									
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
						<div class="choose">
							<p class="num">当前显示<span><c:if test="${page.pageNum==0}">${(page.pageNum+1)*1 }</c:if><c:if test="${page.pageNum!=0}">${(page.pageNum)*5 }</c:if></span>到<span>${(page.pageNum+1)*5 }</span>条，共<span>${page.count }</span>条记录</p>
							<div class="page">
								<a href="javascript:void(0);"  title="首页" onclick="fenye1('0')" style="background-color: #00AAEE;color: #fff;"><<</a>								
								
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
					</div>
				</div>
			</div>
			<p class="or clearfix" >
				<button class="fl"  onclick="onSure();">保存</button>
				<button class="fr" onclick="closeShow();">取消</button>
			</p>
		</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/maintenanceUnit.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	function onSure(){
		
		var ids="";
		var names="";
		var cardNumbers="";
		var phones="";
		$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				 ids = ids+$(this).find("i").attr("data-e")+",";
				 names =names+$(this).find("i").attr("data")+",";
				 phones =phones+$(this).find("i").attr("data-b")+",";
				 cardNumbers =cardNumbers+$(this).find("i").attr("data-c")+",";
			}
		})
		
	 var id=ids.split(",");
	 var name=names.split(",");
	 var phone=phones.split(",");
	 var cardNumber=cardNumbers.split(",");
	//	alert(id.length+","+id);
	 if(id.length>2){
	 	alert("只能选择一条数据！");
	 	return;
	 }
	 window.parent.document.getElementById('${id}').value=id[0];
	 window.parent.document.getElementById('${id1}').value=name[0];
	 window.parent.document.getElementById('${id2}').value=phone[0];
	 window.parent.document.getElementById('${id3}').value=cardNumber[0];
	// window.parent.document.getElementById('installUnit').focus();
	 
	 window.parent.JqueryDialog.Close();
 }
	
	</script>

</html>
