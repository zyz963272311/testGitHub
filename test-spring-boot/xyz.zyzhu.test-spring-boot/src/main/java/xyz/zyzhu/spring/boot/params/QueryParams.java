package xyz.zyzhu.spring.boot.params;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import xyz.zyzhu.spring.boot.model.BasModel;

import com.liiwin.utils.MapUtil;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月5日 下午9:42:02</p>
 * <p>类全名：xyz.zyzhu.spring.boot.params.QueryParams</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class QueryParams<M extends BasModel> {
	protected M model;
	protected Map<String, Object> params;
	protected List<String> keys = new ArrayList<String>();
	/**
	 * @param model
	 * @param params
	 */
	public QueryParams(M model, Map<String, Object> params) {
		super();
		this.model = model;
		this.params = params;
	}
	protected void init()
	{
		keys.add("start");//左匹配
		keys.add("end");//右匹配
		keys.add("nullhandler");//空值说明，即若未空仍然过滤
		keys.add("ignored");//忽略属性列表
	}

	public  Example<M> buildExample()
	{
		ExampleMatcher matcher = ExampleMatcher.matching();
		if(model == null)
		{
			return null;
		}
		Example<M> example = null;
		if(MapUtil.constainsKey(params, keys))
		{
			example = Example.of(model, matcher);
		}
		else
		{
			example = Example.of(model);
		}
		return example;
	}
}
