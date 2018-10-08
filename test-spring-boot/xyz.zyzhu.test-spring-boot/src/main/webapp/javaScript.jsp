<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
textarea {
	width: 100%;
	overflow:scroll; 
	overflow-x:hidden;
	resize:none;
}

#result {
	width: 100%;
	
}

</style>

<script type="text/javascript" src="/js/jquery/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
	function execJS()
	{
	$("#button_execJS").attr("disabled",true);
	var rst = document.getElementById("result");
	rst.innerHTML = "";
	var xmlhttp;
	var info=document.getElementById("info");
	var infoVal = info.value;
	//%转义
	infoVal = infoVal.replace(/\%/g,"%25");
	//加号转义
	infoVal = infoVal.replace(/\+/g,"%2B");
	//&符号转义
	infoVal = infoVal.replace(/\&/g,"%26");
	//空格转义
	infoVal = infoVal.replace(/\ /g,"%20");
	// ?转义
	infoVal = infoVal.replace(/\?/g,"%3F");
	//=转义
	infoVal = infoVal.replace(/\=/g,"%3D");
	//#转义
	infoVal = infoVal.replace(/\#/g,"%23");
	var data="info="+infoVal;

	
	 $("#button_execJS").attr("disabled",true);
	 $.ajax({
         type: "POST",
         url: "/script/executeJS",
         data: data,
         success: function (data, status) {
        	 rst.innerHTML = data;
         },
         error: function () {
         },
         complete: function () {
        	 $("#button_execJS").attr("disabled",false);
         }

     });
	}
</script>
<title>执行JS代码</title>
</head>
<body>
	<textarea id="info"  rows="20" cols="10" placeholder="请输入"></textarea>
	<br/>
	<center ><button id="button_execJS" style="padding: 10px,0px" type="button" onclick="execJS()">运行</button></center>
	<br/>
	<textarea id="result" rows="20" readonly="readonly" style="border: 1;border-color: black;">没有结果 </textarea>
</body>
</html>