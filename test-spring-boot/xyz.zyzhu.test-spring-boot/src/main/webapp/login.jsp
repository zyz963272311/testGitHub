<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 此文件未jsp文件得公共模版文件，其中包含了头部文件，以及url信息 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title></title>
</head>
<body>
<form action="/user/login" method="post">
	用户名:<input name="username" type="text" placeholder="请输入用户名">
	密&nbsp;&nbsp;码:<input name="password" type="password" placeholder="请输入密码">
	<input type="submit" value="登陆">
</form>
</body>
</html>