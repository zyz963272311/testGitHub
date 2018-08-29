package xyz.zyzhu.spring.boot.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import xyz.zyzhu.spring.boot.dao.AutoCodeDao;
import xyz.zyzhu.spring.boot.model.AutoCode;
import xyz.zyzhu.spring.boot.model.AutoCodeG;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月31日 上午10:14:26</p>
 * <p>类全名：xyz.zyzhu.spring.boot.dao.impl.AotoCodeDaoImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Repository
public class AutoCodeDaoImpl implements AutoCodeDao
{
	@Override
	public AutoCode insertM(AutoCode c)
	{
		return null;
	}

	@Override
	public void saveM(AutoCode c)
	{
	}

	@Override
	public AutoCodeG insertG(AutoCodeG g)
	{
		return null;
	}

	@Override
	public void saveG(AutoCodeG g)
	{
	}

	@Override
	public List<AutoCode> queryListM()
	{
		return null;
	}

	@Override
	public List<AutoCodeG> queryListG()
	{
		return null;
	}

	@Override
	public List<AutoCode> queryListMByFilter(AutoCode c, Map<String,Object> p)
	{
		return null;
	}

	@Override
	public List<AutoCodeG> queryListGByFilter(AutoCode c, Map<String,Object> p)
	{
		return null;
	}

	@Override
	public void updateM(AutoCode c)
	{
	}

	@Override
	public void updateG(AutoCodeG g)
	{
	}

	@Override
	public void deleteM(AutoCode c)
	{
	}

	@Override
	public void deleteGByM(AutoCode c)
	{
	}

	@Override
	public void deleteMById(String id)
	{
	}

	@Override
	public void deleteGByMid(String id)
	{
	}
}
