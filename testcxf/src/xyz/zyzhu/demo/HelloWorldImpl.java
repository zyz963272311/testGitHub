package xyz.zyzhu.demo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;

/**
 * <p>���⣺ HelloWorldImpl</p>
 * <p>���ݣ� HelloWorldImpl</p>
 * <p>����ʱ�䣺 2017��3��4��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� xyz.zyzhu.demo.HelloWorldImpl</p>
 * <p>���ߣ� Administrator</p>
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
