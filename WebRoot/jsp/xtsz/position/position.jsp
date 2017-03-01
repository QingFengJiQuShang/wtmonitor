<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>地理位置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list.css" />
		<link rel="stylesheet" href="<%=path %>/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=path %>/css/postion/style.css">
		<script src="<%=path %>/js/jquery-1.7.2.min.js"></script>
	<style>
			.text p {
				font-size: 14px;
				margin-bottom: 15px;
				word-wrap: break-word;
				word-break: break-all;
				text-indent: 10px;
			}
			.zensa{
				margin-top: -430px;
				margin-left:510px;
				margin-right:20px;
				height: 400px;
			}
			.zensa input{margin-left:10px;}
			.table{display: none;}
			.menu{display:none}
			tbody table_con td{text-align: center;}
		</style>
	<script type="text/javascript">
			$(document).ready(function() {
			    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
			    $('.tree li.parent_li > span').on('click', function (e) {

			        var children = $(this).parent('li.parent_li').find(' > ul > li');
			        if (children.is(":visible")) {
			            children.hide('fast');
			            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
			        } else {
			            $('li.parent_li ul li').find(' > ul > li').hide();
			            $('.tree li.parent_li ul li > span').attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
			            children.show('fast');
			            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
			        }
			        e.stopPropagation();
			    });
					    //批量删除
			$(".del").click(function() {
					$(".wei").each(function() {
						if($(this).children("i").hasClass("gou")) {
							$(this).parents("tr").remove();
						}
					})
				});
			//单个删除
		$(".del_one").click(function() {
				$(this).parents("tr").remove();
			});
			//全选
		$("tbody").on("click", ".wei", function() {
				$(this).children("i").toggleClass("gou");
				var checked = $(".wei").length;
				var unchecked = $("tbody").find(".gou").length
				if(checked == unchecked) {
					$(".all").children("i").addClass("gou")
				} else {
					$(".all").children("i").removeClass("gou")
				}
			});
			//取消全选
		$(".all").on("click", function() {
			var checked = $(".wei").length;
			var unchecked = $("tbody").find(".gou").length
			if(checked == unchecked) {
				$(this).children("i").removeClass("gou")
				$("tbody").find("i").removeClass("gou")
			} else {
				$(this).children("i").addClass("gou")
				$("tbody").find("i").addClass("gou")
			}
		});
			});			
	</script>
	</head>
	<body>
		<div class="con">
			<p class="user">系统地图</p>						
					<div class="tree well">
						<ul>


							<ul>
								<li>
													<span>
														<i class="icon-folder-open"></i>省/直辖市
  													</span>
									<ul>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>北京市
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>东城区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>西城区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>昌平区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>海淀区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>通州区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>朝阳区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>丰台区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>顺义区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>房山区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>怀柔区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>宣武区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>石景山区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>门头沟区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>平谷区</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>天津市
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>和平区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>河西区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>南开区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>河北区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>虹桥区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>塘沽区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>汉沽区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>大港区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>东里区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>西青区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>津南区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>北辰区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>武清区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>宝坻区</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>河北省
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>石家庄市</span>
													<ul>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>长安区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>桥东区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>桥西区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>新华区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>井阱矿区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>裕华区</span>
														</li>
													</ul>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>唐山市</span>
													<ul>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>路南区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>路北区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>古治区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>开平区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>丰南区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>丰润区</span>
														</li>
													</ul>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>秦皇岛市</span>
													<ul>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>海港区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>山海区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>北戴河区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>青龙满族自治县</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>昌黎县</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>抚宁县</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>卢龙县</span>
														</li>
													</ul>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>邯郸市</span>
													<ul>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>邯山区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>丛台区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>复兴区区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>峰峰矿区</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>邯郸县县</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>临漳县</span>
														</li>
														<li>
															<span class="black_con9"><i class="icon-leaf"></i>成安县</span>
														</li>
													</ul>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>邢台市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>保定市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>张家口市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>承德市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>沧州市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>廊坊市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>衡水市</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>山西省
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>太原市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>大同市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>阳泉市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>长治市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>晋城市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>朔州市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>晋中市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>运城市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>忻州市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>临汾市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>吕梁市</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>内蒙古自治区
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>呼和浩特市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>包头市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>乌海市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>赤峰市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-signf"></i>通辽市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>鄂尔多斯市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-signf"></i>呼伦贝尔市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>巴彦淖尔市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-signf"></i>乌兰察木市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>兴安盟</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>锡林郭勒盟</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>阿拉善盟</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>辽宁省
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>沈阳市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>大连市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>鞍山市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>本溪市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>抚顺市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>丹东市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>营口市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>阜新尔市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>辽阳市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>盘锦市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>铁岭市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>朝阳市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>葫芦岛市</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>吉林省
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>长春市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>吉林市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>四平市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>辽源市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>通化市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>白山市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>松原市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>白城市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>延边朝鲜自治州</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>黑龙江省
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>哈尔滨市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>齐齐哈尔市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>鸡西市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>鹤岗市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>双鸭山市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>大庆市市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>伊春市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>佳木斯市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>七台河市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>牡丹江市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-minus-sign"></i>黑河市</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>绥化市</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>上海市
															</span>
											<ul>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>黄浦区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>卢湾区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>徐汇区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>静安区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>普陀区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>闸北区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>虹口区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>杨浦区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>闵行区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>宝山区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>嘉定区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>浦东新区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>金山区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>松江新区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>青浦新区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>南汇区</span>
												</li>
												<li>
													<span class="black_con9"><i class="icon-leaf"></i>奉贤区</span>
												</li>
											</ul>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>江苏省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>浙江省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>安徽省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>福建省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>江西省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>山东省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>河南省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>湖北省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>湖南省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>广东省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>广西壮族自治区
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>海南省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>重庆市
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>四川省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>贵州省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>云南省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>西藏自治区
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>陕西省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>甘肃省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>青海省
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>宁夏回族自治区
															</span>
										</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>新疆维吾尔自治区
															</span>
										</li><li>
															<span class="black_con9"><i class="icon-minus-sign"></i>台湾省省
															</span>
									</li><li>
															<span class="black_con9"><i class="icon-minus-sign"></i>香港特别行政区
															</span>
									</li>
										<li>
															<span class="black_con9"><i class="icon-minus-sign"></i>澳门特别行政区
															</span>
										</li>
									</ul>
								</li>
							</ul>

						</ul>
					</div>
		</div>
		<div class="zensa">
			<div class="table">
					<div class="or clearfix">
						<p class="fl add" onclick="lod(5)"><img src="<%=path %>/img/add.png" />新增</p>
						<p class="fl del">批量删除</p>
					</div>
					<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<!--<th class="all"><i></i></th>-->
								<th>序列</th>
								<th>城市列表</th>
								<!--<th>操作</th>-->
							</thead>
							<tbody>
								<tr>
									<!--<td class="wei"><i class=""></i></td>-->
									<td style="text-align: center;">1</td>
									<td style="text-align: center;" class="change1">东城区</td>
									<!--<td>-->
										<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
										<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
										<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
									<!--</td>-->
								</tr>
								<tr>
									<!--<td class="wei"><i class=""></i></td>-->
									<td style="text-align: center;" class="change2">2</td>
									<td style="text-align: center;">西城区</td>
									<!--<td>-->
										<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
										<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
										<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
									<!--</td>-->
								</tr>
								<tr>
									<!--<td class="wei"><i class=""></i></td>-->
									<td style="text-align: center;">3</td>
									<td style="text-align: center;" class='change3'>昌平区</td>
									<!--<td>-->
										<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
										<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
										<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
									<!--</td>-->
								</tr>
								<tr>
									<!--<td class="wei"><i class=""></i></td>-->
									<td style="text-align: center;" >4</td>
									<td style="text-align: center;" class="change4">海淀区</td>
									<!--<td>-->
										<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
										<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
										<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
									<!--</td>-->
								</tr>
								<tr>
									<!--<td class="wei"><i class=""></i></td>-->
									<td style="text-align: center;">5</td>
									<td style="text-align: center;" class="change6">朝阳区</td>
									<!--<td>-->
										<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
										<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
										<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
									<!--</td>-->
								</tr>
							</tbody>
						</table>
						<div class="choose">
							<p class="num">当前显示<span>1</span>到<span>10</span>条，共<span>17</span>条记录</p>
							<div class="page">
								<a href="javascript:;" >首页</a>
								<a href="">
									<img src="<%=path %>/img/last.png" alt="" />
								</a>
								<a href="">1</a>
								<a href="">2</a>
								<a href="">
									<img src="<%=path %>/img/next.png"/>
								</a>
								<a href="" class="mo">尾页</a>
							</div>
						</div>
					</div>
				</div>



			<div class="menu">
				<div class="or clearfix">
					<p class="fl add" onclick="lod(5)"><img src="<%=path %>/img/add.png" />新增</p>
					<p class="fl del">批量删除</p>
				</div>
				<div class="table_con">
					<table border="" cellspacing="" cellpadding="">
						<thead>
						<!--<th class="all"><i></i></th>-->
						<th>序列</th>
						<th>城市列表</th>
						<!--<th>操作</th>-->
						</thead>
						<tbody>
						<tr>
							<!--<td class="wei"><i class=""></i></td>-->
							<td style="text-align: center;">1</td>
							<td style="text-align: center;" class="change1">东城区</td>
							<!--<td>-->
							<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
							<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
							<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
							<!--</td>-->
						</tr>
						<tr>
							<!--<td class="wei"><i class=""></i></td>-->
							<td style="text-align: center;" class="change2">1</td>
							<td style="text-align: center;">西城区</td>
							<!--<td>-->
							<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
							<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
							<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
							<!--</td>-->
						</tr>
						<tr>
							<!--<td class="wei"><i class=""></i></td>-->
							<td style="text-align: center;">1</td>
							<td style="text-align: center;" class='change3'>昌平区</td>
							<!--<td>-->
							<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
							<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
							<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
							<!--</td>-->
						</tr>
						<tr>
							<!--<td class="wei"><i class=""></i></td>-->
							<td style="text-align: center;" >1</td>
							<td style="text-align: center;" class="change4">海淀区</td>
							<!--<td>-->
							<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
							<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
							<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
							<!--</td>-->
						</tr>
						<tr>
							<!--<td class="wei"><i class=""></i></td>-->
							<td style="text-align: center;">5</td>
							<td style="text-align: center;" class="change6">朝阳区</td>
							<!--<td>-->
							<!--<img src="<%=path %>/img/content.png" alt="" onclick="lod(6)"/>-->
							<!--<img src="<%=path %>/img/compile.png" onclick="lod(7)"/>-->
							<!--<img src="<%=path %>/img/del.png" alt="" class="del_one"/>-->
							<!--</td>-->
						</tr>
						</tbody>
					</table>
					<div class="choose">
						<p class="num">当前显示<span>1</span>到<span>10</span>条，共<span>17</span>条记录</p>
						<div class="page">
							<a href="javascript:;" >首页</a>
							<a href="">
								<img src="<%=path %>/img/last.png" alt="" />
							</a>
							<a href="">1</a>
							<a href="">2</a>
							<a href="">
								<img src="<%=path %>/img/next.png"/>
							</a>
							<a href="" class="mo">尾页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
  </body>
  <script src="<%=path %>/js/jquery.min.js"></script>
	<script type="text/javascript">
		
		$(function() {
			$("li").click(function(){
				$(".table").show();
			});
		$("span").click(function(){
			   $(".table").hide();
			});
		});
		$(function() {
			$("span").click(function(){
				$(".menu").show();
			});
			$("ul li ul li").click(function(){
				$(".menu").hide();
			});
		});
</script>
</html>
