package xyz.zyzhu.demo;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * <p>���⣺ HelloWorld</p>
 * <p>���ݣ� HelloWorld</p>
 * <p>����ʱ�䣺 2017��3��4��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� xyz.zyzhu.demo.HelloWorld</p>
 * <p>���ߣ� Administrator</p>
 */
@WebService
public interface HelloWorld
{

	String say(@WebParam(name = "text") String text);

	String sayHi2User(User user);

	String[] sayHiToUserList(List<User> users);
}
