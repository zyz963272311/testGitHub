package xyz.zyzhu.spring.boot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.liiwin.utils.StrUtil;
import com.liiwin.utils.tac.Tac;
import com.liiwin.utils.tac.WriterWaper;
import xyz.zyzhu.spring.boot.tac.BootTac;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月18日 下午12:11:57</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.ScriptController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/script")
public class ScriptController
{
	private static Logger logger = LoggerFactory.getLogger(ScriptController.class);

	@RequestMapping(path = "/executeJS", method = { RequestMethod.GET, RequestMethod.POST })
	public String execte(HttpServletRequest request, HttpServletResponse response, @RequestParam("info") String info)
	{
		PrintWriter writer = null;
		try
		{
			writer = response.getWriter();
			if (StrUtil.isStrTrimNull(info))
			{
				return "无可执行TAC";
			}
			logger.error("执行TAC代码" + info);
			Tac tac = new BootTac("js", new WriterWaper(writer));
			List<Object> resultList = tac.execute(info);
			String result = buildListToStr(resultList);
			return StrUtil.obj2str(result, "执行完成");
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				return e.getMessage() + "\n" + new BootTac("js", new WriterWaper(writer)).printlnArray(e.getStackTrace());
			} catch (IOException e1)
			{
				throw new RuntimeException("报错内容", e1);
			}
		} finally
		{
			if (writer != null)
			{
				writer.close();
			}
		}
	}

	/**
	 * 将list转换成输出得字符串
	 * @param list
	 * @return
	 * 赵玉柱
	 */
	private String buildListToStr(List<Object> list)
	{
		if (list == null || list.isEmpty())
		{
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (Object o : list)
		{
			String strO = StrUtil.obj2str(o);
			sb.append(strO);
		}
		return sb.toString();
	}
}
