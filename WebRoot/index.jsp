<%@page import="org.jbpm.api.ProcessEngine"%>
<%@page import="smart.sys.platform.springUtils.SpringBeanUtil"%>
<%@page import="smart.sys.platform.dao.DBEntity"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object pe = SpringBeanUtil.getBean("processEngine");
out.println(pe);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>

  </head>
  
  <body>
    
  </body>
</html>
