<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

	<body>
	<form id="form"   method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">保险单</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">新增保险单</p>
				<div class="table_con">
					
				<p class="fill">
					<label for="start_end">保单开始日期&nbsp;:&nbsp;</label>
					<input type="text" readonly="readonly" id="start_end" placeholder="请选择" readonly="readonly" />
				</p>
				<p class="fill">
					<label for="start_end">保单结束日期&nbsp;:&nbsp;</label>
					<input type="text" readonly="readonly" id="time_end" placeholder="请选择" readonly="readonly" />
				</p>
				<p class="fill">
					<label for="unit">保险金额&nbsp;:&nbsp;</label>
					<input type="text" id="unit" />
				</p>
				<p class="fill">
					<label for="place">受益人&nbsp;:&nbsp;</label>
					<input type="text" id="place" />
				</p>

				<p class="fill">
					<label for="wb_unit">是否理赔&nbsp;:&nbsp;</label>
					<select name="">
						<option value="">否</option>
						<option value="">是</option>
					</select>
				</p>
				<p class="fill">
					<label for="">上传保单图片&nbsp;:&nbsp;</label>
					<label for="sendimg" id="up">选择图片</label>
				</p>
				<ul class="img-list clearfix">
					<li>
						<input type="file" id="sendimg" multiple class="sendimg" accept="image/gif;image/jpg;" />
					</li>
				</ul>
					<div class="keep clearfix">
						<button class="fl"    onclick="history.go(-1); ">保存</button>
						<button class="fr"   onclick="history.go(-1); ">取消</button>
					</div>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    //		图片格式
    var imgData = {};
    var  i = 0;
    function convertImgToBase64(url, callback, outputFormat){ 
        var canvas = document.createElement('CANVAS'); 
        var ctx = canvas.getContext('2d'); 
        var img = new Image; 
        img.crossOrigin = 'Anonymous'; 
        img.onload = function(){
          var width = img.width;
          var height = img.height;
          // 按比例压缩4倍
          var rate = (width<height ? width/height : height/width)/4;
          canvas.width = width*rate; 
          canvas.height = height*rate; 
          ctx.drawImage(img,0,0,width,height,0,0,width*rate,height*rate); 
          var dataURL = canvas.toDataURL(outputFormat || 'image/png'); 
          callback.call(this, dataURL); 
          canvas = null; 
        };
        img.src = url; 
      }

       function getObjectURL(file) {
            var url = null ; 
            if (window.createObjectURL!=undefined) {  // basic
              url = window.createObjectURL(file) ;
            } else if (window.URL!=undefined) {       // mozilla(firefox)
              url = window.URL.createObjectURL(file) ;
            } else if (window.webkitURL!=undefined) { // web_kit or chrome
              url = window.webkitURL.createObjectURL(file) ;
            }
            return url ;
      }
     // 前端只需要给input file绑定这个change事件即可（上面两个方法不用管）huodong-msg为input class
      $('.sendimg').bind('change',function(event){
            var imageUrl = getObjectURL($(this)[0].files[0]);
            convertImgToBase64(imageUrl, function(base64Img){
            	 var concent = '<li class="img"><img src="'+imageUrl+'"></li> ';
            $(".img-list").prepend(concent);
              // base64Img为转好的base64,放在img src直接前台展示(<img src="data:image/jpg;base64,/9j/4QMZRXh...." />)
//            alert(base64Img);
              // base64转图片不需要base64的前缀data:image/jpg;base64
//            alert(base64Img.split(",")[1]);
                var base = base64Img.split(",");
                imgData[i]={
                   "image":base[1]
                };
                ++i;
                console.log(imgData);
            });
            event.preventDefault(); 
        }); 
     
     </script>
</html>
