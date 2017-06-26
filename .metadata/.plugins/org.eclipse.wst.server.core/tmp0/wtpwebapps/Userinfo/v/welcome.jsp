<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	    <meta http-equiv="refresh" content="4;URL=/Userinfo/heihei/servlet1/DefaultServlet">
		<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

	</head>

	<body>
		<form name=loading>
			<p align=center>
				<font color="#0066ff" size="2" face="Arial">载入中，请稍等...</font>
				<br>
				<input type=text name=chart size=46
					style="font-family: Arial; font-weight: bolder; color: #0066ff; background-color: #fef4d9; padding: 0px; border-style: none;">
				<br>
				<input type=text name=percent size=47
					style="color: #0066ff; text-align: center; border-width: medium; border-style: none;">
				<script> 
var bar=0 
var line="||" 
var amount="||" 
count() 
function count(){ 
bar=bar+2 
amount =amount + line 
document.loading.chart.value=amount 
document.loading.percent.value=bar+"%" 
if (bar<99) 
{setTimeout("count()",100);} 
else 
{window.location = "#";} 
}</script>
			</p>
		</form>

	</body>
</html>
