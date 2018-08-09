package xyz.zyzhu.spring.boot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.RandomUtils;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.LoadBalance;
/**
 * <p>标题： 负载均衡工具类</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月3日 下午4:19:46</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.LoadBalanceUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class LoadBalanceUtils
{
	/**轮训方式对应的key与当前坐标*/
	private static ConcurrentHashMap<String,AtomicInteger>	rrMap	= new ConcurrentHashMap<>();
	/**加权轮训方式对应的key与当前的坐标*/
	private static ConcurrentHashMap<String,AtomicInteger>	wrrMap	= new ConcurrentHashMap<>();

	/**
	 * 
	 * @param key 用于轮询与加权轮训情况下的当前左边缓存
	 * @param balances 负载对象List
	 * @param options 负载类别 1：轮询;2:加权轮询;4:加权随机;默认：随机
	 * @return
	 * 赵玉柱
	 */
	public static LoadBalance balance(String key, List<LoadBalance> balances, int options)
	{
		LoadBalance balance = null;
		if ((options & 1) > 0)
		{
			balance = roundRobinBalance(key, balances);
		} else if ((options & 2) > 0)
		{
			balance = weightRoundRobinBalance(key, balances);
		} else if ((options & 4) > 0)
		{
			balance = weightRandomBalance(balances);
		} else
		{
			balance = randomBalance(balances);
		}
		return balance;
	}

	/**
	 * 轮训方式负载
	 * @param key
	 * @param balances
	 * @return
	 * 赵玉柱
	 */
	public static LoadBalance roundRobinBalance(String key, List<LoadBalance> balances)
	{
		if (StrUtil.isStrTrimNull(key))
		{
			return null;
		}
		List<LoadBalance> onLineBalances = getOnLineBalances(balances);
		if (onLineBalances == null || onLineBalances.isEmpty())
		{
			return null;
		}
		AtomicInteger pos = rrMap.get(key);
		if (pos == null)
		{
			pos = new AtomicInteger(0);
			rrMap.put(key, pos);
		}
		LoadBalance balance = onLineBalances.get((pos.intValue() % (onLineBalances.size())));
		pos.set(pos.incrementAndGet() % onLineBalances.size());
		return balance;
	}

	/**
	 * 随机方式负载
	 * @param key
	 * @param balances
	 * @return
	 * 赵玉柱
	 */
	public static LoadBalance randomBalance(List<LoadBalance> balances)
	{
		List<LoadBalance> onLineBalances = getOnLineBalances(balances);
		if (onLineBalances == null || onLineBalances.isEmpty())
		{
			return null;
		}
		//当仅有一个负载，直接取数
		if (onLineBalances.size() == 1)
		{
			return onLineBalances.get(0);
		}
		int nextInt = RandomUtils.nextInt(0, onLineBalances.size());
		return onLineBalances.get(nextInt);
	}

	/**
	 * 加权轮训方式
	 * @param key
	 * @param balances
	 * @return
	 * 赵玉柱
	 */
	public static LoadBalance weightRoundRobinBalance(String key, List<LoadBalance> balances)
	{
		if (StrUtil.isStrTrimNull(key))
		{
			return null;
		}
		List<LoadBalance> onLineBalances = getOnLineBalances(balances);
		if (onLineBalances == null || onLineBalances.isEmpty())
		{
			return null;
		}
		if (onLineBalances.size() == 1)
		{
			return onLineBalances.get(0);
		}
		List<LoadBalance> tempList = new ArrayList<>();
		for (LoadBalance balance : onLineBalances)
		{
			for (int i = 0; i < balance.getIntWeight(); i++)
			{
				tempList.add(balance);
			}
		}
		if (tempList.isEmpty())
		{
			return null;
		}
		AtomicInteger pos = wrrMap.get(key);
		LoadBalance balance = tempList.get(pos.get() % tempList.size());
		pos.set(pos.incrementAndGet() % tempList.size());
		return balance;
	}

	/**
	 * 加权随机方式
	 * @param balances
	 * @return
	 * 赵玉柱
	 */
	public static LoadBalance weightRandomBalance(List<LoadBalance> balances)
	{
		List<LoadBalance> onLineBalances = getOnLineBalances(balances);
		if (onLineBalances == null || onLineBalances.isEmpty())
		{
			return null;
		}
		if (onLineBalances.size() == 1)
		{
			return onLineBalances.get(0);
		}
		List<LoadBalance> tempList = new ArrayList<>();
		for (LoadBalance balance : onLineBalances)
		{
			for (int i = 0; i < balance.getIntWeight(); i++)
			{
				tempList.add(balance);
			}
		}
		if (tempList.isEmpty())
		{
			return null;
		}
		return tempList.get(RandomUtils.nextInt(0, tempList.size()));
	}

	/**
	 * 获取在线的负载集合
	 * @param balances
	 * @return
	 * 赵玉柱
	 */
	public static List<LoadBalance> getOnLineBalances(List<LoadBalance> balances)
	{
		return getFlagsBalances(balances, true);
	}

	/**
	 * 获取不在线的负载均衡集合
	 * @param balances
	 * @return
	 * 赵玉柱
	 */
	public static List<LoadBalance> getOffLineBalances(List<LoadBalance> balances)
	{
		return getFlagsBalances(balances, false);
	}

	/**
	 * 获取在线或离线的负载均衡对象集合
	 * @param balances
	 * @return
	 * 赵玉柱
	 */
	public static List<LoadBalance> getFlagsBalances(List<LoadBalance> balances, boolean isOnline)
	{
		synchronized (LoadBalanceUtils.class)
		{
			List<LoadBalance> result = new ArrayList<>();
			if (balances == null || balances.isEmpty())
			{
				return result;
			}
			for (LoadBalance balance : balances)
			{
				if (balance != null)
				{
					//异或，相反为一
					if (balance.isOffLine() ^ isOnline)
					{
						result.add(balance);
					}
				}
			}
			return result;
		}
	}
}
