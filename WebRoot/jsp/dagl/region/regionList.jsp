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
    
    <title>区域信息列表</title>
    
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/file/file_index.css" />
		<script type="text/javascript">
		    	
		</script>
	</head>

	<body>
		
		<div class="wrap clearfix">
			
			<div class="con fl">
				
				<div class="con_right">
					<div class="user_list clearfix">
						<div class="choose">
								<div class="term clearfix">
									<p class="fl">
										<label for="area">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行政区域&nbsp;:&nbsp;</label>
										<input name="region.region"    value="${reg}"  id="region" placeholder="请输入">
									</p>
									<p class="fl">
										<label for="area">&nbsp;&nbsp;&nbsp;&nbsp;客户名称&nbsp;:&nbsp;</label>
										<input name="region.clientId"    value="${client}"  id="client" placeholder="请输入">
									</p>
									<button class="fl polling"  onclick="query();">查询</button>
								</div>
							
						</div>
						<div class="clearfix tiao">
							<p class="add add_user fl"  onclick="add();">新增</p>
							<p class="delete_batch fl">批量删除</p>
							<p class="fr export"  onclick="exp();"><img src="<%=path %>/img/export.png" />导出文档</p>
						</div>
						<div class="table">
							<table>
								<thead>
									<tr>
										<th class="all" width="50px">
											<em class="fl"></em>全选</th>
										<th>行政区域</th>
										<th>客户名称</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>

                  		  <c:forEach items="${list}" var="list">
									<tr>
										<td class="select">								
											<em class="fl"><input type="hidden" value="${list.id}" /></em>
										</td>
										<td >${list.region}</td>
										<td>${list.clientId}</td>
										<td>
											<span class="add_user" onclick="findById('${list.id}');"><img src="<%=path %>/img/edit.png" alt="" />编辑</span>
											<span class="delete"  onclick="del('${list.id}');"><img src="<%=path %>/img/delete.png"/ >删除</span>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<p class="page clearfix">
								<c:if test="${page.pageNum==0}">
										<span class="fl" style="background-color:  #e0e0e0;">上一页</span>
								 </c:if>
							 	 <c:if test="${page.pageNum!=0}">
							 	 		<span class="fl"  onclick="fenye('${page.pageNum-1	}')">上一页</span>
                         		</c:if>
								
								<c:if test="${page.pageNum+1==page.countSize}">
                        				<span class="fr"  style="background-color: #e0e0e0;">下一页</span>
		                        </c:if>
		                        <c:if test="${page.pageNum+1!=page.countSize}">
		                        		<span class="fr"  onclick="fenye('${page.pageNum+1}')">下一页</span>
		                    	</c:if>
							</p>
							
							
						</div>
						
					</div>
					
				</div>
			</div>
		</div>
		</div>
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/file/file_idnex.js" type="text/javascript" charset="utf-8"></script>
	
  </body>
</html>
