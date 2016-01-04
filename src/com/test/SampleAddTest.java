package com.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SampleAddTest {
	int x = 0;
	int y = 0;
	int z = 0;
	
	@Before
	public void setUp() {
		z = add(3,2);
	}

	@Test
	public void testAdd() {
		Assert.assertEquals(6, add(3,2));
	}

	@After public void settleDown() { x = 0; }
	
	public int add(int x, int y) {
		return x + y;
	}
}

