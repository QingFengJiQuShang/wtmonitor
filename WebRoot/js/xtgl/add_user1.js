
function type(flag){
			 if(flag==1){
				 document.getElementById("quyu").style.display="none";
				 document.getElementById("unitType").style.display="none";
				 document.getElementById("unitName").style.display="none";

			 }else if(flag==2){
				 document.getElementById("quyu").style.display="";
				 document.getElementById("unitType").style.display="none";
				 document.getElementById("unitName").style.display="none";
				 reSetHeight2();
			 }else{
				  document.getElementById("quyu").style.display="";
				 document.getElementById("unitType").style.display="";
				 document.getElementById("unitName").style.display="";
				 reSetHeight2();
			 }

		  }


 //新增用户时 判断用户名是否存在
		  function skip(){
			var loginname=document.getElementById("logn").value;
			$.ajax({
					     mtype:'post',
			             url: "usersAction.do?method=onlyUser",
			             data: {"loginname":loginname},
			             dataType: "text",
			             success: function(data){
			                       if(data==0) {
			                    	   alert("该用户名已存在，请重新输入！");
			                    	   $("#logn").focus();
			                      }
			               }
			    });
	}

//修改用户时，判断用户名是否存在
		  function skip1(){
			 var loginname1=document.getElementById("logn1").value;
			var loginname=document.getElementById("logn").value;
			if(loginname!=loginname1){
				$.ajax({
					     mtype:'post',
			             url: "usersAction.do?method=onlyUser",
			             data: {"loginname":loginname},
			             dataType: "text",
			             success: function(data){
			                       if(data==0) {
			                    	   alert("该用户名已存在，请重新输入！");
			                    	   $("#logn").focus();
			                      }
			               }
			    });
			}

	}
	/**
	 * 电梯列表  全选
	 * @memberOf {TypeName}
	 */
$("#dtjk_dtlb_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dtjk_dtlb']").prop({
			"checked": true
		});
	} else {
		$("input[id='dtjk_dtlb']").prop({
			"checked": false
		});
	}
})
	/**
	 * 记录回放  全选
	 * @memberOf {TypeName}
	 */
$("#dtjk_jlhf_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dtjk_jlhf']").prop({
			"checked": true
		});
	} else {
		$("input[id='dtjk_jlhf']").prop({
			"checked": false
		});
	}
})
/**
	 * 上报周期  全选
	 * @memberOf {TypeName}
	 */
$("#dtjk_sbzq_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dtjk_sbzq']").prop({
			"checked": true
		});
	} else {
		$("input[id='dtjk_sbzq']").prop({
			"checked": false
		});
	}
})
/**
	 * 白名单  全选
	 * @memberOf {TypeName}
	 */
$("#dtjk_bmd_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dtjk_bmd']").prop({
			"checked": true
		});
	} else {
		$("input[id='dtjk_bmd']").prop({
			"checked": false
		});
	}
})
/**
	 * 电梯流量  全选
	 * @memberOf {TypeName}
	 */
$("#dtjk_dtll_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dtjk_dtll']").prop({
			"checked": true
		});
	} else {
		$("input[id='dtjk_dtll']").prop({
			"checked": false
		});
	}
})
/**
	 * 维保记录  全选
	 * @memberOf {TypeName}
	 */
$("#dtjk_wbjl_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dtjk_wbjl']").prop({
			"checked": true
		});
	} else {
		$("input[id='dtjk_wbjl']").prop({
			"checked": false
		});
	}
})
/**
	 * 年检记录  全选
	 * @memberOf {TypeName}
	 */
$("#dtjk_njjl_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dtjk_njjl']").prop({
			"checked": true
		});
	} else {
		$("input[id='dtjk_njjl']").prop({
			"checked": false
		});
	}
})
/**
	 * 服务费  全选
	 * @memberOf {TypeName}
	 */
$("#dtjk_fwf_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dtjk_fwf']").prop({
			"checked": true
		});
	} else {
		$("input[id='dtjk_fwf']").prop({
			"checked": false
		});
	}
})

/**
	 * 人工接警  全选
	 * @memberOf {TypeName}
	 */
$("#gzgl_rgjj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='gzgl_rgjj']").prop({
			"checked": true
		});
	} else {
		$("input[id='gzgl_rgjj']").prop({
			"checked": false
		});
	}
})

/**
	 * 当前故障  全选
	 * @memberOf {TypeName}
	 */
