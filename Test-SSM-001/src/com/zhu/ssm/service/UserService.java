package com.zhu.ssm.service;

import com.zhu.ssm.common.PageInfo;
import com.zhu.ssm.model.User;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月5日 下午3:49:44</p>
 * <p>类全名：com.zhu.ssm.service.UserService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface UserService
{
	void findUser(PageInfo pageInfo, User user);

	void createUser(User user);

	void deleteUser(User user);

	void updateUser(User user);
}
