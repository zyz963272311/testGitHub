package xyz.zyzhu.spring.boot.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.comparator.ModelComparator;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.DataCodeDef;
import xyz.zyzhu.spring.boot.model.DataCodeGDef;
import xyz.zyzhu.spring.config.RedisConfig;
/**
 * <p>标题： 数据字典工具类</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 上午11:28:08</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.DataCodeUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataCodeUtils
{
	/**
	 * 根据字典编号获取字典信息
	 * @param dictcode
	 * @return
	 * 赵玉柱
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String,String> getDictInfo(String dictcode)
	{
		if (StrUtil.isNoStrTrimNull(dictcode))
		{
			return null;
		}
		String dictRedisKey = getDictRedisKey(dictcode);
		RedisConfig bean = SpringBeanUtils.getBean(RedisConfig.class);
		RedisTemplate<String,Map> dictRedis = null;
		if (bean != null)
		{
			dictRedis = bean.getRedisTemplete(String.class, Map.class);
		}
		if (dictRedis != null)
		{
			Map<String,String> map = dictRedis.opsForValue().get(dictRedisKey);
			if (map != null)
			{
				return map;
			}
		}
		//查询db
		String sql = "select g.* from dictinfog g,dictinfo m where m.dicticode=g.dicticode and m.dictcode=:dictcode order by g.code ";
		BootDatabase db = null;
		Map<String,String> result = new HashMap<>();
		try
		{
			db = BootDatabasePoolManager.getReadDatabaseByTable("dictinfo");
			Map<String,Object> params = new HashMap<>();
			params.put("dictcode", dictcode);
			List<Map<String,Object>> listMapFromSql = db.getListMapFromSql(sql, params);
			if (listMapFromSql != null)
			{
				for (Map<String,Object> map : listMapFromSql)
				{
					String code = StrUtil.obj2str(map.get("code"));
					String name = StrUtil.obj2str(map.get("name"));
					result.put(code, name);
				}
			}
			//将查询的结果塞入redis缓存中
			if (dictRedis != null)
			{
				dictRedis.opsForValue().set(dictRedisKey, result, 2, TimeUnit.DAYS);
			}
		} finally
		{
			if (db != null)
			{
				BootDatabasePoolManager.close(db);
			}
		}
		return result;
	}

	/**
	 * 根据字典编号与key获取值
	 * @param dicticode
	 * @param code
	 * @return
	 * 赵玉柱
	 */
	public static String getDictInfo(String dicticode, String code)
	{
		Map<String,String> dictInfo = getDictInfo(dicticode);
		if (dictInfo != null)
		{
			return dictInfo.get(code);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String,String> getDataCode(String datacode, String codename)
	{
		if (StrUtil.isNoStrTrimNull(datacode))
		{
			return null;
		}
		String dataCodeKey = getDataCodeKey(datacode);
		RedisConfig bean = SpringBeanUtils.getBean(RedisConfig.class);
		RedisTemplate<String,Map> dataCodeRedis = null;
		if (bean != null)
		{
			dataCodeRedis = bean.getRedisTemplete(String.class, Map.class);
		}
		if (dataCodeRedis != null)
		{
			Map<String,Map<String,String>> map = dataCodeRedis.opsForValue().get(dataCodeKey);
			if (map != null)
			{
				Map<String,String> resultMap = new HashMap<>();
				for (Entry<String,Map<String,String>> entry : map.entrySet())
				{
					String key = entry.getKey();
					Map<String,String> value = entry.getValue();
					String codevalue = null;
					if (value != null && !value.isEmpty())
					{
						codevalue = value.get(codename);
					}
					resultMap.put(key, codevalue);
				}
				return resultMap;
			}
		}
		BootDatabase db = null;
		try
		{
			db = BootDatabasePoolManager.getReadDatabaseByTable("datacodedef");
			DataCodeDef codeDef = new DataCodeDef();
			codeDef.setCode(datacode);
			DataCodeDef query1 = db.query1(codeDef);
			if (query1 != null)
			{
				String ordercolumns = query1.getOrdercolumns();
				String queryfilter = query1.getQueryfilter();
				String icode = query1.getIcode();
				DataCodeGDef queryGDef = new DataCodeGDef();
				queryGDef.setIcode(icode);
				List<DataCodeGDef> query = db.query(queryGDef);
				StringBuffer sqlSB = new StringBuffer("select ");
				if (query != null)
				{
					ModelComparator<DataCodeGDef> comparator = new ModelComparator<>("rno");
					//对字段进行排序
					int i = 0;
					int size = query.size();
					Collections.sort(query, comparator);
					for (DataCodeGDef gDef : query)
					{
						String codecolas = gDef.getCodecolas();
						String codecol = gDef.getCodecol();
						if (StrUtil.isStrTrimNull(codecolas))
						{
							sqlSB.append(codecolas).append(" as ");
						}
						sqlSB.append(codecol);
						if (i != size - 1)
						{
							sqlSB.append(",");
						}
						++i;
					}
					sqlSB.append(" from ").append(queryGDef.getTableName());
					if (StrUtil.isNoStrTrimNull(queryfilter))
					{
						sqlSB.append(" where ").append(queryfilter);
					}
					if (StrUtil.isNoStrTrimNull(ordercolumns))
					{
						sqlSB.append(" order by ").append(ordercolumns);
					}
					List<Map<String,Object>> listMapFromSql = db.getListMapFromSql(sqlSB.toString());
					if (listMapFromSql != null && !listMapFromSql.isEmpty())
					{
					}
				}
			}
		} finally
		{
			if (db != null)
			{
				BootDatabasePoolManager.close(db);
			}
		}
		return null;
	}

	/**
	 * 获取字典的rediskey
	 * @param dicticode
	 * @return
	 * 赵玉柱
	 */
	public static String getDictRedisKey(String dicticode)
	{
		String dictRedisKey = getDictRedisKey();
		return dictRedisKey + "|" + dicticode;
	}

	/**
	 * 获取码表的rediskey
	 * @param datacode
	 * @return
	 * 赵玉柱
	 */
	protected static String getDataCodeKey(String datacode)
	{
		String dataCodeKey = getDataCodeKey();
		return dataCodeKey + "|" + datacode;
	}

	/**
	 * 获取字典的rediskey
	 * @return
	 * 赵玉柱
	 */
	protected static String getDictRedisKey()
	{
		return "DictInfo";
	}

	/**
	 * 获取码表的rediskey
	 * @return
	 * 赵玉柱
	 */
	protected static String getDataCodeKey()
	{
		return "DataCode";
	}
}