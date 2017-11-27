<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<script type="text/javascript">
	function execJS()
	{
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

	//Mozilla ,chmore浏览器（将XMLHttpRequest对象作为本地浏览器对象来创建）  
	if (window.XMLHttpRequest) { //Mozilla 浏览器  
		xmlhttp = new XMLHttpRequest();

	} else if (window.ActiveXObject) { //IE浏览器  
		//IE浏览器（将XMLHttpRequest对象作为ActiveX对象来创建）  
		try {
			xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
	var Url = "<%=basePath%>script/executeJS";
		xmlhttp.open("POST", Url, true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.onreadystatechange = result;
		xmlhttp.send(data);

		function result() {
			if (xmlhttp.readyState == 4) {
				var response = xmlhttp.responseText;
				var rst = document.getElementById("result");
				rst.innerHTML = response;
			}
		}
	}
</script>
<title>执行JS代码</title>
</head>
<body>
	<textarea id="info"  rows="20" cols="10" placeholder="请输入"></textarea>
	<br/>
	<center ><button style="padding: 10px,0px" type="button" onclick="execJS()">运行</button></center>
	<br/>
	<textarea id="result" rows="20" readonly="readonly" style="border: 1;border-color: black;">没有结果 </textarea>
</body>
</html>