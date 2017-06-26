<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.user.m.db.pojo.Userinfo" %>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShowUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
		<% List l = (List)request.getAttribute("userlist");
		String pageid = (String)request.getAttribute("pageid");
		String pagesum = (String)request.getAttribute("pagesum");%>
<script language="javascript"> 
	function jump(i){                                                       
			document.form1.pageid.value = parseInt(document.form1.pageid.value)+i; 
			}                                                                         
		                                                                         
		 function init(){                         
				var p = document.form1.pageid.value;  
		 		if(p=="1"){                          
				document.form1.s.disabled = true;    
				}                                     
			      if(p =="<%=pagesum%>"){document.form1.x.disabled = true; }                                 
		 	}  
		 	                                                                         
	<%--	function jump(i){                                                         
				document.form1.pageid.value = parseInt(document.form1.pageid.value)+i;  
			}                                                                          
	                                                                         
		function init(){                       
		var p = document.form1.pageid.value;  
			if(p=="1"){                          
		 			document.form1.s.disabled = true;   
				}                                    
			      if(p =="<%=pagesum  %>"){document.form1.x.disabled = true; }              
		 	}                                       
		 --%>
</script> 

  <BODY onload="init();">                                                                                      
<table width="100%" height="80%" align="center" border="1">                      
  <tr>                                                                           
    <th width="9%">编号</th>                                                     
    <th width="16%">姓名</th>                                                    
    <th width="17%">邮箱</th>                                                    
    <th width="17%">权限</th>                                                    
    <th width="31%">签名</th>   
    <th width="10%">操作</th>                                                  
  </tr>                                                                          
  <%  for(int i=0;i<l.size();i++){                                                                  
    Userinfo u = (Userinfo)l.get(i);%> 
    <%if(u.getState()==1){%>                                                                   
 <tr>                                                                         
   <td><%= u.getId()%></td>                                                     
   <td><%=u.getName()%></td>                                                   
   <td><%=u.getMail()%></td>                                                   
   <td><%=u.getPower()%></td>                                                  
  <td><%=u.getGexingqianming()%></td> 
  <td><a href="/Userinfo/hehe/servlet/DropServlet?temp=<%=u.getId() %>" target="mainFrame">删除用户</a>
  		<a href="/Userinfo/hehe/servlet/DropUserServlet?" target="mainFrame">修改用户</a></td> 
                                      
  </tr>    
  <%} %>                                                                     
           <%} %>                         
</table>                                                                         
<div align="center">                                                             
<form name="form1" method="post" action="/Userinfo/hehe/servlet/DropUserServlet">   
                                                                                     
  <input type="submit" name="s" value="上一页" onClick="jump(-1);">                     
  <input type="text" name="pageid" size="8" value="<%=pageid%>" readonly="true">     
  <input type="submit" name="x" value="下一页" onClick="jump(1);">                  
</form>                                                                                  
</div>                                                                                                  
                                                                                           
  </BODY>    
</html>
