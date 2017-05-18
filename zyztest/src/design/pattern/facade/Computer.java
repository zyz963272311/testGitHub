package design.pattern.facade;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月23日 下午4:48:40</p>
 * <p>类全名：design.pattern.facade.Computer</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Computer
{
	private final CPU		cpu;
	private final Memory	memory;
	private final Disk		disk;

	public Computer()
	{
		this.cpu = new CPU();
		this.memory = new Memory();
		this.disk = new Disk();
	}

	public void startup()
	{
		System.out.println("cunputer is startup");
		this.cpu.startup();
		this.memory.startup();
		this.disk.startup();
	}

	public void shutdown()
	{
		this.cpu.shutdown();
		this.memory.shutdown();
		this.disk.shutdown();
		System.out.println("cunputer is shutdown");
	}
}
