package xyz.zyzhu.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import xyz.zyzhu.spring.boot.service.TestDubboService;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月16日 上午11:24:57</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.impl.TestDubboServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service
public class TestDubboServiceImpl implements TestDubboService
{
	@Override
	public String getInsert(String insert)
	{
		return "用户输入" + insert;
	}
}
