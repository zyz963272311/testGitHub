package xyz.zyzhu.demo;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * <p>标题： HelloWorld</p>
 * <p>内容： HelloWorld</p>
 * <p>创建时间： 2017年3月4日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.demo.HelloWorld</p>
 * <p>作者： Administrator</p>
 */
@WebService
public interface HelloWorld
{

	String say(@WebParam(name = "text") String text);

	String sayHi2User(User user);

	String[] sayHiToUserList(List<User> users);
}
