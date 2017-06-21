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
	function ld(ttl,leg,xa,ser,id)
	{
		//数据
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById(id));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: ttl
            },
            tooltip: {},
            legend: {
                data:leg
            },
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
	}
    </script>
<title>Insert title here</title>
</head>
<body >
<input type="button" onclick="clk('main')" value="点击生成柱状图">
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>