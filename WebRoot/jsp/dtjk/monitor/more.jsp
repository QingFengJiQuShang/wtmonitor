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
    
    <title>多台监控</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/dtjk/list_more.css" />
	<script type="text/javascript">
		 function myrefresh(){
			window.location.reload();
		}
		setTimeout('myrefresh()',${dictionarie.dictionary*1000}); //指定30秒刷新一次s
</script>
	</head>

	<body>
		<div class="con">
			<p class="user">电梯监控</p>
			<div class="warp">
				<ul class="list_more clearfix">
				  <c:forEach items="${list}" var="list" varStatus="s">
				   			<li class="fl">
						<div class="whole">
							<p class="info clearfix">
								<span class="xuhao fl">${s.index + 1 }</span>
								<span class="zhuc fl">电梯注册号:${list.elevator.registerid }</span>
								<span class="code fl">识别号:${list.elevator.distinguishid }</span>
							</p>
							<ul class="show clearfix">
								<li class="img fl">
									<c:if test="${list.elevator.state!='维保' and list.elevator.state!='故障' }">
									<span class="normal_green">正常</span></c:if>
									<c:if test="${list.elevator.state=='故障'}"><span class="normal">故障</span></c:if>
									<c:if test="${list.elevator.state=='维保'}"><span class="normal_black">维保</span></c:if>
									
									<c:if test="${list.elevator.state!='离线'}"><span class="on_line_green">在线</span></c:if>
									<c:if test="${list.elevator.state=='离线'}"><span class="on_line">离线</span></c:if>
									
									<span class="loding">${list.record.floor }</span>
									<span class="or dianti_down"></span>
									<c:if test="${list.record.direction=='上行'}">
									<img class="up" src="<%=path %>/img/arrow_down_gray.png">
									<img class="down" src="<%=path %>/img/arrow_up_red.png">
									</c:if>
									<c:if test="${list.record.direction=='下行'}">
									<img class="up" src="<%=path %>/img/arrow_down_red.png">
									<img class="down" src="<%=path %>/img/arrow_up.png">
									</c:if>
									<c:if test="${list.record.direction!='下行' and list.record.direction!='上行'}">
									<img class="up" src="<%=path %>/img/arrow_down_gray.png">
									<img class="down" src="<%=path %>/img/arrow_up.png">
									</c:if>
									<c:if test="${list.record.door!='关门' and list.record.people=='有人'}"><img src="<%=path %>/img/close.png" alt="" /></c:if>
									<c:if test="${list.record.door!='关门' and list.record.people!='有人'}"><img src="<%=path %>/img/close01.png" alt="" /></c:if>
									<c:if test="${list.record.door=='关门'}"><img src="<%=path %>/img/dianti.png" alt="" /></c:if>
								
								</li>
								<li class="fl dianti_info">
									<h3>电梯状态</h3>
									<p>楼&nbsp;层&nbsp;位&nbsp;置：${list.record.floor }层</p>
									<p>运&nbsp;行&nbsp;方&nbsp;向：${list.record.direction}</p>
									<p>是&nbsp;否&nbsp;有&nbsp;人：${list.record.people}</p>
									<p>电梯门状态：${list.record.door}</p>
									<p>电&nbsp;梯&nbsp;状态：
									<c:if test="${list.elevator.state!='维保' and list.elevator.state!='故障' }">正常</c:if>
									<c:if test="${list.elevator.state=='故障'}">故障</c:if>
									<c:if test="${list.elevator.state=='维保'}">维保</c:if>
									</p>
									<p>在&nbsp;线&nbsp;状&nbsp;态：
									<c:if test="${list.elevator.state!='离线'}">在线</c:if>
									<c:if test="${list.elevator.state=='离线'}">离线</c:if>
									</p>
									<p>供&nbsp;电&nbsp;状&nbsp;态：
									<c:if test="${list.record.type!='停电'}">正常</c:if>
									<c:if test="${list.record.type=='停电'}">停电</c:if>
									</p>
								</li>
								<li class="fl gu_info">
									<h3>故障信息</h3>
									<p class="clearfix">
										<span class="fr">
											超速
											<c:if test="${list.record.type=='超速'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='超速'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
										<span class="fl">
											困人
											<c:if test="${list.record.type=='困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											冲顶困人
											<c:if test="${list.record.type=='冲顶困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='冲顶困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											冲顶
											<c:if test="${list.record.type=='冲顶'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='冲顶'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											蹲底困人
											<c:if test="${list.record.type=='蹲底困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='蹲底困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											蹲底
											<c:if test="${list.record.type=='蹲底'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='蹲底'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											非平层困人
											<c:if test="${list.record.type=='非平层困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='非平层困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											非平层停梯
											<c:if test="${list.record.type=='非平层停梯'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='非平层停梯'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											门关不上
											<c:if test="${list.record.type=='门关不上'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='门关不上'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											开门走梯
											<c:if test="${list.record.type=='开门走梯'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='开门走梯'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											运动中开门
											<c:if test="${list.record.type=='运动中开门'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='运动中开门'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											开门不到位
											<c:if test="${list.record.type=='开门不到位'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='开门不到位'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											非平层开门
											<c:if test="${list.record.type=='非平层开门'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${list.record.type!='非平层开门'}">
											<img class="fr"  src="<%=path %>/img/dianti_green.png" alt="" />
											</c:if>
										</span>
									</p>
								</li>
							</ul>
						</div>

					</li>
				   </c:forEach>
				</ul>
			</div>
		</div>
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

</html>
