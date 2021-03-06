<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>ajax实现二级联动</title>
<script type="text/javascript">
		var xmlhttp;
	function change(value) {
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("POST", "<%=basePath%>Sheng", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.onreadystatechange = back_change;
		xmlhttp.send("sheng=" + value);
	}
	function back_change() {
		//城市下拉选择框的对象
		var cityobj = document.getElementById("shi");
		//当请求状态等于4时，相应已完成，可以访问服务器响应并使用它
		if (xmlhttp.readyState == 4) {
			//当状态为200时意味着，状态正常，未出错
			if (xmlhttp.status == 200) {
				//获取相应的xml文档
				var cityxml = xmlhttp.responseXML;
				//获取根元素
				var root = cityxml.documentElement;
				//获取根元素（city_info）下面的所有city元素
				var cities = root.getElementsByTagName("city");
				//将下拉框内容清除
				cityobj.options.length = 1;
				for (var i = 0; i < cities.length; i++) {
					var city = cities[i];
					//获取节点的值
					var cid = city.childNodes[0].firstChild.nodeValue;
					var cname = city.childNodes[2].firstChild.nodeValue;
					//alert(cid+" "+cname);
					//放到下拉选择框里Option(文本内容,value值);
					cityobj.options[cityobj.options.length] = new Option(cname,
							cid);
				}
			}
			//当状态为404时，表示找不到页面
			else if (xhr.status == 404) {
				alert("Request URL is not exists!");
			} else {
				alert("Error:Status is:" + request.status);
			}
		}
	}
	function changeShi(value)
	{
		alert(value);
	}
</script>
</head>
<body>
	省：
	<select id="sheng" onchange="change(this.value)">
		<option id="001" value="beijing">北京</option>
		<option id="002" value="shanghai">上海</option>
		<option id="003" value="guangdong">广东</option>
	</select> &nbsp;&nbsp;&nbsp;&nbsp; 市：
	<select id="shi" onchange="changeShi(this.value)">
		<option id="000" value="qingxuanze">---请选择---</option>
	</select>
</body>
</html>