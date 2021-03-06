package com.zhu.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liiwin.code.Code;
import com.liiwin.code.CodePart;
import com.liiwin.code.CodeType;
import com.liiwin.utils.StrUtil;
import com.zhu.ssm.common.PageInfo;
import com.zhu.ssm.dao.UserDao;
import com.zhu.ssm.model.User;
import com.zhu.ssm.service.UserService;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月5日 下午3:54:14</p>
 * <p>类全名：com.zhu.ssm.service.impl.UserServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service
public class UserServiceImpl implements UserService
{
	Log				logger	= LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private UserDao	userDao;

	@Override
	public void findUser(PageInfo pageInfo, User user)
	{
		logger.info("findUser pageInfo:" + JSON.toJSONString(pageInfo));
		Page<?> page = PageHelper.startPage(pageInfo.getPageNumber(), pageInfo.getPageSize());
		pageInfo.setRows(userDao.findUserByParams(user));
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void createUser(User user)
	{
		if (user != null)
		{
			if (StrUtil.isStrTrimNull(user.getUserid()))
			{
				List<CodePart> codeParts = new ArrayList<>();
				CodePart codePart1 = new CodePart();
				codePart1.setCodePartFormate("user");
				codePart1.setLength(4);
				codePart1.setType(CodeType.FIX_LENGTH);
				codeParts.add(codePart1);
				CodePart codePart2 = new CodePart();
				codePart2.setLength(8);
				codePart2.setType(CodeType.RANDOM_NUMBER);
				codeParts.add(codePart2);
				Code code = new Code(codeParts);
				user.setUserid(code.makeCode());
			}
			userDao.createUser(user);
		}
	}

	@Override
	public void deleteUser(User user)
	{
		if (user != null && StrUtil.isNoStrTrimNull(user.getUserid()))
		{
			userDao.deleteUser(user);
		}
	}

	@Override
	public void updateUser(User user)
	{
		if (user != null && StrUtil.isNoStrTrimNull(user.getUserid()))
		{
			userDao.updateuser(user);
		}
	}
}
