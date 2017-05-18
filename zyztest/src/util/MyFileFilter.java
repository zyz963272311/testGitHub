package util;

import java.io.File;
import java.io.FileFilter;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 上午11:12:35</p>
 * <p>类全名：util.MyFileFilter</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MyFileFilter implements FileFilter
{
	private Hashtable	filters						= null;
	private String		description					= null;
	private String		fullDescription				= null;
	private boolean		useExtensionsInDescription	= true;

	public MyFileFilter()
	{
		this.filters = new Hashtable();
	}

	public MyFileFilter(String extension)
	{
		this(extension, null);
	}

	public MyFileFilter(String extension, String description)
	{
		this();
		if (extension != null)
		{
			addExtension(extension);
		}
		if (description != null)
		{
			setDescription(description);
		}
	}

	public MyFileFilter(String[] filters)
	{
		this(filters, null);
	}

	public MyFileFilter(String[] filters, String description)
	{
		this();
		for (int i = 0; i < filters.length; i++)
		{
			// add filters one by one
			addExtension(filters[i]);
		}
		if (description != null)
		{
			setDescription(description);
		}
	}

	@Override
	public boolean accept(File f)
	{
		if (f != null)
		{
			if (f.isDirectory())
			{
				return true;
			}
			String extension = getExtension(f);
			if (extension != null && filters.get(getExtension(f)) != null)
			{
				return true;
			}
			;
		}
		return false;
	}

	public String getExtension(File f)
	{
		if (f != null)
		{
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1)
			{
				return filename.substring(i + 1).toLowerCase();
			}
			;
		}
		return null;
	}

	public void addExtension(String extension)
	{
		if (filters == null)
		{
			filters = new Hashtable(5);
		}
		filters.put(extension.toLowerCase(), this);
		fullDescription = null;
	}

	public String getDescription()
	{
		if (fullDescription == null)
		{
			if (description == null || isExtensionListInDescription())
			{
				fullDescription = description == null ? "(" : description + " (";
				// build the description from the extension list
				Enumeration extensions = filters.keys();
				if (extensions != null)
				{
					fullDescription += "." + (String) extensions.nextElement();
					while (extensions.hasMoreElements())
					{
						fullDescription += ", ." + (String) extensions.nextElement();
					}
				}
				fullDescription += ")";
			} else
			{
				fullDescription = description;
			}
		}
		return fullDescription;
	}

	public void setDescription(String description)
	{
		this.description = description;
		fullDescription = null;
	}

	public void setExtensionListInDescription(boolean b)
	{
		useExtensionsInDescription = b;
		fullDescription = null;
	}

	public boolean isExtensionListInDescription()
	{
		return useExtensionsInDescription;
	}
}
