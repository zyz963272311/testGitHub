package com.liiwin.util;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
/**
 * <p>标题： 用户窗口界面工具类</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 LIIWIN</p>
 * <p>公司: 来往互动(北京)科技有限公司</p>
 * <p>创建日期：2017年5月18日 上午11:23:30</p>
 * <p>类全名：com.liiwin.util.WindowsSizeUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class WindowsSizeUtil
{
	/**
	 * 获取用户屏幕大小
	 * @return
	 * 赵玉柱
	 */
	public static Dimension getUserWindowsSize()
	{
		return getUserProportWindowsSize(1);
	}

	/**
	 * 获取用户半个屏幕的长和宽【面积为整个屏幕的1/4】
	 * @return
	 * 赵玉柱
	 */
	public static Dimension getUserHalfWindowsSize()
	{
		return getUserProportWindowsSize(0.5f);
	}

	/**
	 * 获取用户屏幕的黄金比例的大小，这个值大约为0.618【(√5-1)/2】
	 * @return
	 * 赵玉柱
	 */
	public static Dimension getUserGoldenWindowsSize()
	{
		return getUserProportWindowsSize(0.618f);
	}

	/**
	 * 获取用户屏幕的任意比例的窗口大小
	 * @param proport 想要的长度[宽度]/用户屏幕的长度[宽度]
	 * @return
	 * 赵玉柱
	 */
	public static Dimension getUserProportWindowsSize(float proport)
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		dimension.width = (int) (dimension.width * proport);
		dimension.height = (int) (dimension.height * proport);
		return dimension;
	}

	/**
	 * 获取用户屏幕，出去工具栏后的大小
	 * @return
	 * 赵玉柱
	 */
	public static Rectangle getUserWindowsNoToolbar()
	{
		return getProportUserWindowsNoToolbar(1);
	}

	/**
	 * 获取半个屏幕的大小
	 * @return
	 * 赵玉柱
	 */
	public static Rectangle getHalfUserWindowsNoToolbar()
	{
		return getProportUserWindowsNoToolbar(0.5f);
	}

	/**
	 * 获取黄金比例的大小，默认居中显示
	 * @return
	 * 赵玉柱
	 */
	public static Rectangle getGoldenUserWindowsNoToolBar()
	{
		return getProportUserWindowsNoToolbar(0.618f);
	}

	/**
	 * 获取一个剔除滚工具栏后的任意比例的用户窗口大小，这个值应该小于等于1并且大于0
	 * @param proport 想要的长度[宽度]/用户屏幕的长度[宽度]
	 * @return
	 * 赵玉柱
	 */
	public static Rectangle getProportUserWindowsNoToolbar(float proport)
	{
		return getProportUserWindowsNoToolbar(proport, proport);
	}

	/**
	 * 获取一个与用户窗口大小都是任意比例的矩形
	 * @param proportX
	 * @param proportY
	 * @return
	 * 赵玉柱
	 */
	public static Rectangle getProportUserWindowsNoToolbar(float proportX, float proportY)
	{
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maxWinRectangle = graphicsEnvironment.getMaximumWindowBounds();
		int width = maxWinRectangle.width;
		int height = maxWinRectangle.height;
		maxWinRectangle.width = (int) (width * proportX);
		maxWinRectangle.height = (int) (height * proportY);
		maxWinRectangle.x = maxWinRectangle.x + (width - maxWinRectangle.width) / 2;
		maxWinRectangle.y = maxWinRectangle.y + (height - maxWinRectangle.height) / 2;
		return maxWinRectangle;
	}

	public static void main(String[] args)
	{
		System.out.println();
	}
}
