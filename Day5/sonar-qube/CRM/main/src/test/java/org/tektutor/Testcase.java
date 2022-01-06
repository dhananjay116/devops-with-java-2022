package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class Testcase {

	@Test
	public void testCase1() {

		Main obj = new Main();

		String actualResponse = obj.getModuleName();
		String expectedResponse = "Main Module";

		assertEquals ( expectedResponse, actualResponse );

	}
}
