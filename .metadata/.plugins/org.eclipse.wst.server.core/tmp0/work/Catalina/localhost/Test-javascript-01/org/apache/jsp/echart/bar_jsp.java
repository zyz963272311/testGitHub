/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.72
 * Generated at: 2017-06-21 14:37:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.echart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class bar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(path );
      out.write("/resources/js/echarts.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar num = Math.random();//Math.random()：得到一个0到1之间的随机数\r\n");
      out.write("\tfunction ld(ttl,leg,xa,ser,id)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t//数据\r\n");
      out.write("        // 基于准备好的dom，初始化echarts实例\r\n");
      out.write("        var myChart = echarts.init(document.getElementById(id));\r\n");
      out.write("\r\n");
      out.write("        // 指定图表的配置项和数据\r\n");
      out.write("        var option = {\r\n");
      out.write("            title: {\r\n");
      out.write("                text: ttl\r\n");
      out.write("            },\r\n");
      out.write("            tooltip: {},\r\n");
      out.write("            legend: {\r\n");
      out.write("                data:leg\r\n");
      out.write("            },\r\n");
      out.write("            xAxis: {\r\n");
      out.write("                data: xa\r\n");
      out.write("            },\r\n");
      out.write("            yAxis: {},\r\n");
      out.write("            series: ser\r\n");
      out.write("        };\r\n");
      out.write("\r\n");
      out.write("        // 使用刚指定的配置项和数据显示图表。\r\n");
      out.write("        myChart.setOption(option);\r\n");
      out.write("\t};\r\n");
      out.write("\t//这个是在按钮点击时触发的方法\r\n");
      out.write("\tfunction clk(id)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t//数据\r\n");
      out.write("\t\tvar x=2,y=5;\r\n");
      out.write("\t\tvar title = \"EChart\";\r\n");
      out.write("\t\tvar legend = getLegend(x);\r\n");
      out.write("\t\tvar xAxis=getXAxis(y);\r\n");
      out.write("\t\tvar series = getSeries(x, y, \"bar\");\r\n");
      out.write("\t\tld(title, legend, xAxis, series, id);\r\n");
      out.write("\t};\r\n");
      out.write("\t//说明，这里的legend的每一位必须与series中的name有对应关系，将会自动生成，就是在上面显示分类的东西\r\n");
      out.write("\tfunction getLegend(x)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar legend=[];\r\n");
      out.write("\t\tfor(var i=0;i<x;i++)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tlegend[i]='legend'+i;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn legend;\r\n");
      out.write("\t};\r\n");
      out.write("\t//这里随意指定，仅仅是x坐标的文字\r\n");
      out.write("\tfunction getXAxis(x)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar xAxis=[];\r\n");
      out.write("\t\tfor(var i =0;i<x;i++)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\txAxis[i]=\"xAxis\"+i;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn xAxis;\r\n");
      out.write("\t};\r\n");
      out.write("\t//注意：这里必须要自定义一个类，不可以用字符串类型，否则无法显示出数据\r\n");
      out.write("\tfunction getSeries(x,y,type)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar series=[];\r\n");
      out.write("\t\tfor(var i = 0;i<x;i++)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tvar name=\"legend\"+i;\r\n");
      out.write("\t\t\tvar data=getYData(y);\r\n");
      out.write("\t\t\tseries[i]=Series(name, type, data);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn series;\r\n");
      out.write("\t};\r\n");
      out.write("\t//获取每一种：如name1的全部数据，在这里采用随机数的方式，获得的数据必须全部都是数字类型\r\n");
      out.write("\tfunction getYData(y)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar data=[];\r\n");
      out.write("\t\tfor(var i=0;i<y;i++)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tnum = Math.random();\r\n");
      out.write("\t\t\tdata[i]=Math.ceil(num * 80);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn data;\r\n");
      out.write("\t};\r\n");
      out.write("\t//一个自定义的类\r\n");
      out.write("\tfunction Series(name,type,data)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar series = new Object;\r\n");
      out.write("\t\tseries.name=name;//相当于series下的name\r\n");
      out.write("\t\tseries.type=type;//相当于series下的type\r\n");
      out.write("\t\tseries.data=data;//相当于series下的data，是数字数组\r\n");
      out.write("\t\treturn series;\r\n");
      out.write("\t}\r\n");
      out.write("    </script>\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body >\r\n");
      out.write("<input type=\"button\" onclick=\"clk('main')\" value=\"点击生成柱状图\">\r\n");
      out.write("<div id=\"main\" style=\"width: 600px;height:400px;\"></div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
