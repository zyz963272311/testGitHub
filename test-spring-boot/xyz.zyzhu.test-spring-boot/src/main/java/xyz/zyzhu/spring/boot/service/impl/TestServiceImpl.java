package xyz.zyzhu.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import xyz.zyzhu.spring.boot.model.AutoCode;
import xyz.zyzhu.spring.boot.service.TestService;
import xyz.zyzhu.spring.boot.utils.DefaultValueUtils;
import xyz.zyzhu.spring.boot.utils.ObjectUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月1日 下午4:02:25</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.impl.TestServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service
public class TestServiceImpl implements TestService
{
	@Override
	public AutoCode test()
	{
		AutoCode autoCode = DefaultValueUtils.buildDefaultValueByClass(AutoCode.class, true);
		ObjectUtils.objValueCheck(autoCode);
		return autoCode;
	}
}
