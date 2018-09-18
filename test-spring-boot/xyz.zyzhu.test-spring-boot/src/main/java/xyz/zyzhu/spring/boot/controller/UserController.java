package xyz.zyzhu.spring.boot.controller;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.liiwin.config.BasConfig;
import com.liiwin.date.DateUtil;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.UserInfo;
import xyz.zyzhu.spring.boot.service.UserService;
/**
 * <p>标题： UserController</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月17日 下午10:25:07</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.UserController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController()
@RequestMapping("/user")
public class UserController
{
	private static Logger		logger				= LoggerFactory.getLogger(UserController.class);
	private static final String	SESSION_USERNAME	= "user";
	@Autowired
	UserService					service;

	@RequestMapping(path = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public UserInfo login(HttpSession session, String username, String password)
	{
		UserInfo login = service.login(username, password);
		if (login != null)
		{
			session.setAttribute(SESSION_USERNAME, login);
			//设置超时时间
			logger.error("超时时间" + StrUtil.obj2int(BasConfig.getPropertie("login-timeout"), 60));
			session.setMaxInactiveInterval(StrUtil.obj2int(BasConfig.getPropertie("login-timeout"), 60));
		}
		return login;
	}

	@RequestMapping(path = "/get", method = { RequestMethod.POST, RequestMethod.GET })
	public String get(HttpSession session)
	{
		Object o = session.getAttribute(SESSION_USERNAME);
		if (o == null)
		{
			return "用户未登陆";
		}
		return DateUtil.getCurDate().toString();
	}
}
