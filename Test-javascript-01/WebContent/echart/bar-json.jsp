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
var json='{"data":{"title":"EChart-Json","legend":["legend1"],"xAxis":["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋"],"series":[{"name":"legend1","type":"bar","data":[12,34,56,78,90]}]}}';
var json1='{"data":{"title":"EChart-Json","legend":["legend1","legend2"],"xAxis":["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋"],"series":[{"name":"legend1","type":"bar","data":[12,34,56,78,90]},{"name":"legend2","type":"bar","data":[21,43,65,87,99]}]}}';
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
        myChart.setOption(option,true);
	};
	//这个是在按钮点击时触发的方法
	function clk(id,j)
	{
		//数据
		var js;
		if(j == 0)
		{
			js = json;
		}
		else if(j==1)
		{
			js=json1;
		}
		var jsonData = eval("("+js+")");
		title=jsonData.data.title;
		legend=jsonData.data.legend;
		xAxis=jsonData.data.xAxis;
		series=jsonData.data.series;
		ld(title, legend, xAxis, series, id);
	};
    </script>
<title>Insert title here</title>
</head>
<body >
<input type="button" onclick="clk('main',0)" value="点击根据json柱状图">
<input type="button" onclick="clk('main',1)" value="点击根据json1生成柱状图">
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>