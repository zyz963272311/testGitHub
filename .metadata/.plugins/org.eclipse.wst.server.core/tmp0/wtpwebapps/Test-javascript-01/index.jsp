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
<title>index.jsp</title>
	<script type="text/javascript">
	function getA() {
		var d = document.getElementById("baidu");
		d.style.background="#ff9900";
		d.setAttribute("target","_blank");
		window.open(d.href);
	}
	</script>
</head>
<body>
	<a href = "http://www.baidu.com" id="baidu">百度</a>
	<input type="button" value="点击获取a标签" onclick="getA()">
	<a href="echart/bar.jsp">echart/柱状图</a>
	<a href="echart/bar-json.jsp">echart-json/柱状图</a>
</body>
</html>