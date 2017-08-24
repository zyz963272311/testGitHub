package xyz.zyzhu.initlistener;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.liiwin.config.BasConfig;
import com.liiwin.db.Database;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 服务加载的时候发布WebService</p>
 * <p>功能：</p>
 * <p>所属模块： TestSpring</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月11日 上午9:56:19</p>
 * <p>类全名：xyz.zyzhu.initlistener.LoadWebServiceImpl</p>
 * 作者：赵玉柱 
 * 初审： 
 * 复审： 
 * 监听使用界面:
 * 
 * @version 8.0
 */
@Component("LoadWebServiceImpl")
public class LoadWebServiceImpl implements ServletContextListener
{
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
	}

	/**
	 * 发布对外提供的接口
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		System.out.println("contextInitialized");
		System.out.println("当前目录--" + this.getClass().getClassLoader().getResource("/"));
		//		Database database = new Database("project01");
		Database database = new Database("zyztest");
		StringBuffer sql = new StringBuffer();
		sql.append("select ")//
				.append(" servicename")//
				.append(",serviceinterface")//
				.append(",serviceimpl ")//
				.append(" from webservice")//
				.append(" where usedflags=1 ");
		String webserviceHost = BasConfig.getPropertie("webservice-host");
		List<Map<String,Object>> webServiceList = database.getListMapFromSql(sql.toString());
		if (webServiceList != null && StrUtil.isNoStrTrimNull(webserviceHost))
		{
			for (Map<String,Object> webServiceMap : webServiceList)
			{
				String servicename = StrUtil.obj2str(webServiceMap.get("servicename"));
				// String serviceinterface =
				// StrUtil.obj2str(webServiceMap.get("serviceinterface"));
				String serviceimpl = StrUtil.obj2str(webServiceMap.get("serviceimpl"));
				try
				{
					Endpoint.publish(webserviceHost + "/" + servicename, Class.forName(serviceimpl).newInstance());
					System.out.println(servicename + "发布成功URL是" + webserviceHost + "/" + servicename + ",实现类是" + serviceimpl);
				} catch (ClassNotFoundException e)
				{
					System.out.println(servicename + "发布失败");
					continue;
				} catch (InstantiationException e)
				{
					throw new RuntimeException("报错内容", e);
				} catch (IllegalAccessException e)
				{
					throw new RuntimeException("报错内容", e);
				}
			}
		}
	}

	public static void main(String[] args)
	{
		LoadWebServiceImpl impl = new LoadWebServiceImpl();
		impl.contextInitialized(null);
	}

	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		System.out.println(this.getClass().getClassLoader().getResource("/"));
		System.out.println("spring容易初始化完毕================================================");
		System.out.println("当前目录--" + System.getProperty("user.dir"));
		System.out.println("当前目录--" + System.getProperty("web.root"));
	}
}
