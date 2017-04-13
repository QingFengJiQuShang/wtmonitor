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
    
    <title>物业单位</title>
    
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
		
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		<script src="<%=path%>/js/region.js" type="text/javascript" charset="utf-8"></script>
		
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
				<div class="select "  style="height: 110px;">
					<div class="clearfix">
						<p class="fl" >
							<label for="logn">省&nbsp;:&nbsp;</label>
							<input type="hidden"  id="id" value="${id}">
							<input type="hidden"  id="id1" value="${id1}">
							<input type="hidden"   id="provinceid"  >
							<select   id="province"   >
							<option value="${province}"  selected="selected">${province}</option>
							</select>
					</p>
						<p class="fl"  >
						<label for="logn">市&nbsp;:&nbsp;</label>
							<select   id="city" >
							<option value="${city}"  selected="selected">${city}</option>
							</select>
					</p>
					<p class="fl">
							<label for="logn">区&nbsp;:&nbsp;</label>
							<select   id="area" >
								<option value="${area}"  selected="selected">${area}</option>
							</select>
						</p>
						<p class="fl">
							<label for="user">单位名称&nbsp;:&nbsp;</label>
							<input type="text" id="name" placeholder="请输入"  value="${name}" />
						</p>
	
						</div>
					<div class="clearfix">
					<p class="fl">
						<label for="unit">单位地址&nbsp;:&nbsp;</label>
						<input type="text" id="address" placeholder="请输入"  value="${address}" />
					</p>
					<p class="fl">
						<label for="man">联系人&nbsp;:&nbsp;</label>
						<input type="text" id="liaisons" placeholder="请输入"  value="${liaisons}"/>
					</p>
					<p class="fl">
						<label for="phone">联系人电话&nbsp;:&nbsp;</label>
						<input type="text" id="phone"  name="phone"  value="${phone}"/>
					</p>
					<button class="fl"  onclick="query2();">查询</button>
					</div>
				</div>
				
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th class="all">
									<i></i>
								</th>
								<th>序列</th>
								<th>物业单位名称</th>
								<th>联系人</th>
								<th>联系人电话</th>
								<th>物业单位地址</th>
								<th>省</th>
								<th>市</th>
								<th>区</th>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""  data-e="${list.id }" data="${list.name }"></i>
										
									</td>
									<td>${s.index + 1 }</td>
									<td>${list.name }</td>
									<td>${list.liaisons }</td>
									<td>${list.phone }</td>
									<td>${list.address}</td>
									<td>${list.province}</td>
									<td>${list.city}</td>
									<td>${list.area}</td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
						<div class="choose">
							<p class="num">当前显示<span><c:if test="${page.pageNum==0}">${(page.pageNum+1)*1 }</c:if><c:if test="${page.pageNum!=0}">${(page.pageNum)*(page.pageSize) }</c:if></span>到<span>${(page.pageNum+1)* (page.pageSize)}</span>条，共<span>${page.count }</span>条记录</p>
							<div class="page">
								<a href="javascript:void(0);"  title="首页" onclick="fenye2('0')" style="background-color: #00AAEE;color: #fff;"><<</a>								
								
								<c:if test="${page.pageNum==0}">
										<a href="javascript:void(0);"  title="上一页"   style="background-color: #333;color: #fff;"><</a>
								 </c:if>
							 	 <c:if test="${page.pageNum!=0}">
							 	 		<a href="javascript:void(0);"  title="上一页"  onclick="fenye2('${page.pageNum-1	}')"  style="background-color: #00AAEE;color: #fff;"><</a>
                         		</c:if>
								
								<c:if test="${page.pageNum+1==page.countSize}">
                        				<a href="javascript:void(0);" title="下一页"  style="background-color: #333;color: #fff;">></a>
		                        </c:if>
		                        <c:if test="${page.pageNum+1!=page.countSize}">
		                        		<a href="javascript:void(0);"  title="下一页"  onclick="fenye2('${page.pageNum+1}')"  style="background-color: #00AAEE;color: #fff;">></a>
		                    	</c:if>
								<a href="javascript:void(0);" class="mo" title="尾页"  onclick="fenye2('${page.countSize-1}')"  style="background-color: #00AAEE;color: #fff;">>></a>
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
	<script src="<%=path%>/js/xtgl/propertyUnit.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	function onSure(){
		
		var ids="";
		var names="";
		$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				 ids = ids+$(this).find("i").attr("data-e")+",";
				 names =names+$(this).find("i").attr("data")+",";
				
			}
		})
		
	 var id=ids.split(",");
	 var name=names.split(",");
	if(id.length==1){
	 	alert("请选择数据！");
	 	return;
	 }
	 for(var i=0; i<id.length; i+=1){
		 if(name[i]!=""){
			  $('#${id}',window.parent.document).append("<option value='"+id[i]+"' selected='selected' >"+name[i]+"</option>");	 
			  $('#${id1}',window.parent.document).append("<option value='"+name[i]+"' selected='selected' >"+name[i]+"</option>");	 
			window.parent.document.getElementById('${id}').focus();
		 }			
  	}
	 window.parent.JqueryDialog.Close();
 }
	
	</script>

</html>
