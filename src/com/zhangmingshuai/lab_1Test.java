package com.zhangmingshuai;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class lab_1Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJudge0() 
	{
		f F = new f();
		assertEquals(true,F.Judge("2*x*2*y+32*z"));
	}
	@Test
	public void testJudge1() 
	{
		f F = new f();
		assertEquals(false,F.Judge("x^y"));
	}
	@Test
	public void testJudge2() 
	{
		f F = new f();
		assertEquals(false,F.Judge("4*xy-2%"));
	}
	@Test
	public void testJudge3() 
	{
		f F = new f();
		assertEquals(false,F.Judge("2^2+3*x"));
	}
	
	@Test
	public void testDerivative0() 
	{
		f F = new f();
		F.Judge("2*x*y+32*x");
		F.expression();
		assertEquals(true,F.derivative("!d/dx"));
	}
	@Test
	public void testDerivative1() 
	{
		f F = new f();
		F.Judge("2*x*y+32*x");
		F.expression();
		assertEquals(true,F.derivative("!d/dy"));
	}
	@Test
	public void testDerivative2() 
	{
		f F = new f();
		F.Judge("2*x*y+32*x");
		F.expression();
		assertEquals(true,F.derivative("!d/dz"));
	}

	@Test
	public void testDerivative3() 
	{
		f F = new f();
		F.Judge("2*x^3+2");
		F.expression();
		assertEquals(true,F.derivative("!d/dx"));
	}

	@Test
	public void testDerivative4() 
	{
		f F = new f();
		F.Judge("2*x^3+2");
		F.expression();
		assertEquals(false,F.derivative("!y/yxz"));
	}


	

}