$("#gzgl_dqgz_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='gzgl_dqgz']").prop({
			"checked": true
		});
	} else {
		$("input[id='gzgl_dqgz']").prop({
			"checked": false
		});
	}
})

/**
	 * 历史故障  全选
	 * @memberOf {TypeName}
	 */
$("#gzgl_lsgz_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='gzgl_lsgz']").prop({
			"checked": true
		});
	} else {
		$("input[id='gzgl_lsgz']").prop({
			"checked": false
		});
	}
})

/**
	 * 使用单位  全选
	 * @memberOf {TypeName}
	 */
$("#dwgl_sydw_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dwgl_sydw']").prop({
			"checked": true
		});
	} else {
		$("input[id='dwgl_sydw']").prop({
			"checked": false
		});
	}
})
/**
	 * 维保单位  全选
	 * @memberOf {TypeName}
	 */
$("#dwgl_wbdw_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dwgl_wbdw']").prop({
			"checked": true
		});
	} else {
		$("input[id='dwgl_wbdw']").prop({
			"checked": false
		});
	}
})

/**
	 * 维保人员  全选
	 * @memberOf {TypeName}
	 */
$("#dwgl_wbry_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dwgl_wbry']").prop({
			"checked": true
		});
	} else {
		$("input[id='dwgl_wbry']").prop({
			"checked": false
		});
	}
})
/**
	 * 救援单位  全选
	 * @memberOf {TypeName}
	 */
$("#dwgl_jydw_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dwgl_jydw']").prop({
			"checked": true
		});
	} else {
		$("input[id='dwgl_jydw']").prop({
			"checked": false
		});
	}
})
/**
	 * 物业单位  全选
	 * @memberOf {TypeName}
	 */
$("#dwgl_wydw_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dwgl_wydw']").prop({
			"checked": true
		});
	} else {
		$("input[id='dwgl_wydw']").prop({
			"checked": false
		});
	}
})
/**
	 * 制造单位  全选
	 * @memberOf {TypeName}
	 */
$("#dwgl_zzdw_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dwgl_zzdw']").prop({
			"checked": true
		});
	} else {
		$("input[id='dwgl_zzdw']").prop({
			"checked": false
		});
	}
})
/**
	 * 保险单位  全选
	 * @memberOf {TypeName}
	 */
$("#dwgl_bxdw_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dwgl_bxdw']").prop({
			"checked": true
		});
	} else {
		$("input[id='dwgl_bxdw']").prop({
			"checked": false
		});
	}
})
/**
	 * 区域单位  全选
	 * @memberOf {TypeName}
	 */
$("#dwgl_qydw_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='dwgl_qydw']").prop({
			"checked": true
		});
	} else {
		$("input[id='dwgl_qydw']").prop({
			"checked": false
		});
	}
})

/**
	 * 未保电梯  全选
	 * @memberOf {TypeName}
	 */
$("#bxgl_wbdt_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='bxgl_wbdt']").prop({
			"checked": true
		});
	} else {
		$("input[id='bxgl_wbdt']").prop({
			"checked": false
		});
	}
})

/**
	 * 在保电梯  全选
	 * @memberOf {TypeName}
	 */
$("#bxgl_zbdt_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='bxgl_zbdt']").prop({
			"checked": true
		});
	} else {
		$("input[id='bxgl_zbdt']").prop({
			"checked": false
		});
	}
})

/**
	 * 脱保电梯  全选
	 * @memberOf {TypeName}
	 */
$("#bxgl_tbdt_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='bxgl_tbdt']").prop({
			"checked": true
		});
	} else {
		$("input[id='bxgl_tbdt']").prop({
			"checked": false
		});
	}
})

/**
	 * 保单记录  全选
	 * @memberOf {TypeName}
	 */
$("#bxgl_bdjl_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='bxgl_bdjl']").prop({
			"checked": true
		});
	} else {
		$("input[id='bxgl_bdjl']").prop({
			"checked": false
		});
	}
})

/**
	 * 理赔电梯  全选
	 * @memberOf {TypeName}
	 */
$("#bxgl_lpjl_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='bxgl_lpjl']").prop({
			"checked": true
		});
	} else {
		$("input[id='bxgl_lpjl']").prop({
			"checked": false
		});
	}
})

/**
	 * 保险统计  全选
	 * @memberOf {TypeName}
	 */
