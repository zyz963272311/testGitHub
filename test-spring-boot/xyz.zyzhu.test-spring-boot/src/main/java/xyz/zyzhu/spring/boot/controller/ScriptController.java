package xyz.zyzhu.spring.boot.controller;

import java.util.List;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.liiwin.utils.StrUtil;
import com.liiwin.utils.tac.Tac;
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
	@RequestMapping(path = "/executeJS", method = { RequestMethod.GET, RequestMethod.POST })
	public String execte(String info)
	{
		try
		{
			if (StrUtil.isStrTrimNull(info))
			{
				return "无可执行TAC";
			}
			System.out.println("执行TAC代码" + info);
			Tac tac = new Tac("js");
			List<Object> resultList = tac.execute(info);
			String result = buildListToStr(resultList);
			return StrUtil.obj2str(result, "执行完成");
		} catch (Exception e)
		{
			e.printStackTrace();
			return e.getMessage() + "\n" + new Tac().printlnArray(e.getStackTrace());
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
