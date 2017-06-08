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
import com.zhu.ssm.dao.GoodsDao;
import com.zhu.ssm.model.Goods;
import com.zhu.ssm.service.GoodsService;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月8日 下午3:16:53</p>
 * <p>类全名：com.zhu.ssm.service.impl.GoodsServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service
public class GoodsServiceImpl implements GoodsService
{
	Log					logger	= LogFactory.getLog(GoodsServiceImpl.class);
	@Autowired
	private GoodsDao	goodsDao;

	@Override
	public void findGood(PageInfo pageInfo, Goods goods)
	{
		logger.info("findGoods pageInfo:" + JSON.toJSONString(pageInfo));
		Page<?> page = PageHelper.startPage(pageInfo.getPageNumber(), pageInfo.getPageSize());
		pageInfo.setRows(goodsDao.findGoodsByParams(goods));
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void createGood(Goods goods)
	{
		if (goods != null)
		{
			if (StrUtil.isStrTrimNull(goods.getGoodsid()))
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
				goods.setGoodsid(code.makeCode());
			}
			goodsDao.createGoods(goods);
		}
	}

	@Override
	public void deleteGood(Goods goods)
	{
		goodsDao.deleteGoods(goods);
	}

	@Override
	public void updateGood(Goods goods)
	{
		goodsDao.updateGoods(goods);
	}
}
