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
	<script type="text/javascript">
		 function myrefresh(){
			window.location.reload();
		}
		setTimeout('myrefresh()',${dictionarie.dictionary*1000}); //指定30秒刷新一次s
</script>
	</head>

	<body>
		
				
				<ul class="list_more clearfix">
					<li>
						<div class="whole">
							<ul class="show clearfix">
								<li class="img fl">
									<span class="normal">
									<c:if test="${list.state!='维保' and list.state!='故障' }">正常</c:if>
									<c:if test="${list.state=='故障'}">故障</c:if>
									<c:if test="${list.state=='维保'}">维保</c:if>
									</span>
									<span class="on_line">
									<c:if test="${list.state!='离线'}">正常</c:if>
									<c:if test="${list.state=='离线'}">离线</c:if>
									</span>
									<span class="loding">${records.floor }</span>
									<span class="or dianti_down"></span>
									<c:if test="${records.direction!='上行'}">
									<img class="up" src="<%=path %>/img/arrow_down_gray.png">
									<img class="down" src="<%=path %>/img/arrow_up_red.png">
									</c:if>
									<c:if test="${records.direction=='下行'}">
									<img class="up" src="<%=path %>/img/arrow_down_red.png">
									<img class="down" src="<%=path %>/img/arrow_up.png">
									</c:if>
									<c:if test="${records.direction!='下行' and records.direction!='上行'}">
									<img class="up" src="<%=path %>/img/arrow_down_gray.png">
									<img class="down" src="<%=path %>/img/arrow_up.png">
									</c:if>
									
									<c:if test="${records.door!='关门'}"><img src="<%=path %>/img/dianti_close.png" alt="" /></c:if>
									<c:if test="${records.door=='关门'}"><img src="<%=path %>/img/dianti.png" alt="" /></c:if>
									
								</li>
								<li class="fl dianti_info">
									<h3>电梯信息</h3>
									<p>楼&nbsp;层&nbsp;位&nbsp;置：${records.floor }层</p>
									<p>运&nbsp;行&nbsp;方&nbsp;向：${records.direction}</p>
									<p>是&nbsp;否&nbsp;有&nbsp;人：${records.people}</p>
									<p>电梯门状态：${records.door}</p>
									<p>电&nbsp;梯&nbsp;状态：
									<c:if test="${list.state!='维保' and list.state!='故障' }">正常</c:if>
									<c:if test="${list.state=='故障'}">故障</c:if>
									<c:if test="${list.state=='维保'}">维保</c:if>
									</p>
									<p>在&nbsp;线&nbsp;状&nbsp;态：
									<c:if test="${list.state!='离线'}">正常</c:if>
									<c:if test="${list.state=='离线'}">离线</c:if>
									</p>
									<p>供&nbsp;电&nbsp;状&nbsp;态：
									<c:if test="${records.type!='停电'}">正常</c:if>
									<c:if test="${records.type=='停电'}">停电</c:if>
									</p>
									<p></p>
									<p></p>
								</li>
								<li class="fl gu_info">
									<h3>故障信息</h3>
									<p class="clearfix">
										
										<span class="fr">
											超速
											<c:if test="${records.type=='超速'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='超速'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
										<span class="fl">
											困人
											<c:if test="${records.type=='困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											冲顶困人
											<c:if test="${records.type=='冲顶困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='冲顶困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											冲顶
											<c:if test="${records.type=='冲顶'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='冲顶'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											蹲底困人
											<c:if test="${records.type=='蹲底困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='蹲底困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											蹲底
											<c:if test="${records.type=='蹲底'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='蹲底'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											非平层困人
											<c:if test="${records.type=='非平层困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='非平层困人'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											非平层停梯
											<c:if test="${records.type=='非平层停梯'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='非平层停梯'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											门关不上
											<c:if test="${records.type=='门关不上'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='门关不上'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											开门走梯
											<c:if test="${records.type=='开门走梯'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='开门走梯'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											运动中开门
											<c:if test="${records.type=='运动中开门'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='运动中开门'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
										<span class="fr">
											开门不到位
											<c:if test="${records.type=='开门不到位'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='开门不到位'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
									</p>
									<p class="clearfix">
										<span class="fl">
											非平层开门
											<c:if test="${records.type=='非平层开门'}">
											<img class="fr"  src="<%=path %>/img/dianti_red.png" alt="" />
											</c:if>
											<c:if test="${records.type!='非平层开门'}">
											<img class="fr"  src="<%=path %>/img/dianti_gray.png" alt="" />
											</c:if>
										</span>
									</p>
								</li>
								<li class="fl" style="width: 66%; ">
									<p class="update clearfix">更新时间:<fmt:formatDate value='${records.foundTime}' pattern='yyyy-MM-dd HH:mm:ss'/><button class="fr">查看历史记录</button></p>
								</li>
							</ul>
							
							</div>
						</div>
					</li>
				</ul>		
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/dtjk/lsit_details.js" type="text/javascript" charset="utf-8"></script>
</html>
