package test;

import java.io.File;
import java.util.List;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年9月1日 下午2:31:03</p>
 * <p>类全名：test.File</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class FileErgodic
{
	private boolean				dir;			//是否是文件夹
	private boolean				file;			//是否是文件
	private List<File>			dirs;			//当前文件夹下的子文件夹列表
	private List<File>			files;			//当前文件夹下的文件列表
	private String				url;			//当前文件/文件夹路径
	private List<FileErgodic>	subFileErgodic; //当前文件夹的子文件遍历
	private int					parents;		//父文件夹的个数

	public boolean isDir()
	{
		return dir;
	}

	public void setDir(boolean dir)
	{
		this.dir = dir;
	}

	public boolean isFile()
	{
		return file;
	}

	public void setFile(boolean file)
	{
		this.file = file;
	}

	public List<File> getDirs()
	{
		return dirs;
	}

	public void setDirs(List<File> dirs)
	{
		this.dirs = dirs;
	}

	public List<File> getFiles()
	{
		return files;
	}

	public void setFiles(List<File> files)
	{
		this.files = files;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public List<FileErgodic> getSubFileErgodic()
	{
		return subFileErgodic;
	}

	public void setSubFileErgodic(List<FileErgodic> subFileErgodic)
	{
		this.subFileErgodic = subFileErgodic;
	}

	public int getParents()
	{
		return parents;
	}

	public void setParents(int parents)
	{
		this.parents = parents;
	}

	@Override
	public String toString()
	{
		String rt = "";
		if (dir || file)
		{
			rt = "文件" + (dir ? "夹" : "") + "路径为" + url;
			String pre = "";
			for (int i = 0; i < parents; i++)
			{
				pre += "--》";
			}
			if (subFileErgodic != null && !subFileErgodic.isEmpty())
			{
				for (FileErgodic sub : subFileErgodic)
				{
					rt += sub.toString();
				}
			}
			rt = "\n" + pre + rt;
		} else
		{
			rt = "路径[" + url + "]不是文件或文件夹";
		}
		return rt;
	}
}
