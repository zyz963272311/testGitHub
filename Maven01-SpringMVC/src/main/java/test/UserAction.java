package test;

import java.io.UnsupportedEncodingException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>标题： UserAction</p>
 * <p>内容： UserAction</p>
 * <p>创建时间： 2017年3月16日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： Maven01.Maven01_SpringMVC.test.UserAction</p>
 * <p>作者： Administrator</p>
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserAction
{

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(WebRequest request) throws UnsupportedEncodingException
	{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String utfName = new String(name.getBytes("iso8859-1"), "utf-8");
		System.out.println("UserAction.save接收到前台发来的数据utfname[" + utfName + "],password[" + password + "]");
		System.out.println("UserAction.save接收到前台发来的数据name[" + name + "],password[" + password + "]");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/test/saveUserSuccess");
		mv.addObject("msg", "保存成功");
		return mv;
	}
}
