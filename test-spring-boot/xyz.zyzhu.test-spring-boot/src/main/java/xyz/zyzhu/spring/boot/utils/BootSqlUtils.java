package xyz.zyzhu.spring.boot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.liiwin.db.SqlUtil;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.constance.CompareMethodConstance;
import xyz.zyzhu.spring.boot.db.BootDatabase;
/**
 * <p>标题： Boot使用的sql工具类</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月12日 下午8:34:02</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.BootSqlUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootSqlUtils extends SqlUtil implements CompareMethodConstance
{
	public static String buildSql(BootDatabase db, String field, Object value, Map<String,Object> params)
	{
		return buildSql(db, field, value, params, 0);
	}

	public static String buildSql(BootDatabase db, String field, Object value, Map<String,Object> params, int compareMethod)
	{
		if (db == null || StrUtil.isStrTrimNull(field) || value == null || params == null)
		{
			return null;
		}
		StringBuffer sb = new StringBuffer(" ");
		sb.append(field);
		int length = sb.length();
		int noExistKeyIdx = 0;
		List<Integer> noExistKeyIdxList = null;
		String valueStr = null;
		String[] valueArr = null;
		int size = 0;
		//注意，在使用like的时候，需要注意使用字符串拼接函数
		switch (compareMethod)
		{
		case EQ:
			//等于
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append("=:").append(field).append(noExistKeyIdx).append(" ");
			params.put(field + noExistKeyIdx, value);
			break;
		case LIKE_L:
			//左侧like 右侧加%
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append(" like(CONCAT(:").append(field).append(noExistKeyIdx).append(",'%')) ");
			params.put(field + noExistKeyIdx, value);
			break;
		case LIKE_R:
			//右侧like 左侧加%
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append(" like(CONCAT('%',:").append(field).append(noExistKeyIdx).append(")) ");
			params.put(field + noExistKeyIdx, value);
			break;
		case LIKE:
			//两侧加%
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append(" like(CONCAT(CONCAT('%',:").append(field).append(noExistKeyIdx).append("),'%')) ");
			params.put(field + noExistKeyIdx, value);
			break;
		case LIKE_BY_CHAR:
			//根据字段添加百分号
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append("like(:").append(field).append(noExistKeyIdx).append(") ");
			params.put(field + noExistKeyIdx, value);
			break;
		case IN:
			valueStr = StrUtil.obj2str(value);
			valueArr = StrUtil.split(valueStr, ',');
			noExistKeyIdxList = noExistKeyIdxList(params, field, valueArr.length);
			sb.append(" in (");
			size = noExistKeyIdxList.size();
			for (int i = 0; i < size; i++)
			{
				int idx = noExistKeyIdxList.get(i);
				sb.append(field).append(idx);
				if (i != size - 1)
				{
					sb.append(",");
				}
				sb.append(") ");
				params.put(field + idx, valueArr[i]);
			}
			//在xxx中
			break;
		case LE:
			//大于等于
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append(">=:").append(field).append(noExistKeyIdx).append(" ");
			params.put(field + noExistKeyIdx, value);
			break;
		case LG:
			//大于
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append(">:").append(field).append(noExistKeyIdx).append(" ");
			params.put(field + noExistKeyIdx, value);
			break;
		case RE:
			//小于等于
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append("<=:").append(field).append(noExistKeyIdx).append(" ");
			params.put(field + noExistKeyIdx, value);
			break;
		case RG:
			//小于
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append("<:").append(field).append(noExistKeyIdx).append(" ");
			params.put(field + noExistKeyIdx, value);
			break;
		case N_EQ:
			//不等于
			noExistKeyIdx = noExistKeyIdx(params, field);
			sb.append("<>:").append(field).append(noExistKeyIdx).append(" ");
			params.put(field + noExistKeyIdx, value);
			break;
		case N_IN:
			//不在xxx中
			valueStr = StrUtil.obj2str(value);
			valueArr = StrUtil.split(valueStr, ',');
			noExistKeyIdxList = noExistKeyIdxList(params, field, valueArr.length);
			sb.append(" not in (");
			for (int i = 0; i < size; i++)
			{
				int idx = noExistKeyIdxList.get(i);
				sb.append(field).append(idx);
				if (i != size - 1)
				{
					sb.append(",");
				}
				sb.append(") ");
				params.put(field + idx, valueArr[i]);
			}
			break;
		default:
			break;
		}
		return sb.length() > length ? sb.toString() : null;
	}

	/**
	 * 获取一个不存在的key的索引
	 * @param params
	 * @param key
	 * @return
	 * 赵玉柱
	 */
	public static int noExistKeyIdx(Map<String,Object> params, String key)
	{
		if (key == null)
		{
			return 0;
		}
		if (params == null || params.isEmpty())
		{
			return 0;
		}
		int idx = 0;
		return noExistKeyIdx(params, key, idx);
	}

	public static int noExistKeyIdx(Map<String,Object> params, String key, int fromIdx)
	{
		String newKey = key + fromIdx;
		while (params.containsKey(newKey))
		{
			newKey = key + (fromIdx++);
		}
		return fromIdx;
	}

	/**
	 * @param params
	 * @param key
	 * @param size
	 * @return
	 * 赵玉柱
	 */
	public static List<Integer> noExistKeyIdxList(Map<String,Object> params, String key, int size)
	{
		return noExistKeyIdxList(params, key, size, 0);
	}

	/**
	 * 判断map中是否含有某key
	 * @param params
	 * @param key
	 * @param size
	 * @param fromIdx
	 * @return
	 * 赵玉柱
	 */
	public static List<Integer> noExistKeyIdxList(Map<String,Object> params, String key, int size, int fromIdx)
	{
		if (size <= 0)
		{
			size = 1;
		}
		List<Integer> result = new ArrayList<>();
		if (key == null)
		{
			return result;
		}
		if (params == null || params.isEmpty())
		{
			return result;
		}
		for (int i = 0; i < size; i++)
		{
			fromIdx = noExistKeyIdx(params, key, fromIdx);
			result.add(fromIdx);
			fromIdx++;
		}
		return result;
	}
}
