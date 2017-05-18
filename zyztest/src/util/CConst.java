package util;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 上午10:53:14</p>
 * <p>类全名：util.CConst</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CConst
{
	/**
	   * 网站图片目录
	   */
	public final static String		PIC_ROOT				= "/pic/";
	/**
	 * 网站软件目录
	 */
	public final static String		SOFT_ROOT				= "/soft/";
	/**
	 * 附件文件存放的绝对路径
	 */
	public final static String		ATTACH_ROOT				= "/attach/";
	/**
	 * 动态程序路径
	 */
	public final static String		ProgramPath				= "/programs/";
	/**
	 * 上传图片大小限制，单位byte
	 */
	public final static long		MAX_UPLOAD_PIC_SIZE		= 1000 * 1024 * 4;
	/**
	 * 上传文件大小限制，单位byte
	 */
	public final static long		MAX_UPLOAD_FILE_SIZE	= 10 * 1024 * 1024 * 8;
	/**
	 * 上传软件大小限制，单位byte
	 */
	public final static long		MAX_UPLOAD_SOFT_SIZE	= 100 * 1024 * 1024 * 8;
	/**
	  * 错误登录次数最多3次
	  */
	public final static int			MAX_LOGIN_TIMES			= 3;
	/**
	  * 错误登录3次后用户被锁10分钟
	  */
	public final static int			LOCK_TIME				= +10;
	public final static String[]	ADMIN					= { "admin", "longlob" };
}
