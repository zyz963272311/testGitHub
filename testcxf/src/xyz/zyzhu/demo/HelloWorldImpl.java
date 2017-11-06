package xyz.zyzhu.demo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;

/**
 * <p>标题： HelloWorldImpl</p>
 * <p>内容： HelloWorldImpl</p>
 * <p>创建时间： 2017年3月4日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.demo.HelloWorldImpl</p>
 * <p>作者： Administrator</p>
 */
@WebService(endpointInterface = "xyz.zyzhu.demo.HelloWorld", serviceName = "helloWorld")
public class HelloWorldImpl implements HelloWorld
{

	Map<Integer, User> users = new LinkedHashMap<Integer, User>();

	public String say(String text)
	{
		return "say:" + text;
	}

	public String sayHi2User(User user)
	{
		users.put(users.size() + 1, user);
		return "hello:" + user.getName();
	}

	public String[] sayHiToUserList(List<User> users)
	{
		String[] result = new String[users.size()];
		int i = 0;
		for (User user : users)
		{
			result[i] = "hello:" + user.getName();
			i++;
		}
		return result;
	}
}
