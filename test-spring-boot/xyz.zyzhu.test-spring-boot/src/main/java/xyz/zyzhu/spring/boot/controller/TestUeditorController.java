package xyz.zyzhu.spring.boot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.baidu.ueditor.ActionEnter;
/**
 * <p>标题： 测试ueditorcontroller</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午12:31:52</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.TestUeditorController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Controller()
public class TestUeditorController
{
	@RequestMapping("/js/ueditor/testUeditor")
	public ModelAndView ueditor()
	{
		ModelAndView mav = new ModelAndView("testUeditor");
		return mav;
	}

	@RequestMapping("/js/ueditor/config")
	public void config(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("application/json");
		//		String rootPath = request.getSession().getServletContext().getRealPath("/");
		try
		{
			String rootPath = this.getClass().getResource("/static").toURI().getPath();
			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException | URISyntaxException e)
		{
			e.printStackTrace();
		}
	}
}
