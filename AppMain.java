package com.corejava.entity;


	import java.util.List;
	import java.util.Scanner;

	public class AppMain {
		
	public static void main(String[] args) {
	//update a row
	EmployeeServlet daoImp=new EmployeeServlet();
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the Id to update");
	int id=sc.nextInt();
	System.out.println("Enter the name to update");
	String name=sc.next();
	System.out.println("Enter the age to update");
	int age=sc.nextInt();
	System.out.println("Enter the salary to update");
	int salary=sc.nextInt();
	Employee emp=new Employee();
	emp.setEname(name);
	emp.setAge(age);
	emp.setSalary(salary);
	emp.setId(id);
	if(daoImp.updateEmployee(emp)) {
	System.out.println("Updated the row ");
	}
	else {
	System.out.println("Not Updated");
	}
	}
	
}
}