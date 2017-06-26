package com.zhu.ssm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月26日 下午4:51:45</p>
 * <p>类全名：com.zhu.ssm.controller.FileController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@RequestMapping("file")
@Controller
public class FileController
{
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest request) throws IOException
	{
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		if (fileName == null || fileName.length() == 0)
		{
			return "ok";
		}
		File dir = new File(path, fileName);
		if (!dir.exists())
		{
			dir.mkdir();
		}
		file.transferTo(dir);
		return "ok";
	}

	@RequestMapping(value = "/down")
	public String down(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String fileName = request.getSession().getServletContext().getRealPath("upload") + "/mysql-connector-java-5.1.22.zip";
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
		String filename = "mysql-connector-java-5.1.22.zip";
		filename = URLEncoder.encode(filename, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		byte[] array = new byte[1024];
		while ((len = bis.read(array)) != -1)
		{
			out.write(array, 0, len);
			out.flush();
		}
		bis.close();
		out.close();
		return null;
	}
}
