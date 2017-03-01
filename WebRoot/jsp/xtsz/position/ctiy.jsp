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

    <head>
    <title>树形菜单</title>
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/css/bootstrap-treeview.css" rel="stylesheet">
  </head>
  <body>
  	<div class="container">
      <div class="row">
        <div class="col-sm-12">
          <label for="treeview"></label>
          <div id="treeview"/>
        </div>
      </div>
    </div>
    <script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap-treeview.js"></script>
  	<script type="text/javascript">

      $(function() {

  			$('#treeview').treeview({
  				levels: 0,
          		data: ${array}
       		 });
  		});
  	</script>
  </body>

</html>
