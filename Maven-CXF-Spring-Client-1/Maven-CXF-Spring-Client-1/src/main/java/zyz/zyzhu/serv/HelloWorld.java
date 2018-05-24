package zyz.zyzhu.serv;

import javax.jws.WebService;


/**
 * <p>���⣺ HelloWorld</p>
 * <p>���ݣ� HelloWorld</p>
 * <p>����ʱ�䣺 2017��3��21��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� xyz.zyzhu.serv.HelloWorld</p>
 * <p>���ߣ� Administrator</p>
 */
@WebService(targetNamespace = "http://serv.xyzhu.zyz/")
public interface HelloWorld
{

	public String sayHello();
}
