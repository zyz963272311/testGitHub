package xyz.zyzhu.spring.boot.constance;

/**
 * <p>标题： 比较方式常量类</p>
 * <p>功能： </p>
 * <p>所属模块： 比较方式常量类</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月11日 下午9:47:21</p>
 * <p>类全名：xyz.zyzhu.spring.boot.constance.CompareMethodConstance</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface CompareMethodConstance
{
	//等于
	public static final int	EQ				= 0;
	//左侧like 右侧加百分号
	public static final int	LIKE_L			= 1;
	//右侧like 左侧加百分号
	public static final int	LIKE_R			= 2;
	//中间like 两侧加百分号
	public static final int	LIKE			= 3;
	//根据字符串获取like  %,下划线
	public static final int	LIKE_BY_CHAR	= 4;
	//in 各个字符串之间用逗号分开
	public static final int	IN				= 5;
	//大于等于
	public static final int	LE				= 6;
	//大于
	public static final int	LG				= 7;
	//小于等于
	public static final int	RE				= 8;
	//小于
	public static final int	RG				= 9;
	//不等于
	public static final int	N_EQ			= 10;
	//not in 各个字符串之间用逗号分开
	public static final int	N_IN			= 11;
}
