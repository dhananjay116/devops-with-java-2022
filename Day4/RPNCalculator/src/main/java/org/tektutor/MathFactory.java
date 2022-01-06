package org.tektutor;

import java.util.Map;
import java.util.HashMap;

public class MathFactory {
	private static Map<String,String> mathOperatorToClassNameMap = new HashMap<String,String>();

	static {
		mathOperatorToClassNameMap.put ( "+", "org.tektutor.Addition" );
		mathOperatorToClassNameMap.put ( "-", "org.tektutor.Subtraction" );
		mathOperatorToClassNameMap.put ( "*", "org.tektutor.Multiplication" );
		mathOperatorToClassNameMap.put ( "/", "org.tektutor.Division" );
	}

	public static IMathOperation getMathObject ( String mathOperator ) {
		IMathOperation mathOperation = null;

		String className = mathOperatorToClassNameMap.get ( mathOperator );

		try {
			mathOperation = (IMathOperation) Class.forName( className ).newInstance();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}

		return mathOperation;
	}

}
