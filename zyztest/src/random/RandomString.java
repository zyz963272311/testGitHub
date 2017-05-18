package random;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 下午5:40:14</p>
 * <p>类全名：random.RandomString</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface RandomString
{
	String getRandomString(int length, char max);

	String getRandomString(int length, char min, char max);

	String[] getRandomString(String[] o, int length, int size, char max);

	String[] getRandomString(String[] o, int length, int size, char min, char max);
}
