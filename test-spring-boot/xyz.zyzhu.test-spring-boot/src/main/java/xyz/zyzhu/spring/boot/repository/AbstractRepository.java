package xyz.zyzhu.spring.boot.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.http.util.Asserts;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月6日 下午10:39:15</p>
 * <p>类全名：xyz.zyzhu.spring.boot.repository.AbstractRepository</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0

 */
public  abstract class  AbstractRepository<M extends BasModel,I extends Serializable> extends SimpleJpaRepository<M,I>{
	protected EntityManagerFactory readFactory;
	protected EntityManagerFactory writeFactory;
	protected EntityManager readManager;
	protected EntityManager writeManager;
	public AbstractRepository(Class<M> clazz,EntityManager manager)
	{
		this(clazz, manager, null, null);
	}
	
	public AbstractRepository(Class<M> clazz,EntityManager manager,EntityManagerFactory readFactory,EntityManagerFactory writeFactory)
	{
		super(clazz, manager);
		if(readFactory == null)
		{
			readFactory = SpringBeanUtils.getBean("readEntityManagerFactory", EntityManagerFactory.class);
		}
		if(writeFactory == null)
		{
			writeFactory = SpringBeanUtils.getBean("writeEntityManagerFactory", EntityManagerFactory.class);
		}
		Asserts.notNull(writeFactory, "writeFactory");
		Asserts.notNull(readFactory, "readFactory");
		readManager = readFactory.createEntityManager();
		writeManager = writeFactory.createEntityManager();
		this.readFactory = readFactory;
		this.writeFactory = writeFactory;
	}
	
}
