/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.72
 * Generated at: 2017-06-23 16:16:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.v;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.user.m.db.pojo.Userinfo;

public final class ZhuYe_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

     static int sum = 1;
  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \r\n");
      out.write("    <title>My JSP 'ZhuYe.jsp' starting page</title>\r\n");
      out.write("    \r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t<!--\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\r\n");
      out.write("\t-->\r\n");
      out.write("\r\n");
      out.write("\t\t");

		if(sum < 214736){
			sum++;
		}else{
			sum =0;
		}
		 
      out.write(" \r\n");
      out.write("\t\t ");

		Userinfo u =(Userinfo)session.getAttribute("loginuser");//应该获取UserServlet中的u对象（存储了登录者的信息） 
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("  <BODY>\r\n");
      out.write("    ");
      out.print(u.getName() );
      out.write("您好！<br>\r\n");
      out.write("您的权限是： ");
      out.print( u.getPower());
      out.write("<br>\r\n");
      out.write("<a href=\"/Userinfo/v/welcome2.jsp\" target=\"mainFrame\">首页</a>\r\n");
      out.write("    ");
  if(u.getPower().equals("管理员")){
			           //合成管理员页面
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<a href=\"/Userinfo/v/adduser.jsp\" target=\"mainFrame\">添加用户</a>\r\n");
      out.write("\t\t<a href=\"/Userinfo/hehe/servlet/DropUserServlet?pageid=1\" target=\"mainFrame\">管理用户</a>\r\n");
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
}else{ 
      out.write("\r\n");
      out.write("\t\t\t");
//合成普通用户页面 
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t<a href=\"#\" target=\"mainFrame\">查询个人信息\r\n");
      out.write("\t\t<a href=\"#\" target=\"mainFrame\">修改个人信息\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
} 
      out.write("\r\n");
      out.write("<a href=\"/Userinfo/heihei/servlet/ExitServlet?haha=");
      out.print(sum );
      out.write("\" target=\"_parent\">注销</a>\r\n");
      out.write("  </BODY>  \r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
