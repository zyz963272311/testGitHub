package xyz.zyzhu.spring.boot.tac;

import com.liiwin.utils.tac.Tac;
import com.liiwin.utils.tac.WriterWaper;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月19日 下午2:35:08</p>
 * <p>类全名：xyz.zyzhu.spring.boot.tac.BootTac</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootTac extends Tac
{
	public BootTac()
	{
		super();
	}

	public BootTac(String enginename)
	{
		super(enginename);
	}

	public BootTac(String enginename, WriterWaper waper)
	{
		super(enginename, waper);
	}
}
