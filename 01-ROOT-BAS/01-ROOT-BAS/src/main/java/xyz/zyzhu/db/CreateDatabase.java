package xyz.zyzhu.db;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import xyz.zyzhu.comm.WorkSpace;
import xyz.zyzhu.config.Config;
import xyz.zyzhu.util.StrUtil;
/**
 * <p>标题： createDatabase</p>
 * <p>内容： createDatabase</p>
 * <p>创建时间： 2017年4月24日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.createDatabase</p>
 * <p>作者： Administrator</p>
 */
abstract class CreateDatabase implements ICreateDatabase
{
	Map<String,String>						sysdef;
	Map<String,List<Map<String,Object>>>	tabdef;
	Map<String,List<Map<String,Object>>>	coldef;

	public CreateDatabase(Map<String,String> sysdef, Map<String,List<Map<String,Object>>> tabdef, Map<String,List<Map<String,Object>>> coldef)
	{
		this.sysdef = sysdef;
		this.tabdef = tabdef;
		this.coldef = coldef;
	}

	/**
	 * 创建数据库
	 */
	public void getDatabase(String filePath)
	{
		Map<String,String> workspace = WorkSpace.getWorkSpace("conf");
	}

	/**
	 * @param filePath
	 */
	private void test(String filePath)
	{
		File file = null;
		try
		{
			String configPath = "config.properties";
			Properties properties = Config.getConfig("src/main/resources/" + configPath);
			filePath = properties.getProperty("CREATEDATABASE.FILEPATH") + filePath;
			file = new File(filePath);
			System.out.println(file.getAbsolutePath());
			if (!file.exists())
			{
				throw new RuntimeException("表结构定义文件【" + filePath + "】不存在");
			}
			if (file.isDirectory())
			{
				throw new RuntimeException("表结构文件路径【" + filePath + "】找到的是路径，不是有效文件");
			}
			String fileName = file.getName();
			if (StrUtil.isStrNull(fileName))
			{
				throw new RuntimeException("表结构文件路径-【" + filePath + "】对应的文件无文件名");
			}
			int dotPos = fileName.lastIndexOf(".");
			if (dotPos < 0)
			{
				throw new RuntimeException("表结构文件路径【" + filePath + "】对应的文件无后缀名");
			}
			if (dotPos == 0)
			{
				throw new RuntimeException("表结构文件路径【" + filePath + "】对应的文件名无前缀");
			}
			String extName = fileName.substring(dotPos + 1);
			if (!"TXT".equals(extName.toUpperCase()))
			{
				throw new RuntimeException("表结构文件路径-" + filePath + "】必须是txt文件");
			}
			String preFileName = fileName.substring(0, dotPos);
			String preFix = properties.getProperty("CREATEDATABASE.PREFIX");
			if (StrUtil.isStrNull(preFix))
			{
				throw new RuntimeException(configPath + "中未定义【CREATEDATABASE.PREFIX】或【CREATEDATABASE.PREFIX】的值为空");
			}
			int prePos = preFileName.indexOf(preFix);
			if (prePos != 0)
			{
				throw new RuntimeException("表结构文件路径【" + filePath + "】的文件名" + fileName + "必须以【" + preFix + "】为文件名");
			}
			String sufFileName = preFileName.substring(preFix.length());
			int lastU = sufFileName.lastIndexOf("_");
			int fstU = sufFileName.indexOf("_");
			if (fstU <= 0 || lastU <= 0 || lastU == sufFileName.length())
			{
				String preFormate = properties.getProperty("CREATEDATABASE.FOEMATE");
				if (StrUtil.isStrNull(preFormate))
				{
					throw new RuntimeException(configPath + "中未定义【CREATEDATABASE.FOEMATE】或为【CREATEDATABASE.FOEMATE】设置值");
				}
				throw new RuntimeException("表结构文件路径【" + filePath + "】的文件名【" + fileName + "】应是【" + preFix + configPath);
			}
			String sysid = sufFileName.substring(0, fstU);
			String sysname = sufFileName.substring(lastU + 1);
			this.sysdef.put(sysid, sysname);
			System.out.println();
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 创建表表
	 */
	public void createTable(String filePath)
	{
	}

	/**
	 * 创建表结构表
	 */
	public void createCol(String filepath)
	{
	}

	public Map<String,String> getSysdef()
	{
		return sysdef;
	}

	public Map<String,List<Map<String,Object>>> getTabdef()
	{
		return tabdef;
	}

	public Map<String,List<Map<String,Object>>> getColdef()
	{
		return coldef;
	}
}
