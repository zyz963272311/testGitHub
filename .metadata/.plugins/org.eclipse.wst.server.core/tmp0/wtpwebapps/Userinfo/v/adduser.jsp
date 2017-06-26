<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adduser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script language="javascript">
		function  mycheck(){
			var n = document.form1.tu.value;
			if(n.length == 0){
				mytu.innerHTML = "<font color='red'>用户名不允许为空</font>";
				document.form1.tu.select();
				return false;
			} 
			var p = document.form1.tp.value;
			if(p.length<6 || p.length>15){
				mytp.innerHTML = "<font color='red'>密码不能小于6位或大于15位</font>";
				document.form1.tp.select();
				return false;
			}
			var p2= document.form1.tpr.value;
			if(p != p2){
				mytpr.innerHTML = "<font color='red'>两次输入不一致请重新输入</font>";
				document.form1.tpr.select();
				return false;
			
			}
			var age = document.form1.ta.value;
			var i = age.length;
			age = parseInt(age);
			if(age <0 || age >145 || i == 0){
			    myta.innerHTML = "<font color='red'>年龄输入不正确，请重新输入</font>";
				document.form1.ta.select();
				return false;
			}
			var mail = document.form1.tm.value;
			var j = mail.indexOf('@');
			var k = mail.lastIndexOf('@');
			var h = mail.indexOf('.');
			var g = mail.lastIndexOf('.');
			if(j == -1 || j != k || h ==-1 || h < k || j==0 || g==mail.length-1 ){
				mytm.innerHTML = "<font color='red'>邮箱输入不正确，请重新输入</font>";
				document.form1.tm.select();
				return false;
			
			}

			var tc  = document.form1.c;
			var b = false;
			for(var t=0;t<tc.length;t++){
				if(tc[t].checked == true){
					b = true;
					break;
				
				}
			
			}		 
			
			if(b == false){
				myc.innerHTML = "<font color='red'>爱好至少选一项</font>";
				return false;
			}
			
			var mcity = document.form1.city.value;

			if(mcity == "null"){
				mycity.innerHTML = "<font color='red'>城市不能使用默认值</font>";
				return false;
			}
			
			var mtt = document.form1.tt.value;
			if(mtt.length < 15 ){
				mytt.innerHTML = "<font color='red'>个性签名不能少于15个字</font>";
				return false
			}
			
			
			return true;
		}
		//---------------------------------
		
		function  mytucheck(){
		    var n = document.form1.tu.value;
		    if(n.length > 0){
				mytu.innerHTML = "";
			} 
		}
		function  mytpcheck(){
		   var p = document.form1.tp.value;
			if(p.length>6 && p.length<15){
				mytp.innerHTML = "";
			}
		}
		function  mytprcheck(){
		   var p2 = document.form1.tpr.value;
		    var p = document.form1.tp.value;
			if(p == p2){
				mytp.innerHTML = "";
			}
		}
		function mytacheck(){
			var age = document.form1.ta.value;
			var i = age.length;
			age = parseInt(age);
			if(age >0 || age <145 || i != 0){
			    myta.innerHTML = "";
				
			}
		
		}
		
		function mytmcheck(){
			var mail = document.form1.tm.value;
			var j = mail.indexOf('@');
			var k = mail.lastIndexOf('@');
			var h = mail.indexOf('.');
			var g = mail.lastIndexOf('.');
			if(j !=-1 && j==k && h!=-1 && h>j && j !=0 && g != mail.length-1){
			
				mytm.innerHTML = "";
			}
		}
		
		function myccheck(){
			var tc  = document.form1.c;
				var b = false;
				for(var t=0;t<tc.length;t++){
					if(tc[t].checked == true){
						b = true;
						break;
					
					}
				
				}		 
				if(b){
					myc.innerHTML = "";
				}
		}
	
	    function mycitycheck(){
			var mcity = document.form1.city.value;
			if(mcity != "---请选着---"){
				mycity.innerHTML = "";
			}
		}
		
		function myttcheck(){
			var mtt = document.form1.tt.value;
			if(mtt.length >15 ){
				mytt.innerHTML = "<font color='red'></font>";
			}
		}
</script>
  </head>
  
  <body>
   <form name="form1" method="post" action="/Userinfo/heihei/servlet1/AddUserServlet" onSubmit="return mycheck();">
<table width="100%" height="100%" >
  <tr>
    <td width="16%"><div align="right">用&nbsp;&nbsp;&nbsp;&nbsp;户</div></td>
    <td width="25%"><input type="text" name="tu" onBlur="mytucheck();"></td>
    <td width="59%" id="mytu">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码</div></td>
    <td><input type="password" name="tp" onBlur="mytpcheck();"></td>
    <td id="mytp">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right">密码确认</div></td>
    <td><input type="password" name="tpr" onBlur="mytprcheck();"></td>
    <td id="mytpr">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right">年&nbsp;&nbsp;&nbsp;&nbsp;龄</div></td>
    <td><input type="text" name="ta" onBlur="mytacheck();"></td>
    <td id="myta">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right">邮&nbsp;&nbsp;&nbsp;&nbsp;箱</div></td>
    <td><input type="text" name="tm" onBlur="mytmcheck();"></td>
    <td id="mytm">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right">性&nbsp;&nbsp;&nbsp;&nbsp;别</div></td>
    <td><input type="radio" name="r" value="男" checked>男
	<input type="radio" name="r"  value="女">女
	</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right">爱&nbsp;&nbsp;&nbsp;&nbsp;好</div></td>
    <td><input type="checkbox" name="c" value="看书" onclick="myccheck();">看书
	<input type="checkbox" name="c" value="看报" onclick="myccheck();">看报
	<input type="checkbox" name="c" value="看电影" onclick="myccheck();">看电影
	<input type="checkbox" name="c" value="看电视" onclick="myccheck();">看电视</td>
    <td id="myc">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right">城&nbsp;&nbsp;&nbsp;&nbsp;市</div></td>
    <td><select name="city"  onChange="mycitycheck();">
      <option value="null">---请选着---</option>
      <option value="沈阳">沈阳</option>
      <option value="大连">大连</option>
      <option value="鞍山">鞍山</option>
      <option value="抚顺">抚顺</option>
    </select></td>
    <td id="mycity">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right"> 个性签名</div></td>
    <td><textarea name="tt" onpropertyChange="myttcheck();" ></textarea></td>
    <td id="mytt">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right"></div></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right"></div></td>
    <td><input type="submit" name="Submit" value="注册"></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right"></div></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
  </body>
</html>
