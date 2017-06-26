<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function jump(){
			location.href="/Userinfo/v/adduser.jsp";
		}
	</script>
  </head>
  
  <body>
    <form name = "form1" method ="post" action="/Userinfo/heihei/servlet1/UserServlet">
       用户：<input type="text" name ="haha"> <br>
       密码：<input type="password" name ="hehe"> <br>
        <input type="submit" value ="登录" >
         <input type="button" value ="注册" onclick="jump();">
    </form>
  </body>
</html>
