<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		  function add(){
			  window.location="<%=path%>/jsp/dagl/region/addRegion.jsp";
			  
		  }
		
		</script>
	</head>

	<body>
		
		<div class="wrap clearfix">
			
			<div class="con fl">
				
				<div class="con_right">
					<div class="user_list clearfix">
						<div class="choose">
							<form>
								<div class="term clearfix">
									<p class="fl">
										<label for="area">行政区域&nbsp;:&nbsp;</label>
										<input  name="" id="area" placeholder="请输入">
									</p>
									<p class="fl place">
										<label for="place">地点&nbsp;:&nbsp;</label>
										<select name="" id="">
											<option value="">请选择</option>
										</select>
									</p>
									<button class="fl polling">查询</button>
								</div>
							</form>
						</div>
						<div class="clearfix tiao">
							<p class="add add_user fl"  onclick="add();">新增</p>
							<p class="delete_batch fl">批量删除</p>
							<p class="fr export"><img src="<%=path %>/img/export.png" />导出文档</p>
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
									<tr>
										<td class="select"><em class="fl"></em></td>
										<td>XXX</td>
										<td>XXX</td>
										<td>
											<span class="add_user"><img src="<%=path %>/img/edit.png" alt="" />编辑</span>
											<span class="delete"><img src="<%=path %>/img/delete.png"/>删除</span>
										</td>
									</tr>
									<tr>
										<td class="select"><em class="fl"></em></td>
										<td>XXX</td>
										<td>XXX</td>
										<td>
											<span class="add_user"><img src="<%=path %>/img/edit.png" alt="" />编辑</span>
											<span class="delete"><img src="<%=path %>/img/delete.png"/>删除</span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		</div>
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/file/file_idnex.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>

  </body>
</html>
