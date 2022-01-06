package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class Testcase {

	@Test
	public void testCase1() {

		BusinessLayer bl = new BusinessLayer();

		String actualResponse = bl.getModuleName();
		String expectedResponse = "BusinessLayer Module";

		assertEquals ( expectedResponse, actualResponse );

	}
}
