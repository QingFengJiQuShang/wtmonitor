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
    
    <title>电梯监控</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/dtjk/list_details.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>	
		<script type="text/javascript" src="<%=path %>/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script></head>
	
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
		 function myrefresh(){
			window.location.reload();
		}
		setTimeout("detail('${list.id}');",${dictionarie.dictionary*1000}); //指定30秒刷新一次s
</script>

	<body>
		<div class="con">
			<div class="wrap">
				<p class="order">电梯监控</p>
				<p class="back"  onclick="history.go(-1); "><img src="<%=path %>/img/back.png" />返回</p>
		<!--	<iframe class="img " src="<%=path%>/recordAction.do?method=findByMonitor&id=${list.id}&flag=1" id="main"  name="main"  frameborder="0" scrolling="no" marginheight="0" marginwidth="0"  width="100%"  height="500"      style="margin-top: -2px; overflow-x:hidden;  ">
			
				</iframe>-->
					<ul class="list_more clearfix"  id="monitor"  >
						
				</ul>	
							
		</div>
		</div>

	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/dtjk/monitor.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/dtjk/detail.js" type="text/javascript" charset="utf-8"></script>

	<script type="text/javascript">
	function detail (id){
		
					$.ajax({
						     mtype:'post',
				             url: "recordAction.do?method=findByMonitor1",
				             data: {"id":id},
				             success: function(data){
										var str="";  
				                       str +="<li class='flone'>";
				                       str +="<div class='whole'>";
				                       str +="<li class='img fl'>";
				                       if(data.state!='维保' && data.state!='故障'){
				                    	    str+="<span class='normal_green'>正常</span>";
				                       }
				                        if(data.state=='故障'){
				                    	    str +="<span class='normal'>故障</span>";
				                       }
				                         if(data.state=='维保'){
				                    	    str +="<span class='normal_black'>维保</span>";
				                       }
				                       if(data.state!='离线'){
				                    	    str +="<span class='on_line_green'>在线</span>";
				                       }
				                       if(data.state=='离线'){
				                    	    str +="<span class='on_line'>离线</span>";
				                       }
				                        str +="<span class='loding'>"+data.records.floor+ "</span>";
				                        str +="<span class='or dianti_down'></span>";
				                         if(data.records.direction=='上行'){
				                    	    str +="<img class='up' src='<%=path %>/img/arrow_down_gray.png'><img class='down' src='<%=path %>/img/arrow_up_red.png'>";
				                       }
				                         if(data.records.direction=='下行'){
				                    	    str +="<img class='up' src='<%=path %>/img/arrow_down_red.png'><img class='down' src='<%=path %>/img/arrow_up.png'>";
				                       }
				                       if(data.records.direction!='上行'&&data.records.direction!='下行'){
				                    	    str +="<img class='up' src='<%=path %>/img/arrow_down_gray.png'><img class='down' src='<%=path %>/img/arrow_up.png'>";
				                       }
				                        if(data.records.door!='关门' && data.records.people=='有人'){
				                    	    str +="<img src='<%=path %>/img/close.png' alt='' />";
				                       }
				                         if(data.records.door!='关门' && data.records.people!='有人'){
				                    	    str +="<img src='<%=path %>/img/close01.png' alt=''/>";
				                       }
				                         if(data.records.door=='关门'){
				                    	    str +="<img src='<%=path %>/img/dianti.png' alt='' />";
				                       }
				                       str +="</li >";
				                       str +="</div>";
				                       str +="</ul >";
				                       $("#monitor").append(str); 
				                       $("#monitor").listview("refresh");   //在使用'ul'标签时才使用，作用:刷新列表
									   $("#monitor").trigger("create"); 
				                       $.parser.parse($("#monitor").parent());  
				               }
				    });
	
		}
	</script>
		</body>
</html>
