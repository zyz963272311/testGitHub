<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
</head>
<body>
总记录数:<label>${total }</label><br/>
当前页面记录数:<label>${size }</label><br/>
总页数:<label>${pages }</label><br/>
<c:forEach var="menu" items="${menuList}">
	 <c:out value="菜单"></c:out>
	 <c:out value="${menu.mid}" /><br/>
	 <c:out value="${menu.mname}" /><br/>
	 <c:out value="${menu.offlags}" /><br/>
	 <c:out value="${menu.url}" /><br/>
	 <c:out value="${menu.limits}" /><br/>
</c:forEach>
</body>
</html>