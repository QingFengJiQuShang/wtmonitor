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
    
    <title>电梯网关信息列表</title>
    
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/file/file_wang.css" />
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
										<label for="area">电梯网关类型&nbsp;:&nbsp;</label>
										<select  id="type">
											<option value="">请选择</option>
											<option value="并行网关">并行网关</option>
											<option value="事件网关">事件网关</option>
											<option value="包含网关">包含网关</option>
										</select>
									</p>
									<p class="fl ">
										<label for="place">取流方式&nbsp;:&nbsp;</label>
										<select  id="net">
											<option value="">请选择</option>
											<option value="并联">并联</option>
											<option value="串联">串联</option>
											
										</select>
									</p>
									<p class="fl">
										<label for="place">入网方式&nbsp;:&nbsp;</label>
										<select   id="flow">
											<option value="">请选择</option>
											<option value="有线">有线</option>
											<option value="无线">无线</option>
										</select>
									</p>
									<p class="fl">
										<label for="letter">通信路线&nbsp;:&nbsp;</label>
										<input type="text" id="communication" value="${communication}" placeholder="请输入"/>
									</p>
								</div>
								<div class="term clearfix">
									
									<p class="fl ">
										<label for="place">终端机型&nbsp;:&nbsp;</label>
										<input type="text" id="terminal"  value="${terminal}"/>
									</p>
									<p class="fl">
										<label for="place">硬件版本&nbsp;:&nbsp;</label>
										<input type="text" id="hardware"   value="${hardware}"/>
									</p>
									<p class="fl">
										<label for="area">软件版本&nbsp;:&nbsp;</label>
										<input type="text" id="software"   value="${software}"  />
									</p>
									
								<button class="fl polling"  onclick="query();">查询</button>
							</div>
					</div>
						<div class="clearfix tiao">
							<p class="add add_user fl"   onclick="add();">新增</p>
							<p class="delete_batch fl">批量删除</p>
							<p class="fr export"   onclick="exp();"><img src="<%=path %>/img/export.png" />导出文档</p>
						</div>
						<div class="table">
							<table>
								<thead>
									<tr>
										<th class="all" width="50px">
											<em class="fl"></em>全选</th>
										<th>电梯网关类型</th>
										<th>取流方式</th>
										<th>入网方式</th>
										<th>通信路线</th>
										<th>终端机型</th>
										<th>硬件版本</th>
										<th>软件版本 </th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>

                  		  <c:forEach items="${list}" var="list">
									<tr>
										<td class="select">								
											<em class="fl"><input type="hidden" value="${list.id}" /></em>
										</td>
										<td >${list.type}</td>
										<td>${list.flow}</td>
										<td>${list.net}</td>
										<td>${list.communication}</td>
										<td>${list.terminal}</td>
										<td>${list.hardware}</td>
										<td>${list.software}</td>
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
	<script src="<%=path %>/js/file/file_wang.js" type="text/javascript" charset="utf-8"></script>
	
  </body>
</html>
