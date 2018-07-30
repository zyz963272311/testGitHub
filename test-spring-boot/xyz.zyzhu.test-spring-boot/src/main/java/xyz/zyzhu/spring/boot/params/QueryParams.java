package xyz.zyzhu.spring.boot.params;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import com.liiwin.utils.MapUtil;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.BasModel;
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
public class QueryParams<M extends BasModel>
{
	protected M						model;
	protected Map<Object,Object>	params;
	protected List<Object>			keys	= new ArrayList<>();

	/**
	 * @param model
	 * @param params
	 */
	public QueryParams(M model, Map<Object,Object> params)
	{
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
		keys.add("ignoreCase");//忽略大小写
	}

	public Example<M> buildExample()
	{
		if (model == null)
		{
			return null;
		}
		Example<M> example = null;
		if (MapUtil.constainsKey(params, keys))
		{
			ExampleMatcher matcher = ExampleMatcher.matching();
			buildMatcher(matcher);
			example = Example.of(model, matcher);
		} else
		{
			example = Example.of(model);
		}
		return example;
	}

	/**
	 * 组装匹配器
	 * @param matcher
	 * 赵玉柱
	 */
	private void buildMatcher(ExampleMatcher matcher)
	{
		//start with 查询
		String objectStart = StrUtil.obj2str(params.get("start"));
		if (StrUtil.isNoStrTrimNull(objectStart))
		{
			String[] keyArray = StrUtil.split(objectStart, ',');
			for (String key : keyArray)
			{
				matcher = matcher.withMatcher(key, ExampleMatcher.GenericPropertyMatchers.startsWith());
			}
		}
		//已xxx结尾
		String objectEnd = StrUtil.obj2str(params.get("end"));
		if (StrUtil.isNoStrTrimNull(objectEnd))
		{
			String[] keyArray = StrUtil.split(objectEnd, ',');
			for (String key : keyArray)
			{
				matcher = matcher.withMatcher(key, ExampleMatcher.GenericPropertyMatchers.endsWith());
			}
		}
		//空值说明
		boolean objectnullHandler = StrUtil.obj2bool(params.get("nullhandler"));
		matcher.withNullHandler(objectnullHandler ? NullHandler.INCLUDE : NullHandler.IGNORE);
		//忽略参数列表
		String objectIgnored = StrUtil.obj2str(params.get("ignored"));
		if (StrUtil.isNoStrTrimNull(objectIgnored))
		{
			String[] keyArray = StrUtil.split(objectIgnored, ',');
			matcher = matcher.withIgnorePaths(keyArray);
		}
		//忽略大小写
		String objectIgnoreCase = StrUtil.obj2str(params.get("ignoreCase"));
		if (StrUtil.isNoStrTrimNull(objectIgnoreCase))
		{
			String[] keyArray = StrUtil.split(objectIgnoreCase, ',');
			matcher = matcher.withIgnoreCase(keyArray);
		}
	}
}
