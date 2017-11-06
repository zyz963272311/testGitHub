package com.testweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sheng extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String provincevalue = request.getParameter("sheng");
		System.out.println("省份编号：" + provincevalue);
		List<Map<String, String>> shis = new ArrayList<>();
		if ("beijing".equals(provincevalue)) {
			Map<String, String> shi = new HashMap<String, String>();
			shi.put("id", "001");
			shi.put("value", "beijing");
			shi.put("show", "北京");
			shis.add(shi);
		} else if ("shanghai".equals(provincevalue)) {
			Map<String, String> shi = new HashMap<String, String>();
			shi.put("id", "001");
			shi.put("value", "shanghai");
			shi.put("show", "上海");
			shis.add(shi);
		} else if ("guangdong".equals(provincevalue)) {
			Map<String, String> guangzhou = new HashMap<String, String>();
			guangzhou.put("id", "001");
			guangzhou.put("value", "guangzhou");
			guangzhou.put("show", "广州");
			shis.add(guangzhou);
			Map<String, String> shenzhen = new HashMap<String, String>();
			shenzhen.put("id", "002");
			shenzhen.put("value", "shenzhen");
			shenzhen.put("show", "深圳");
			shis.add(shenzhen);
			Map<String, String> zhongshan = new HashMap<String, String>();
			zhongshan.put("id", "003");
			zhongshan.put("value", "zhongshan");
			zhongshan.put("show", "中山");
			shis.add(zhongshan);
		}

		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<city_info>");
		for (Map<String, String> c : shis) {
			xml.append("<city>");
			xml.append("<id>" + c.get("id") + "</id>");
			xml.append("<value>" + c.get("value") + "</value>");
			xml.append("<show>" + c.get("show") + "</show>");
			xml.append("</city>");
		}
		xml.append("</city_info>");
		// 设置响应字符集编码，防止中文乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		// 将xml文档写出去
		PrintWriter writer = response.getWriter();
		// 因为只能写字符串，所以toString
		writer.write(xml.toString());
		writer.flush();
		writer.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
