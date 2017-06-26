<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 <frameset rows="80,*,80"  border="1" framespacing="0">
  <frame src="/Userinfo/v/ZhuYe.jsp" name="topFrame"  scrolling="NO" noresize>
  <frame src="/Userinfo/v/welcome2.jsp" name="mainFrame" scrolling="NO" noresize>
  <frame src="/Userinfo/v/foot.jsp" name="bottomFrame"   scrolling="NO" noresize>
</frameset>
<noframes><body>
</body></noframes>
</html>
