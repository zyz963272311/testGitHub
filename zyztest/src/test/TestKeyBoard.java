package test;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * <p>标题：控制台获取按键输入 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月9日 下午6:58:07</p>
 * <p>类全名：test.TestKeyBoard</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestKeyBoard implements KeyListener
{
	Component	comp;

	public static void main(String args[])
	{
		TestKeyBoard testKeyBoard = new TestKeyBoard();
		testKeyBoard.addListener();
	}

	public void addListener()
	{
		//comp = new Component();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}
}
