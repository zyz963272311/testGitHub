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
<title>Insert title here</title>
</head>
<body>
<form action="<%=path %>/file/upload" method="post" enctype="multipart/form-data">  
        选择文件:<input type="file" name="file" width="120px">  
        <input type="submit" value="上传">  
    </form>  
    <hr>  
    <form action="<%=path %>/file/down" method="get">  
        <input type="submit" value="下载">  
    </form>  
</body>
</html>