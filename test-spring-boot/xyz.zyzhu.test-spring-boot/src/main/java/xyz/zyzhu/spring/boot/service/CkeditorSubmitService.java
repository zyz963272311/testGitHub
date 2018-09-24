package xyz.zyzhu.spring.boot.service;

import xyz.zyzhu.spring.boot.model.CkeditorSubmitRequest;
import xyz.zyzhu.spring.boot.model.CkeditorSubmitResponse;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午7:38:00</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.CkeditorSubmitService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface CkeditorSubmitService
{
	public CkeditorSubmitResponse submit(CkeditorSubmitRequest request);
}
