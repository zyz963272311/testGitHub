package design.pattern.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import design.pattern.abstractfactory.Provider;
import design.pattern.abstractfactory.QQSenderFactory;
import design.pattern.abstractfactory.Sender;
import design.pattern.adapter.method.Adapter;
import design.pattern.adapter.method.Target;
import design.pattern.adapter.object.Source;
import design.pattern.adapter.object.Wrapper;
import design.pattern.bridge.IJDBC;
import design.pattern.bridge.JDBCManager;
import design.pattern.bridge.MySQL;
import design.pattern.bridge.MySQLManager;
import design.pattern.builder.Director;
import design.pattern.builder.Product;
import design.pattern.combination.Tree;
import design.pattern.combination.TreeNode;
import design.pattern.command.Command;
import design.pattern.command.Invoke;
import design.pattern.command.Receive;
import design.pattern.decorator.Source1;
import design.pattern.decorator.Source2;
import design.pattern.decorator.Sourceable;
import design.pattern.facade.Computer;
import design.pattern.factory.SenderFactory;
import design.pattern.flyweight.FlyOrderFactory;
import design.pattern.flyweight.Order;
import design.pattern.iterator.Collection;
import design.pattern.iterator.Iterator;
import design.pattern.iterator.MyCollection;
import design.pattern.memento.Origin;
import design.pattern.memento.Storage;
import design.pattern.oberver.Observer;
import design.pattern.oberver.ObserverA;
import design.pattern.oberver.ObservreB;
import design.pattern.oberver.SubjectA;
import design.pattern.oberver.SubjectAbstract;
import design.pattern.prototype.shollow.Shollow;
import design.pattern.proxy.cglib.ProxyFactory;
import design.pattern.proxy.stic.IUserDao;
import design.pattern.proxy.stic.UserDao;
import design.pattern.proxy.stic.UserDaoProxy;
import design.pattern.responsibility.MyHandler;
import design.pattern.singleton.Singleton;
import design.pattern.state.Context;
import design.pattern.state.State;
import design.pattern.strategy.Diamonds;
import design.pattern.strategy.Golden;
import design.pattern.strategy.Normal;
import design.pattern.strategy.PriceStrategy;
import design.pattern.templete.Day;
import design.pattern.templete.ProgramerDay;
import design.pattern.visitor.MySubject;
import design.pattern.visitor.MyVisitor;
import design.pattern.visitor.Subject;
import design.pattern.visitor.Visitor;
/**
 * <p>标题： 测试23种java设计模式</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月22日 上午10:09:56</p>
 * <p>类全名：design.pattern.test.TestDesignPattern</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestDesignPattern
{
	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException, InterruptedException
	{
		System.out.println("开始测试");
		//testFactory();
		//testAbstractFactory();
		//testSingleton();
		//testBuilder();
		//testPrototype();
		//testAdapterMethod();
		//testAdapterObject();
		//testAdapterInterface();
		//testDecorator();
		//testStaticProxy();
		//testDynamicProxy();
		//testFaced();
		//testBridge();
		//tectCombination();
		//testFlyweight();
		//testStrategy();
		//testTemplete();
		//testObserver();
		//testIterator();
		//testResponsiblity();
		//testCommand();
		//testMemento();
		//testState();
		testVisitor();
		System.out.println("测试结束");
	}

	private static void testFactory()
	{
		System.out.println("测试工厂类");
		SenderFactory senderFactory = new SenderFactory();
		design.pattern.factory.Sender sender = senderFactory.sendQQ();
		sender.send();
		System.out.println("测试工厂类结束");
	}

	private static void testAbstractFactory()
	{
		System.out.println("测试抽象工厂类");
		Provider provider = new QQSenderFactory();
		Sender sender = provider.produce();
		sender.send();
		System.out.println("测试抽象工厂类结束");
	}

	private static void testSingleton()
	{
		System.out.println("测试单例模式");
		Singleton singleton = Singleton.getInstance();
		System.out.println("测试单例模式结束");
	}

	private static void testBuilder()
	{
		Product product;
		System.out.println("测试建造者模式");
		Director director = new Director();
		product = director.getCarAProduct();
		System.out.println(product.toString());
		System.out.println("测试建造者模式结束");
	}

	private static void testPrototype() throws CloneNotSupportedException, ClassNotFoundException, IOException
	{
		//textPrototypeShollow();
		testPrototypeDeep();
	}

	/**
	 * 
	 * x250-2
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private static void testPrototypeDeep() throws ClassNotFoundException, IOException
	{
		System.out.println("测试原型模式深拷贝");
		Shollow shollow = new Shollow();
		shollow.setA(123);
		shollow.setB("赵玉柱");
		shollow.setC(new int[] { 1, 2, 3, 4 });
		Shollow shollow_copy = shollow.deepCopy();
		System.out.println(shollow.toString());
		System.out.println(shollow_copy.toString());
		shollow.setB("ewqewqewq");
		System.out.println(shollow.toString());
		System.out.println(shollow_copy.toString());
		System.out.println("测试原型模式深拷贝结束");
	}

	/**
	 * @throws CloneNotSupportedException
	 * x250-2
	 */
	private static void textPrototypeShollow() throws CloneNotSupportedException
	{
		System.out.println("测试原型模式浅拷贝");
		Shollow shollow = new Shollow();
		shollow.setA(123);
		shollow.setB("赵玉柱");
		shollow.setC(new int[] { 1, 2, 3, 4 });
		Shollow shollow_copy = (Shollow) shollow.clone();
		System.out.println(shollow.toString());
		System.out.println(shollow_copy.toString());
		shollow.setB("ewqewqewq");
		System.out.println(shollow.toString());
		System.out.println(shollow_copy.toString());
		System.out.println("测试原型模式浅拷贝结束");
	}

	private static void testAdapterMethod()
	{
		System.out.println("测试适配器方法");
		Target target = new Adapter();
		target.method1();
		target.method2();
		System.out.println("测试适配器方法结束");
	}

	private static void testAdapterObject()
	{
		System.out.println("测试适配器对象");
		Source source = new Source();
		design.pattern.adapter.object.Target target = new Wrapper(source);
		target.method1();
		target.method2();
		System.out.println("测试适配器对象结束");
	}

	private static void testAdapterInterface()
	{
		System.out.println("测试适配器接口");
		design.pattern.adapter.inter.Target target = new design.pattern.adapter.inter.Source();
		target.method1();
		System.out.println("测试适配器接口结束");
	}

	private static void testDecorator()
	{
		System.out.println("测试装饰模式");
		Sourceable sourceable = new Source1();
		Sourceable sourceable2 = new Source2(sourceable);
		sourceable2.method1();
		System.out.println("测试装饰模式结束");
	}

	private static void testStaticProxy()
	{
		System.out.println("测试静态代理模式");
		IUserDao userDao = new UserDao();
		IUserDao userDaoProxy = new UserDaoProxy(userDao);
		userDaoProxy.save();
		System.out.println("测试静态代理模式结束");
	}

	private static void testDynamicProxy()
	{
		System.out.println("测试动态代理模式");
		design.pattern.proxy.dynamic.IUserDao iUserDao = new design.pattern.proxy.dynamic.UserDao();
		design.pattern.proxy.dynamic.IUserDao proxy = (design.pattern.proxy.dynamic.IUserDao) new design.pattern.proxy.dynamic.UserDaoProxy(iUserDao).getProxyInstance();
		proxy.save();
		System.out.println("测试动态代理模式结束");
	}

	private static void testCglibProxy()
	{
		System.out.println("测试CGLIB代理模式");
		design.pattern.proxy.cglib.UserDao userDao = new design.pattern.proxy.cglib.UserDao();
		UserDao proxy = (UserDao) new ProxyFactory(userDao).getInstance();
		proxy.save();
		System.out.println("测试CGLIB代理模式结束");
	}

	private static void testFaced() throws InterruptedException
	{
		System.out.println("测试外观模式");
		Computer computer = new Computer();
		computer.startup();
		Thread.sleep(new Random().nextInt(1000));
		computer.startup();
		System.out.println("测试外观模式结束");
	}

	private static void testBridge()
	{
		System.out.println("测试桥接模式");
		IJDBC jdbc = new MySQL();
		JDBCManager manager = new MySQLManager(jdbc);
		manager.getConn();
		manager.updqte("31");
		System.out.println("测试桥接模式结束");
	}

	private static void tectCombination()
	{
		System.out.println("测试组合模式");
		Tree tree = new Tree("树");
		TreeNode rmnd = null;
		for (int i = 0; i < 10; i++)
		{
			TreeNode node = new TreeNode("node" + i);
			if (i == 5)
			{
				rmnd = node;
			}
			tree.getRoot().add(node);
		}
		Enumeration<TreeNode> childen = tree.getRoot().getChilden();
		while (childen.hasMoreElements())
		{
			System.out.println(childen.nextElement().toString());
		}
		System.out.println("tree[" + tree.toString() + "]");
		System.out.println("==========================");
		tree.getRoot().remove(rmnd);
		childen = tree.getRoot().getChilden();
		while (childen.hasMoreElements())
		{
			System.out.println(childen.nextElement().toString());
		}
		System.out.println("测试组合模式结束");
	}

	private static void testFlyweight()
	{
		System.out.println("测试亨元模式");
		FlyOrderFactory factory = FlyOrderFactory.getInstance();
		List<Order> orders = new ArrayList<Order>();
		takeOrder(factory, orders, "拿铁");
		takeOrder(factory, orders, "猫屎");
		takeOrder(factory, orders, "卡布奇诺");
		takeOrder(factory, orders, "拿铁");
		takeOrder(factory, orders, "黑咖");
		takeOrder(factory, orders, "黑咖");
		takeOrder(factory, orders, "拿铁");
		takeOrder(factory, orders, "猫屎");
		takeOrder(factory, orders, "猫屎");
		takeOrder(factory, orders, "卡布奇诺");
		takeOrder(factory, orders, "猫屎");
		for (Order order : orders)
		{
			order.sell();
		}
		System.out.println("一共卖了" + orders.size() + "杯咖啡");
		System.out.println("一共生成" + factory.getFlyOrderMade() + "次咖啡");
		System.out.println("测试亨元模式结束");
	}

	private static void takeOrder(FlyOrderFactory factory, List<Order> orders, String fly)
	{
		orders.add(factory.getOrder(fly));
	}

	private static void testStrategy()
	{
		System.out.println("测试策略模式");
		PriceStrategy strategy = new PriceStrategy(new Golden());
		System.out.println(strategy.realPrice(1024));
		PriceStrategy strategy1 = new PriceStrategy(new Normal());
		System.out.println(strategy1.realPrice(1024));
		PriceStrategy strategy2 = new PriceStrategy(new Diamonds());
		System.out.println(strategy2.realPrice(1024));
		System.out.println("测试策略模式结束");
	}

	private static void testTemplete()
	{
		System.out.println("测试模版方法模式");
		Day day = new ProgramerDay();
		day.process();
		System.out.println("测试模版方法模式结束");
	}

	private static void testObserver()
	{
		System.out.println("测试观察者模式");
		SubjectAbstract subject = new SubjectA();
		Observer oA1 = new ObserverA("oA1");
		Observer oA2 = new ObserverA("oA2");
		Observer oA3 = new ObserverA("oA3");
		Observer oA4 = new ObserverA("oA4");
		Observer oB1 = new ObservreB("oB1");
		Observer oB2 = new ObservreB("oB2");
		Observer oB3 = new ObservreB("oB3");
		Observer oB4 = new ObservreB("oB4");
		subject.add(oA1);
		subject.add(oA2);
		subject.add(oA3);
		subject.add(oA4);
		subject.add(oB1);
		subject.add(oB2);
		subject.add(oB3);
		subject.add(oB4);
		System.out.println("测试不输出");
		subject.option(null);
		System.out.println("测试不输出完成");
		subject.option("今天是个好日子");
		System.out.println("测试删除oB1");
		subject.delete(oB1);
		subject.option("删除了oB1");
		System.out.println("测试观察者模式结束");
	}

	private static void testIterator()
	{
		System.out.println("测试迭代子模式");
		Collection collection = new MyCollection(new Object[] { 1, 2, 3, 4, 5 });
		Iterator iterator = collection.iterator();
		System.out.println("测试迭代器");
		while (iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
		System.out.println("测试迭代器结束");
		System.out.println("测试first" + iterator.first());
		System.out.println("测试迭代子模式结束");
	}

	private static void testResponsiblity()
	{
		System.out.println("测试责任链模式");
		MyHandler handler1 = new MyHandler("handler1");
		MyHandler handler2 = new MyHandler("handler2");
		MyHandler handler3 = new MyHandler("handler3");
		MyHandler handler4 = new MyHandler("handler4");
		MyHandler handler5 = new MyHandler("handler5");
		handler1.setHandler(handler2);
		handler2.setHandler(handler3);
		handler3.setHandler(handler4);
		handler4.setHandler(handler5);
		handler1.operate();
		System.out.println("测试责任链模式结束");
	}

	private static void testCommand()
	{
		System.out.println("测试命令模式");
		Receive receive = new Receive();
		Command command = new Command(receive);
		Invoke invoke = new Invoke(command);
		invoke.action();
		System.out.println("测试命令模式结束");
	}

	private static void testMemento()
	{
		System.out.println("测试备忘录模式");
		//创建原始类
		Origin origin = new Origin("egg");
		//创建备忘录,通过源创建备忘录
		Storage storage = new Storage(origin.createMemento());
		//修改原始状态
		System.out.println("原始状态为" + origin.getValue());
		origin.setValue("double eggs");
		System.out.println("修改后状态为" + origin.getValue());
		System.out.println("还原状态");
		origin.restoreMemento(storage.getMemento());
		System.out.println("还原后状态为" + origin.getValue());
		System.out.println("测试备忘录模式结束");
	}

	private static void testState()
	{
		System.out.println("测试状态模式");
		State state = new State("invoke1");
		Context context = new Context(state);
		context.method("invoke1");
		System.out.println("测试状态模式结束");
	}

	private static void testVisitor()
	{
		System.out.println("测试访问者模式");
		Visitor visitor = new MyVisitor();
		Subject subject = new MySubject();
		subject.accept(visitor);
		System.out.println("测试访问者模式结束");
	}
}
