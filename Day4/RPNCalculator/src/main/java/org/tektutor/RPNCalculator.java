package org.tektutor;

import java.util.Stack;

public class RPNCalculator {

	private double firstNumber, secondNumber, result;
	private Stack<Double> numberStack;

	public RPNCalculator() {
		firstNumber = secondNumber = result = 0.0;
		numberStack = new Stack<Double>();
	}

	private void extractInputs() {
		secondNumber = numberStack.pop();
		firstNumber = numberStack.pop();
	}

	private boolean isMathOperator ( String rpnToken ) {
		String mathOperators = "+-*/";
		return mathOperators.contains ( rpnToken );
	}

	public double evaluate ( String rpnMathExpression ) {
		String[] rpnTokens = rpnMathExpression.split ( " " );

		IMathOperation mathOperation = null;

		for ( String token : rpnTokens ) {
			if ( isMathOperator ( token)  ) {
				extractInputs();
				mathOperation = MathFactory.getMathObject ( token );
				result = mathOperation.evaluate ( firstNumber, secondNumber );
				numberStack.push ( result );
			}
			else
				numberStack.push ( Double.parseDouble( token ) );
		}

		return numberStack.pop();
	}

}
