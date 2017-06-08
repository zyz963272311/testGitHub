<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function deleteUsr(dom)
	{
		var url = dom.href;
		var userid = document.getElementById("deleteUserId").value;
		if(userid == undefined||userid=="")
		{
			throw "userid不可为空";
		}
		url=url+userid;
		alert(url);
		dom.href = url;
	}
</script>
</head>
<body>
 我告诉你我是首页
<br>
<a href="<%=basePath %>user/list?pageNumber=1&pageSize=1">获取分页用户</a>
<br>
<a href="<%=basePath %>user/insert?username=zyz">添加用户zyz</a>
<br>
<a href="<%=basePath %>user/delete?userid=" onclick="deleteUsr(this)">删除用户</a><input id="deleteUserId"/>
<br>
<a href="<%=basePath %>goods/findGoods?pageNumber=1&pageSize=1&price=12">获取分页商品</a>
<br>
<a href="<%=basePath %>order/findOrder?pageNumber=1&pageSize=2">获取分页订单</a>
</body>
</html>