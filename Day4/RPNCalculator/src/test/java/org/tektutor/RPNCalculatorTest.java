package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class RPNCalculatorTest {

	private double actualResult, expectedResult;

	@Test
	public void testSimpleAddition() {
		RPNCalculator rpnCalculator = new RPNCalculator();

		actualResult = rpnCalculator.evaluate ( "10 15 +" );
		expectedResult = 25.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );

		actualResult = rpnCalculator.evaluate ( "100 25 +" );
		expectedResult = 125.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );
	}

	@Test
	public void testSimpleSubtraction() {
		RPNCalculator rpnCalculator = new RPNCalculator();

		actualResult = rpnCalculator.evaluate ( "100 15 -" );
		expectedResult = 85.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );

		actualResult = rpnCalculator.evaluate ( "100 25 -" );
		expectedResult = 75.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );
	}

	@Test
	public void testSimpleMultiplication() {
		RPNCalculator rpnCalculator = new RPNCalculator();

		actualResult = rpnCalculator.evaluate ( "100 15 *" );
		expectedResult = 1500.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );

		actualResult = rpnCalculator.evaluate ( "100 25 *" );
		expectedResult = 2500.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );
	}

	@Test
	public void testSimpleDivision() {
		RPNCalculator rpnCalculator = new RPNCalculator();

		actualResult = rpnCalculator.evaluate ( "100 10 /" );
		expectedResult = 10.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );

		actualResult = rpnCalculator.evaluate ( "100 25 /" );
		expectedResult = 4.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );
	}

	@Test
	public void testComplexRPNMathExpression() {
		RPNCalculator rpnCalculator = new RPNCalculator();

		actualResult = rpnCalculator.evaluate ( "5 20 * 100 5 / -" );
		expectedResult = 80.0;
		assertEquals ( expectedResult, actualResult, 0.0001 );
	}

}
