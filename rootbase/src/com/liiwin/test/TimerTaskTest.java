package com.liiwin.test;

import java.util.HashMap;
import java.util.Map;
import com.liiwin.timertask.AppTimer;
import com.liiwin.timertask.AppTimerTask;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月7日 下午4:18:51</p>
 * <p>类全名：com.liiwin.test.TimerTaskTest</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TimerTaskTest
{
	public static void main(String[] args)
	{
		Map<String,Object> params = new HashMap<>();
		AppTimer<AppTimerTask> timer = new AppTimer<>(params);
	}
}
