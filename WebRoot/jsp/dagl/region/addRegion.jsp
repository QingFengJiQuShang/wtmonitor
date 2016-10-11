<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
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
	</head>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
    }
     }
</script>
	<body>
		
		<div class="wrap clearfix">
			<div class="con fl">			
				<div class="con_right">	
					<div class="add_info">
						<form id="form" action="<%=path %>/regionAction.do?method=addEntity" method="post"  encType="multipart/form-data">
							<p>
								<label for="area2">行政区域&nbsp;:&nbsp;</label>
								<input type="text" id="region"  name="region"/>
							</p>
							<p>
								<label for="name">客户名称&nbsp;:&nbsp;</label>
								<input type="text" id=""  name="" />
							</p>
							<p class="clearfix">
								<label for="">&nbsp;</label>
								<input type="submit" class="sure" value="确定"  onclick="add();"></input>
								<input type="reset" class="reset" value="重置">
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
		</div>
	</body>
  </body>
</html>
