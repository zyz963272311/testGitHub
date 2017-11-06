package xyz.zyzhu.util;

import java.awt.Dimension;
import java.awt.Toolkit;


/**
 * <p>标题： WindowsUtil</p>
 * <p>内容： WindowsUtil</p>
 * <p>创建时间： 2017年5月16日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.util.WindowsUtil</p>
 * <p>作者： Administrator</p>
 */
public class WindowsUtil
{
	public static Dimension getUserWindowsSize()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		return dimension;
	}

	public static Dimension getAThridWindowsSize()
	{
		Dimension dimension = getUserWindowsSize();
		Dimension aThridDimension = new Dimension(dimension.width / 3, dimension.height / 3);
		return aThridDimension;
	}

	public static Dimension getHalfWindowSize()
	{
		Dimension dimension = getUserWindowsSize();
		Dimension aThridDimension = new Dimension(dimension.width / 2, dimension.height / 2);
		return aThridDimension;
	}
	public static Dimension getCasualWindowsSize(float proportion)
	{
		Dimension dimension = getUserWindowsSize();
		Dimension aThridDimension = new Dimension((int) (dimension.width * proportion),
				(int) (dimension.height * proportion));
		return aThridDimension;
	}

	public static Dimension getGoldWindowsSize()
	{
		float proportion = 0.618f;
		return getCasualWindowsSize(proportion);
	}
}
