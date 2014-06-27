package com.tutorialspoint.struts2;

public class HelloWorldAction {

	public String execute()
	{
		System.out.println("Hello From execute.");
//		return "success";
//		return "failure";
		return "eventDropdown";
	}
}
