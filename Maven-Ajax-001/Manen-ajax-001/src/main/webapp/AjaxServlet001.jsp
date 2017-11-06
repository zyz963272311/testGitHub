<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#Ajax").click(function() {
			var jsonStr = $.ajax({
				type:"post",
				url:"<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/DownloadSanDiskSecureAccess_Mac.pdf&_downloadFlags=1",
				async:false
			}).responseText;
			var json = eval("("+jsonStr+")")
			$.each(json,function(idx,item){
				//alert("id:"+idx+",name:"+item)
				$("#message").append("<div>"+idx+":"+item+"</div><hr/>");
			})
		});
	});
</script>
</head>
<body>

	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/DownloadSanDiskSecureAccess_Mac.pdf&_downloadFlags=2">AjaxServlet001</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/DownloadSanDiskSecureAccess_Mac.pdf&_downloadFlags=1" target="_blank">AjaxServlet001</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/ftp.txt&_downloadFlags=1" target="_blank">ftp.txt</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/pom.xml&_downloadFlags=1" target="_blank">pom.xml</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/bd_logo1.png&_downloadFlags=1" target="_blank">bd_logo1.png</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/bgm_track1_loop.mp3&_downloadFlags=1" target="_blank">bgm_track1_loop.mp3</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/Android.pptx&_downloadFlags=1" target="_blank">Android.mht</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/sdfghjk.doc&_downloadFlags=1" target="_blank">sdfghjk.doc</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/gggg.xlsx&_downloadFlags=1" target="_blank">gggg.xlsx</a>
		<!-- 分隔符 -->
		<br>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/ftp.txt&_downloadFlags=2" target="_blank">ftp.txt</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/pom.xml&_downloadFlags=2" target="_blank">pom.xml</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/bd_logo1.png&_downloadFlags=2" target="_blank">bd_logo1.png</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/bgm_track1_loop.mp3&_downloadFlags=2" target="_blank">bgm_track1_loop.mp3</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/Android.mht&_downloadFlags=2" target="_blank">Android.mht</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/sdfghjk.doc&_downloadFlags=2" target="_blank">sdfghjk.doc</a>
	<a
		href="<%=this.getServletContext().getContextPath()%>/AjaxServlet001?filePath=file/gggg.xlsx&_downloadFlags=2" target="_blank">gggg.xlsx</a>
		<!-- 分隔符 -->
	<input type="text" id="id">
	<button id="Ajax">Ajax</button>
	<div id="message" style="width: auto; height: 100px;"></div>
</body>
</html>