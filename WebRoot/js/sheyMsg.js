/*
 *Author:sohighthesky
 *From:http://blog.csdn.net/sohighthesky  
 *Date:2009-11-9
 */
/*
 *box 指定要显示消息框或者其id
 *options:参见代码中setOptions中的注释
 */
var sheyMsg=function(box,options) {
	this.box=this.g(box);
	this.setOptions(options);
	this.init();
}
sheyMsg.prototype={
    ae:function(e,call) {
        if(window.addEventListener)window.addEventListener(e,call,false);
        else window.attachEvent("on"+e,call);
    },
    g:function(id) {return typeof(id)=="string"?document.getElementById(id):id; },
    isFixed:!window.ActiveXObject || (navigator.userAgent.indexOf("MSIE 6")==-1 &&  document.compatMode=="CSS1Compat"),
    setOptions:function(options) {
		this.options={//默认配置
				showDelay:1,//显示延时
				autoHide:0,//自动隐藏时间，设置为0时，不自动隐藏
				onShow:function(){},//显示后调用
				onHide:function(){}//隐藏后调用
		};
		for(var o in options) {
			this.options[o]=options[o];
		}
    },
    hide:function() {//隐藏
		var _top=this.box.clientHeight;
		var o=this;
		if(/ing$/.test(o.status))return;
		o.status="hiding";
		clearTimeout(o.tt);
		o.t=setInterval(function() {
		    if(o.isFixed)
			    o.box.style.bottom=(-o.box.clientHeight+(--_top))+'px';
			else
			    o.box.style.top=o.de.scrollTop+o.de.clientHeight-5-(--_top) +"px";
			if(_top==-5) {
				clearInterval(o.t);
				o.status="hide";
				o.box.style.display="none";
				o.options.onHide();
			}
		},5);
    },
    show:function() {//显示
		var _top=0;
		var o=this;
		if(/ing$/.test(o.status))return;
		o.status="showing";
		clearTimeout(o.tt);
		o.box.style.display="block";
		o.t=setInterval(function() {
		    if(o.isFixed)
			    o.box.style.bottom=(-o.box.clientHeight+(++_top))+"px";
			else
			    o.box.style.top=(o.de.scrollTop+o.de.clientHeight-5-(++_top)) +"px";
			if(_top==o.box.clientHeight) {
				clearInterval(o.t);
				o.status="show";
				o.options.onShow();
				var h=o.options.autoHide-0;
				if(h) o.tt=setTimeout(function() {o.hide();},h*1000);
			}
		},1);
    },
    fixIE6:function() {//IE6 滚动定位
        this.box.style.left=this.de.scrollLeft+this.de.clientWidth-this.box.clientWidth-2+"px";
        if(this.status=="show") {            
            this.box.style.top=this.de.scrollTop+this.de.clientHeight-this.box.clientHeight-5+"px";
        } else if(this.status=="hide") {
		    this.box.style.top=this.de.scrollTop+this.de.clientHeight+5+"px";
        }
    },
    init:function() {
		with(this.box.style) {
			display="block";//显示之后才能取出宽度和高度
			if(this.isFixed) {
				position="fixed";
				right="2px";
				bottom=(-this.box.clientHeight-5)+"px";
			} else {
				position="absolute";
			}
		}
		this.status="hide";
		var o=this;
		if(!this.isFixed) {
		    o.de=document.compatMode=="CSS1Compat"?document.documentElement:document.body;
		    var timer;
		    this.ae("resize",function() { clearTimeout(timer);timer=setTimeout(function(){o.fixIE6.call(o)},30);});
		    this.ae("scroll",function() { clearTimeout(timer);timer=setTimeout(function(){o.fixIE6.call(o)},30);});
		    this.fixIE6();//加载时指定位置
		}
		o.box.style.display="none";
		o.tt=setTimeout(function() {o.show();},o.options.showDelay*1000);
	}
}