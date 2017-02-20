<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>保险单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
 <script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>
	<!-- 鼠标悬浮图片放大效果 -->
		<link rel="stylesheet" type="text/css" href="<%=path%>/imageHover/css/normalize.css" />	
		<link rel="stylesheet" type="text/css" href="<%=path%>/imageHover/css/style.css" />	
 		<script src="<%=path%>/imageHover/js/prefixfree.min.js" type="text/javascript" charset="utf-8"></script>
	
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
	
		<style>
			select {
				width: 153px;
				height: 30px;
				border: 1px solid #d2d2d2;
			}
			#sendimg {
				display: none;
			}
			.img-list {
				overflow: hidden;
				padding: 0 10px;
			}
			.img-list li {
				float: left;
				width: 20%;
				margin: 0 1%;
			}
			.img-list img {
				width: 100%;
			}
			#up {
				width: 80px;
			    height: 27px;
			    -webkit-border-radius: 5px;
			    -moz-border-radius: 5px;
			    border-radius: 5px;
			    color: #fff;
			    background-color: #00AAEE;
			    line-height: 25px;
			    text-align: center;
			    padding: 0;
			}
		</style>
	</head>
  
	<body  style="background-color:#fff;">
	<form id="form"   action="<%=path %>/safeAction.do?method=updateEntity"    method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">保险单</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改保险单</p>
				<div class="table_con">
				  <script type="text/plain" id="j_ueditorupload" style="height:5px;display:none;" ></script>
    <script>
      //实例化编辑器
      var o_ueditorupload = UE.getEditor('j_ueditorupload',
      {
        autoHeightEnabled:false
      });
      o_ueditorupload.ready(function ()
      {
      
    o_ueditorupload.hide();//隐藏编辑器
      
    //监听图片上传
    o_ueditorupload.addListener('beforeInsertImage', function (t,arg)
    {
    	var picturePath=document.getElementById("picturePath").value;
    	for(var i=0;i<arg.length;i++){
    		picturePath=picturePath+arg[i].src+",";
    	}
    	document.getElementById("picturePath").value=picturePath;
         
    });
      });
        
      //弹出图片上传的对话框
      function upImage()
      {
        var myImage = o_ueditorupload.getDialog("insertimage");
        myImage.open();
      }
     
    </script>
    			<p class="fill">
					<label for="unit">保单号&nbsp;:&nbsp;</label>
					<input type="text" id="number"  name="safe.number"  value="${list.number}"  placeholder="请输入"/>
				</p>
				<p class="fill">
					<label for="unit">保险公司&nbsp;:&nbsp;</label>
					<input type="hidden" id="company"  name="safe.company"  value="${list.company}"  placeholder="请输入"/>
					<input type="hidden"  id="safeUnitId"  name="safe.safeUnitId.id"  value="${list.safeUnitId.id}" />
					<input type="text"  id="safeUnitId1"  placeholder="请选择"   value="${list.safeUnitId.name}"  readonly="readonly"   onclick="selectsafeUnitId('safeUnitId','safeUnitId1');"/>
				
				</p>	
				<p class="fill">
					<label for="start_end">保单开始日期&nbsp;:&nbsp;</label>
					<input type="hidden" id="id"  name="safe.id"  value="${list.id}" />
					<input type="hidden" id="elevatorId"  name="safe.elevatorId.id"  value="${list.elevatorId.id}" />
					
					<input type="text"  class="Wdate"  name="startTime"  id="start_end"    value="<fmt:formatDate value="${list.startTime}"  pattern='yyyy-MM-dd'/>"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
				</p>
				<p class="fill">
					<label for="start_end">保单结束日期&nbsp;:&nbsp;</label>
					<input type="text"  class="Wdate"   name="endTime"  	 id="time_end"  value="<fmt:formatDate value="${list.endTime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
				</p>
				<p class="fill">
					<label for="unit">保险金额&nbsp;:&nbsp;</label>
					<input type="text" id="money"  name="safe.money"   placeholder="请输入" value="${list.money}"/>
				</p>
				<p class="fill">
					<label for="place">受益人&nbsp;:&nbsp;</label>
					<input type="text" id="beneficiary"  name="safe.beneficiary"  placeholder="请输入"  value="${list.beneficiary}"  />
				</p>

				<p class="fill">
					<label for="wb_unit">是否理赔&nbsp;:&nbsp;</label>
					<select id="state" name="safe.state">
						<option value="">请选择</option>
						<option <c:if test="${list.state=='否'}">selected="selected" </c:if>  value="否">否</option>
						<option <c:if test="${list.state=='是'}">selected="selected" </c:if> value="是">是</option>
					</select>
				</p>
				<p class="fill">
					<label for="">上传保单图片&nbsp;:&nbsp;</label>
					<label for="sendimg" id="up"   onClick="upImage()">上传图片</label>
					<input type="hidden" id="picturePath"  name="safe.picturePath"  placeholder="请输入" value="${list.picturePath}"/>
  					<div class="gallery cf">
						<c:forEach items="${str}" var="str" varStatus="s">
							<div>
						    	<img src="${str}"  style="width: 100px;height: 100px;"/>
						  	</div>
						</c:forEach>
						</div>
				</p>
				<ul class="img-list clearfix">
					<li>
						<input type="file" id="sendimg" multiple class="sendimg" accept="image/gif;image/jpg;" />
					</li>
				</ul>
					<p class="or clearfix">
						<input type="button"  value="保存"  onclick="add();">
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
  function add(){
		 	$('#form').submit();
     }
   
     </script>
</html>
