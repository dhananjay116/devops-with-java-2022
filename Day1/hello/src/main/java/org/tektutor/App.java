package org.tektutor;

public class App {

	public String sayHello() {
		return "Hello DevOps !";
	}

	public static void main ( String[] args ) {

		App hello = new App();
		System.out.println ( hello.sayHello() );

	}

}	
