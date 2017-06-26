<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.user.m.db.pojo.Userinfo"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ZhuYe.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%!     static int sum = 1;%>
		<%
		if(sum < 214736){
			sum++;
		}else{
			sum =0;
		}
		 %> 
		 <%
		Userinfo u =(Userinfo)session.getAttribute("loginuser");//应该获取UserServlet中的u对象（存储了登录者的信息） 
		%>

  <BODY>
    <%=u.getName() %>您好！<br>
您的权限是： <%= u.getPower()%><br>
<a href="/Userinfo/v/welcome2.jsp" target="mainFrame">首页</a>
    <%  if(u.getPower().equals("管理员")){
			           //合成管理员页面%>
		
		<a href="/Userinfo/v/adduser.jsp" target="mainFrame">添加用户</a>
		<a href="/Userinfo/hehe/servlet/DropUserServlet?pageid=1" target="mainFrame">管理用户</a>

		
		<%}else{ %>
			<%//合成普通用户页面 %>
			
		<a href="#" target="mainFrame">查询个人信息
		<a href="#" target="mainFrame">修改个人信息
		
		<%} %>
<a href="/Userinfo/heihei/servlet/ExitServlet?haha=<%=sum %>" target="_parent">注销</a>
  </BODY>  
 

</html>
