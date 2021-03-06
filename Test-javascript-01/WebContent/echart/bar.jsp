<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=path %>/resources/js/echarts.min.js"></script>
<script type="text/javascript">
	var num = Math.random();//Math.random()：得到一个0到1之间的随机数
	var nm = "tyghjkl;hjkl';kj';kj';kjds;lakd;lsakd;lsa";
	var myChart;
	//在界面加载的时候初始化echarts
	function initECharts(id)
	{
		myChart= echarts.init(document.getElementById(id));
	};
	function ld(ttl,leg,xa,ser,id)
	{
		//数据
        // 基于准备好的dom，初始化echarts实例

        // 指定图表的配置项和数据
        var option = {
            title: {
            	//设置标题
                text: ttl,
                //设置是否显示标题
                show:true,
                //设置超链接，默认为""
                //link:"http://www.baidu.com",
                //在什么位置打开超链接 self：当前窗口；blank：新窗口
                //target:"blank",
                //设置标题的样式
                textStyle:{
                	//设置标题的颜色 默认#333
                	color:'#ff9900',
                	//设置文字样式，可选项：normal，italic，oblique
                	fontStyle:'italic',
                	//设置文字的宽度可选normal，bold，bolder，lighter，100 | 200 | 300 | 400...
                	fontWeight:'lighter',
                	//设置字体主题
                	fontFamily:'sans-serif',
                	//设置字体大小
                	fontSize:30
                },
				//设置标题的水平对齐方式,支持left，center，right不太懂，感觉是图相对于文字的位置
				//textAlign:'left',
				//设置标题的垂直对齐，支持top，middle，bottom，没测试通过
				textBaseLine:'bottom',
				//设置副标题，支持\n换行，不是自适应的，需要自己调整位置
				subtext:'测试副标题\n换行\n换行\n换行测试副标题',
				//设置副标题的超链接
				sublink:"http://www.baidu.com",
				//设置指定窗口打开超链接,与target相同
				subtarget:'blank',
				//设置副标题样式，与textStyle相同
				subtextStyle:{
					//默认#aaa
					color:'#000'
				},
				//设置内边距，默认5[,5,5,5]    有如下两种设置方式
				//padding:5,
				padding:[5,10,15,20,],
				//设置主标题与副标题之间的间距,默认10
				itemGap:20,
				//所有图形的zlevel值,默认0
				//用于 Canvas 分层，不同zlevel值的图形会放置在不同的 Canvas 中，Canvas 分层是一种常见的优化手段。
				//我们可以把一些图形变化频繁（例如有动画）的组件设置成一个单独的zlevel。需要注意的是过多的 Canvas 
				//会引起内存开销的增大，在手机端上需要谨慎使用以防崩溃。
				//大的 Canvas 会放在 zlevel 小的 Canvas 的上面。
// 				zlevel:0,
				//组件的所有z值，默认2 控制图形的先后顺序，z值小的会被z值大的覆盖，
				//z相比与zlevel的优先级更低，而且不会创建新的canvas
// 				z:2,
// 				//grid组件距离容器左侧的距离,绝对距离20,相对距离20%，对齐left,center, right
// 				left:'auto',
// 				//grid组件距离容器左侧的距离,绝对距离20,相对距离20%，对齐top,middle, bottom
// 				top:'auto',
// 				//grid组件距离容器左侧的距离,默认auto,绝对距离20,相对距离20%，对齐left,center, right
// 				right:'auto',
// 				//grid组件距离容器左侧的距离,默认auto,绝对距离20,相对距离20%，对齐left,center, right
// 				bottom:'auto',
// 				//标题背景颜色，默认透明,rgba,a:0~1透明度减小，0位全透明，1为全不透明
// 				backgroundColor:'rgba(255,125,0,0)',
// 				//标题边框颜色，默认透明,与backgroundColor支持相同
// 				borderColor:'rgba(255,125,0,0)',
// 				//设置标题的边框线宽度，默认0
// 				borderWidth:1,
				//图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
				shadowBlur:3,
				//阴影颜色。支持的格式同color。
				shadowColor:'rgba(255,99,0,0)',
				//X平偏移量，默认0
				shadowOffsetX:2,
				//y平偏移量，默认0
				shadowOffsetY:0
            },
            legend: {
            	//在使用的时候，请把下面的注释去掉，使用默认的只有名称的显示
                //data:leg,
                //是否显示,默认显示,如果为false则不显示上边的多个标题
                show:true,
                //不懂，默认0
                zlevel:0,
                //不懂，默认2
                z:2,
                //距离容器左边框的距离
//                 left:'auto',
//                 //距离容器上边框的距离
//                 top:'auto',
//                 //距离容器右边框的距离
//                 right:'auto',
//                 //距离容器下边框的距离
//                 bottom:'auto',
//                 //图例组件的宽度。默认自适应。
//                 width:'auto',
//                 //图例组件的高度。默认自适应。
//                 height:'auto',
                //设置布局朝向，默认垂直,可选horizontal，vertical
                orient:'vertical',
                //图例列表的布局朝向可选 小图标相对于文字的方向auto ，left，right
                align:'left',
                //图标的内边距 上右下左
                padding:[10,50,10,10,],
                //设置每个选项之间的间距 默认10
                itemGap:20,
                //每个小图标的宽度 默认25
                itemWidth:40,
                //每个小图标的高度 默认14
                itemHeight:10,
                //用来格式化图标的文本,支持{}和回调方法
                formatter:function () {
                    //参数：需要转换的【文字】，【总宽度】，【文字每个宽度样式】，【超出部分的显示样式】
        			return echarts.format.truncateText(nm, 40, '14px Microsoft Yahei', '~~');
    			},
                //formatter:'dghfkjdfhkljfashjdsjafkldjskl',
                //图例选择的模式，控制是否可以通过点击图例改变系列的显示状态。默认开启图例选择，可以设成 false 关闭。
				//除此之外也可以设成 'single' 或者 'multiple' 使用单选或者多选模式。
                selectedMode:true,
                //图标关闭时的图标颜色，默认#ccc
                inactiveColor:'#ff9900',
                //图标的选中状态表{data[0]:bool,data[1]:bool},默认true
                selected:{'legend0':true,'legend1':true},
                //图例的公用文本样式。上面都有
                textStyle:{
                    //颜色,默认#333,当被选中时文字的颜色
                    color:'#00ff00',
                    //文字的样式,同上面的fontStyle
                    fontStyle:'noormal'
                },
                //显示提示框，比如当文字很长的情况下，可以使用这个配置来悬浮显示全部文字，默认开启
                //详细信息参考后面的tooltip
                tooltip:{
                    show:true
                },
                //图例的数据数组。数组项通常为一个字符串，每一项代表一个系列的 name（如果是饼图，也可以是饼图单个数据的 name）。图例组件会自动根据对应系列的图形标记（symbol）来绘制自己的颜色和标记，特殊字符串 ''（空字符串）或者 '\n'（换行字符串）用于图例的换行。
				//如果要设置单独一项的样式，也可以把该项写成配置项对象。此时必须使用 name 属性对应表示系列的 name。
				data:[{
						 name: 'legend0',
					    // 强制设置图形为圆。
					    icon: 'circle',
					    // 设置文本为红色
					    textStyle: {
					        color: 'red'
						   }
					},
					{
						 name: 'legend1',
					    // 强制设置图形为圆。
					    icon: 'circle',
					    // 设置文本为红色
					    textStyle: {
					        color: 'blue'
						}
					}
				],
				//背景颜色，同上面的北京颜色，默认透明
				backgroundColor:'rgba(255,255,255,1)'
				//borderColor,borderWidth,shadowBlur,shadowColor,shadowOffsetX,shadowOffsetY,同上
				
            },
            tooltip: {},
            xAxis: {
                data: xa
            },
            yAxis: {},
            series: ser
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	};
	//这个是在按钮点击时触发的方法
	function clk(id)
	{
		//数据
		var x=2,y=5;
		var title = "EChart";
		var legend = getLegend(x);
		var xAxis=getXAxis(y);
		var series = getSeries(x, y, "bar");
		ld(title, legend, xAxis, series, id);
	};
	//说明，这里的legend的每一位必须与series中的name有对应关系，将会自动生成，就是在上面显示分类的东西
	function getLegend(x)
	{
		var legend=[];
		for(var i=0;i<x;i++)
		{
			legend[i]='legend'+i;
		}
		return legend;
	};
	//这里随意指定，仅仅是x坐标的文字
	function getXAxis(x)
	{
		var xAxis=[];
		for(var i =0;i<x;i++)
		{
			xAxis[i]="xAxis"+i;
		}
		return xAxis;
	};
	//注意：这里必须要自定义一个类，不可以用字符串类型，否则无法显示出数据
	function getSeries(x,y,type)
	{
		var series=[];
		for(var i = 0;i<x;i++)
		{
			var name="legend"+i;
			var data=getYData(y);
			series[i]=Series(name, type, data);
		}
		return series;
	};
	//获取每一种：如name1的全部数据，在这里采用随机数的方式，获得的数据必须全部都是数字类型
	function getYData(y)
	{
		var data=[];
		for(var i=0;i<y;i++)
		{
			num = Math.random();
			data[i]=Math.ceil(num * 80);
		}
		return data;
	};
	//一个自定义的类
	function Series(name,type,data)
	{
		var series = new Object;
		series.name=name;//相当于series下的name
		series.type=type;//相当于series下的type
		series.data=data;//相当于series下的data，是数字数组
		return series;
	};
	//重新设置大小
	function resize(id)
	{
		if(myChart)
		{
			var option = myChart.getOption();
			myChart.resize(option,100,100);
		}
	};
	function setColor(id)
	{
		if(myChart)
		{
// 			myChart.setOption(
// 				{
// 					visualMap:
// 					{
// 						color:#ff9900;
// 					}
// 				}
// 			);
		}
	}
    </script>
<title>Insert title here</title>
</head>
<body onload="initECharts('main')">
<input type="button" onclick="clk('main')" value="点击生成柱状图">
<br/>
<label>更改样式</label>
<br/>
<input type="button" onclick="resize('main')" value="改变柱状图大小">
<input type="button" onclick="setColor('main')" value="改变柱状图颜色">
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>