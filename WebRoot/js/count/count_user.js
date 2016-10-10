$(".select").click(function() {
	var a = $(this).attr("data");
	if(a == 50) {
		$(this).attr("data", "60");
		$(this).find("i").css({
			"backgroundImage": 'url(../../img/up1.png)'
		});
	} else {
		$(this).attr("data", "50")
		$(this).find("i").css({
			"backgroundImage": 'url(../../img/down2.png)'
		});
	}
})
require.config({
	paths: {
		echarts: '../../js/echarts/build/dist'
	}
});

// 使用
require(
	[
		'echarts',
		'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
	],
	function(ec) {
		// 基于准备好的dom，初始化echarts图表
		var myChart = ec.init(document.getElementById('main'));
		//设置数据
		var option = {
			title: {
				text: '各维保单位平均无故障运行时间对比图'
					//						subtext: '纯属虚构'
			},
			tooltip: {
				trigger: 'axis'
			},
			//设置坐标轴
			xAxis: [{
				type: 'category',
				data: ["单位1", "单位2", "单位3", "单位4", "单位5", "单位6", "单位7", "单位8", "单位9", "单位10"]
			}],
			yAxis: [{
				type: 'value'
			}],
			//设置数据
			series: [{
				"name": "小时",
				"type": "bar",
				"data": [5, 20, 40, 10, 24, 20, 24, 32, 60],
			}]
		};

		// 为echarts对象加载数据 
		myChart.setOption(option);
	}
);