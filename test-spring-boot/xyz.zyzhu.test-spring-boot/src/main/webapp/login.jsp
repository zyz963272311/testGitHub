<%@ include file="WEB-INF/jsp/comm/comm.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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