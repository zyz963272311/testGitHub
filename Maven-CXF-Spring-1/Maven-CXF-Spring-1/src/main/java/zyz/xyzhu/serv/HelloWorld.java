package zyz.xyzhu.serv;

import javax.jws.WebService;



/**
 * <p>���⣺ HelloWorld</p>
 * <p>���ݣ� HelloWorld</p>
 * <p>����ʱ�䣺 2017��3��21��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ����zyz.xyzhu.serv.HelloWorld</p>
 * <p>���ߣ� Administrator</p>
 */
// @WebService(endpointInterface = "zyz.xyzhu.serv.HelloWorld")
@WebService
public interface HelloWorld
{
	String sayHello();
}
