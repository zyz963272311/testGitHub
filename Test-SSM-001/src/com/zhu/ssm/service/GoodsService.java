package com.zhu.ssm.service;

import com.zhu.ssm.common.PageInfo;
import com.zhu.ssm.model.Goods;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月8日 上午10:03:56</p>
 * <p>类全名：com.zhu.ssm.service.GoodsService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface GoodsService
{
	void findGood(PageInfo pageInfo, Goods goods);

	void createGood(Goods goods);

	void deleteGood(Goods goods);

	void updateGood(Goods goods);
}
