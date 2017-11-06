package test;

import java.io.UnsupportedEncodingException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>���⣺ UserAction</p>
 * <p>���ݣ� UserAction</p>
 * <p>����ʱ�䣺 2017��3��16��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� Maven01.Maven01_SpringMVC.test.UserAction</p>
 * <p>���ߣ� Administrator</p>
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
		System.out.println("UserAction.save���յ�ǰ̨����������utfname[" + utfName + "],password[" + password + "]");
		System.out.println("UserAction.save���յ�ǰ̨����������name[" + name + "],password[" + password + "]");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/test/saveUserSuccess");
		mv.addObject("msg", "����ɹ�");
		return mv;
	}
}
