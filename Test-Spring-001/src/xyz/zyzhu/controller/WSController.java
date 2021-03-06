package xyz.zyzhu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.zyzhu.pojo.BasRequestPojo;
import xyz.zyzhu.pojo.BasResponsePojo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liiwin.db.Database;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： Webservice 服務端Controller</p>
 * <p>功能：</p>
 * <p>所属模块： Test-Spring</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月12日 下午12:31:31</p>
 * <p>类全名：xyz.zyzhu.controller.WSController</p>
 * 作者：赵玉柱 
 * 初审： 
 * 复审： 
 * 监听使用界面:
 * 
 * @version 8.0
 */
@Component()
@RequestMapping("/api")
public class WSController
{
	@RequestMapping("/executeApi")
	public void executeApi(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("调用WSController.executeApi");
		InputStream in = null;
		String requestClass = request.getHeader("requestClass");
		String result = null;
		BasResponsePojo responsePojo = null;
		OutputStream out = null;
		try
		{
			in = request.getInputStream();
			byte[] respBytes = new byte[1024];
			int len = 0;
			String req = "";
			while ((len = in.read(respBytes)) > -1)
			{
				req += new String(respBytes, 0, len, "utf-8");
			}
			BasRequestPojo requestPojo = (BasRequestPojo) JSONObject.parseObject(req, Class.forName(requestClass));
			if (requestPojo != null)
			{
				Database database = new Database("project01");
				//				Database database = new Database("zyztest");
				StringBuffer sql = new StringBuffer();
				sql.append("select ")//
						.append(" m.servicename as servicename")//
						.append(",m.serviceinterface as serviceinterface")//
						.append(",m.serviceimpl as serviceimpl ")//
						.append(",g.method as  method ")//
						.append(" from webservice m,webmethod g")//
						.append(" where usedflags=1 ")//
						.append(" and apiname='" + requestPojo.getApiName() + "'")//
						.append(" and m.servicename=g.servicename");
				List<Map<String,Object>> webServiceList = database.getListMapFromSql(sql.toString());
				if (webServiceList == null || webServiceList.isEmpty())
				{
					throw new RuntimeException("根据apiname[" + requestPojo.getApiName() + "]为找到对应的WebService");
				}
				Map<String,Object> webserviceMap = webServiceList.get(0);
				String serviceimplName = StrUtil.obj2str(webserviceMap.get("serviceimpl"));
				String methodName = StrUtil.obj2str(webserviceMap.get("method"));
				Class<?> serviceimpl = Class.forName(serviceimplName);
				Method method = serviceimpl.getDeclaredMethod(methodName, requestPojo.getClass());
				responsePojo = (BasResponsePojo) method.invoke(serviceimpl.newInstance(), requestPojo);
				result = JSON.toJSONString(responsePojo);
			}
		} catch (Exception e)
		{
			responsePojo = new BasResponsePojo();
			responsePojo.setMessage(e.getMessage());
			responsePojo.setStatus("FALSE");
			responsePojo.setCode("500");
			result = JSON.toJSONString(responsePojo);
		} finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					throw new RuntimeException("报错内容", e);
				}
			}
			try
			{
				out = response.getOutputStream();
				out.write(result.getBytes("utf-8"));
				out.flush();
			} catch (IOException e)
			{
				throw new RuntimeException("报错内容", e);
			} finally
			{
				if (out != null)
				{
					try
					{
						out.close();
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						throw new RuntimeException("报错内容", e);
					}
				}
			}
		}
		response.setHeader("responseJson", result);
		System.out.println(result);
	}
}
