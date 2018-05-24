package com.zhu.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhu.ssm.common.Constans;
import com.zhu.ssm.common.PageInfo;
import com.zhu.ssm.common.ResultBean;
import com.zhu.ssm.model.User;
import com.zhu.ssm.service.UserService;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月5日 下午4:01:58</p>
 * <p>类全名：com.zhu.ssm.controller.UserController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController
{
	@Autowired
	private UserService	userService;

	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageInfo findUser(PageInfo pageInfo, User user)
	{
		userService.findUser(pageInfo, user);
		return pageInfo;
	}

	@RequestMapping(value = "insert", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean insertUser(User user)
	{
		ResultBean rb = new ResultBean();
		userService.createUser(user);
		rb.setFlag(Constans.SUCCESS);
		return rb;
	}

	@RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean deleteUser(User user)
	{
		ResultBean rb = new ResultBean();
		userService.deleteUser(user);
		rb.setFlag(Constans.SUCCESS);
		return rb;
	}

	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultBean updateUser(User user)
	{
		ResultBean rb = new ResultBean();
		userService.updateUser(user);
		rb.setFlag(Constans.SUCCESS);
		return rb;
	}
}
