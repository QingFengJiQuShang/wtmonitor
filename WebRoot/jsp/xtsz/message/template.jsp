<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>短信模板</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
			<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
			<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

	</head>

	<body>
	<form id="form" action="<%=path %>/dictionaryAction.do?method=addTemplate" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">短信模板</label>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</label>
			<div class="table">
				<p class="add">短信模板</label>
			<div class="table_con">
					<p class="fill">
						<label for="name">模板类型&nbsp;:&nbsp;</label>
						<input type="hidden" id="id"  name="dictionary.id"  value="${list.id}"/>
						<input type="hidden" id="flag"  name="dictionary.flag"  value="${list.flag}"/>
						<select id="remarks"  name="dictionary.remarks">
							<c:forEach items="${strs}" var="strs" varStatus="s">
								<option value="${strs}"  onclick="push('${strs}');">${strs}</option>
						</c:forEach>
						</select>

					</label>

					<p class="fill">
						<label for="man">模板内容&nbsp;:&nbsp;</label>
						<textarea  id="dictionary"  name="dictionary.dictionary"   rows="3" cols="50">${list.dictionary}</textarea>
					</label>

					<p class="or clearfix">
            <%if(Authority.haveRigth(user.getId(),"xtsz_dxmb_update")) {%>
  						<input type="button"  value="保存"  onclick="add();">
            <%}%>
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</label>
				</div>
				<div style="margin：0,auto;text-align:center;">
            
						<label>姓名：{name}</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label>安装地址：{place}</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label>识别码：{code}</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label>故障类型：{type}</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label>sim卡号：{sim}</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label>日期：{date}</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label>天数：{days}</label>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
    	  var dictionary= document.getElementById("dictionary").value;
    //	   alert(dictionary );
    //	  alert(dictionary.indexOf("{place}") );
    	 if(dictionary.indexOf("{name}") > -1)
		 	$('#form').submit();
     }
     function push (remarks){
		//var remarks= document.getElementById("remarks").value;
			$.ajax({
				 mtype:'post',
				 url: '<%=path%>/dictionaryAction.do?method=template',
				 data:'remarks='+remarks,
				 success: function(rs){
				          var json=eval(rs.rows);
				          for(var i=0;i<json.length;i++){
				        	  document.getElementById('id').value=json[0].id;
				         	  document.getElementById('flag').value=json[0].flag;
				         	  document.getElementById('dictionary').value=json[0].dictionary;
				          }
				  },
				  error: function(result){
							alert("操作失败!");
					}
		 });
	}
     </script>
</html>