$("#bxgl_bxtj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='bxgl_bxtj']").prop({
			"checked": true
		});
	} else {
		$("input[id='bxgl_bxtj']").prop({
			"checked": false
		});
	}
})

/**
	 * 未保险公司统计  全选
	 * @memberOf {TypeName}
	 */
$("#bxgl_bxgs_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='bxgl_bxgs']").prop({
			"checked": true
		});
	} else {
		$("input[id='bxgl_bxgs']").prop({
			"checked": false
		});
	}
})

/**
	 * 系统用户  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_xtyh_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_xtyh']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_xtyh']").prop({
			"checked": false
		});
	}
})

/**
	 * 操作日志  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_czrz_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_czrz']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_czrz']").prop({
			"checked": false
		});
	}
})

/**
	 * 通信日志  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_txrz_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_txrz']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_txrz']").prop({
			"checked": false
		});
	}
})

/**
	 * 系统帮助  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_xtbz_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_xtbz']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_xtbz']").prop({
			"checked": false
		});
	}
})

/**
	 * 短信模板  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_dxmb_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_dxmb']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_dxmb']").prop({
			"checked": false
		});
	}
})

/**
	 * 短信提醒  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_dxtx_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_dxtx']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_dxtx']").prop({
			"checked": false
		});
	}
})
/**
	 * 短信权限  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_dxqx_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_dxqx']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_dxqx']").prop({
			"checked": false
		});
	}
})

/**
	 * 短信日志  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_dxrz_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_dxrz']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_dxrz']").prop({
			"checked": false
		});
	}
})

/**
	 * 刷新时间  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_sxsj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_sxsj']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_sxsj']").prop({
			"checked": false
		});
	}
})

/**
	 * 单包流量  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_dbll_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_dbll']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_dbll']").prop({
			"checked": false
		});
	}
})

/**
	 * 报警控制  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_bjkz_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_bjkz']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_bjkz']").prop({
			"checked": false
		});
	}
})

/**
	 * 地理位置  全选
	 * @memberOf {TypeName}
	 */
$("#xtsz_dlwz_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='xtsz_dlwz']").prop({
			"checked": true
		});
	} else {
		$("input[id='xtsz_dlwz']").prop({
			"checked": false
		});
	}
})

/**
	 * 故障区域统计  全选
	 * @memberOf {TypeName}
	 */
$("#tjfx_gzqytj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='tjfx_gzqytj']").prop({
			"checked": true
		});
	} else {
		$("input[id='tjfx_gzqytj']").prop({
			"checked": false
		});
	}
})

/**
	 * 故障类型统计  全选
	 * @memberOf {TypeName}
	 */
$("#tjfx_gzlxtj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='tjfx_gzlxtj']").prop({
			"checked": true
		});
	} else {
		$("input[id='tjfx_gzlxtj']").prop({
			"checked": false
		});
	}
})

/**
	 * 救援区域统计  全选
	 * @memberOf {TypeName}
	 */
$("#tjfx_jyqytj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='tjfx_jyqytj']").prop({
			"checked": true
		});
	} else {
		$("input[id='tjfx_jyqytj']").prop({
			"checked": false
		});
	}
})

/**
	 * 救援响应统计  全选
	 * @memberOf {TypeName}
	 */
$("#tjfx_jyxytj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='tjfx_jyxytj']").prop({
			"checked": true
		});
	} else {
		$("input[id='tjfx_jyxytj']").prop({
			"checked": false
		});
	}
})

/**
	 * 维保区域统计  全选
	 * @memberOf {TypeName}
	 */
$("#tjfx_wbqytj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='tjfx_wbqytj']").prop({
			"checked": true
		});
	} else {
		$("input[id='tjfx_wbqytj']").prop({
			"checked": false
		});
	}
})

/**
	 * 维保出勤统计  全选
	 * @memberOf {TypeName}
	 */
$("#tjfx_wbcqtj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='tjfx_wbcqtj']").prop({
			"checked": true
		});
	} else {
		$("input[id='tjfx_wbcqtj']").prop({
			"checked": false
		});
	}
})

/**
	 *短信统计  全选
	 * @memberOf {TypeName}
	 */
$("#tjfx_dxtj_all").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='tjfx_dxtj']").prop({
			"checked": true
		});
	} else {
		$("input[id='tjfx_dxtj']").prop({
			"checked": false
		});
	}
})
var height = $(window.document).height();
$('#iframepage', window.parent.document).height(height + 40);
