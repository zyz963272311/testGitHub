
package xyz.zyzhu.comm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import xyz.zyzhu.util.Dom4jUtil;
import xyz.zyzhu.util.StrUtil;

/**
 * <p>标题： WorkSpace</p>
 * <p>内容： WorkSpace</p>
 * <p>创建时间： 2017年4月25日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.comm.WorkSpace</p>
 * <p>作者： Administrator</p>
 */
public class WorkSpace
{

	private String url;
	private String name;
	private String pass;
	private String id;
	private String read;
	private String write;
	private String type;

	public WorkSpace(String id) {
		Map<String, String> wkSpace = getWorkSpace(id);
		String _id = wkSpace.get("id");
		String _url = wkSpace.get("url");
		String _name = wkSpace.get("name");
		String _pass = wkSpace.get("pass");
		String _read = wkSpace.get("read");
		String _write = wkSpace.get("write");
		String _type = wkSpace.get("type");
		setId(_id);
		setUrl(_url);
		setName(_name);
		setPass(_pass);
		setRead(_read);
		setWrite(_write);
		setType(_type);
	}
	private static Logger logger = Logger.getLogger(WorkSpace.class);

	@SuppressWarnings("unchecked")
	public static Map<String, String> getWorkSpace(String workSpaceID)
	{
		Map<String, String> workspace = new HashMap<String, String>();
		if (StrUtil.isStrNull(workSpaceID))
		{
			logger.error("workSpaceID为空");
			return workspace;
		}
		String xmlFilePath = System.getProperty("user.dir") + "/src/main/resources/workspace.xml";
		Document document = Dom4jUtil.getXmlRead(xmlFilePath);
		Element root = document.getRootElement();
		for (Iterator<Element> iter = root.elementIterator("workspace"); iter.hasNext();)
		{
			Element work = iter.next();
			String id = work.attributeValue("id");
			if (StrUtil.isStrNull(id))
			{
				continue;
			}
			if (id.equals(workSpaceID))
			{
				String url = work.attributeValue("url");
				String name = work.attributeValue("name");
				String pass = work.attributeValue("pass");
				String write = work.attributeValue("write");
				String read = work.attributeValue("read");
				String type = work.attributeValue("type");
				workspace.put("url", url);
				workspace.put("id", id);
				workspace.put("name", name);
				workspace.put("pass", pass);
				workspace.put("write", write);
				workspace.put("read", read);
				workspace.put("type", type);
				logger.info(workspace);
			}
		}
		return workspace;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getRead()
	{
		return read;
	}

	public void setRead(String read)
	{
		this.read = read;
	}

	public String getWrite()
	{
		return write;
	}

	public void setWrite(String write)
	{
		this.write = write;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public static void main(String[] args)
	{
		System.out.println(System.getProperty("user.dir"));
		getWorkSpace("conf");
	}

	public String getPass()
	{
		return pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}
}
