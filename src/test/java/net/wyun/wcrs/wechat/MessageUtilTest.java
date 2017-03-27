package net.wyun.wcrs.wechat;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MessageUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String evtKey = "qrscene_2";
		int parent = MessageUtil.parseEventKey(evtKey);
		assertEquals(2, parent);
	}

}
