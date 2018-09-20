package xyz.zyzhu.spring.boot.jdalli.util;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.jdalli.model.KeywordQuery;
import xyz.zyzhu.spring.boot.jdalli.model.KeywordQueryResult;
import xyz.zyzhu.spring.boot.jdalli.model.QueryExplosiveGoods;
import xyz.zyzhu.spring.boot.jdalli.model.QueryExplosiveGoodsResult;
/**
 * <p>标题： 京东联盟接口</p>
 * <p>功能： </p>
 * <p>所属模块：京东联盟</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月20日 下午8:25:04</p>
 * <p>类全名：xyz.zyzhu.spring.boot.jdalli.JDAlliUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class JDAlliUtils
{
	/**
	 * 获取爆款商品
	 * @param p
	 * @return
	 * 赵玉柱
	 */
	public static List<QueryExplosiveGoodsResult> queryExplosiveGoods(QueryExplosiveGoods p)
	{
		Integer from = p.getFrom();
		if (from == null || from < 0)
		{
			from = 0;
		}
		Integer pageSize = p.getPageSize();
		if (pageSize == null || pageSize <= 0)
		{
			pageSize = 10;
		}
		JSONArray array = new JSONArray();
		List<QueryExplosiveGoodsResult> results = new ArrayList<>();
		if (array != null)
		{
			int length = array.size();
			for (int i = 0; i < length; i++)
			{
				JSONObject json = array.getJSONObject(i);
				if (i == 0)
				{
					String resultCode = StrUtil.obj2str(json.get("resultCode"));
					if (!"200".equals(resultCode))
					{
						break;
					}
				}
				QueryExplosiveGoodsResult result = JSONObject.toJavaObject(json, QueryExplosiveGoodsResult.class);
				results.add(result);
			}
		}
		return results;
	}

	/**
	 * 关键词查询
	 * @param p
	 * @return
	 * 赵玉柱
	 */
	public List<KeywordQueryResult> keywordQuery(KeywordQuery p)
	{
		List<KeywordQueryResult> results = new ArrayList<>();
		return results;
	}
}
