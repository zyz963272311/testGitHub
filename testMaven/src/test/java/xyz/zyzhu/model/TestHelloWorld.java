package xyz.zyzhu.model;

import org.junit.*;
import org.junit.Assert.*;

public class TestHelloWorld
{
	@Test
	public void tesySayHello()
	{
		Assert.assertEquals("hello world",new HelloWorld().sayHello());
	}
}