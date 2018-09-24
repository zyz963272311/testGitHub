package com.baidu.ueditor.define;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:10:12</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.define.State</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface State
{
	public abstract boolean isSuccess();

	public abstract void putInfo(String paramString1, String paramString2);

	public abstract void putInfo(String paramString, long paramLong);

	public abstract String toJSONString();
}
