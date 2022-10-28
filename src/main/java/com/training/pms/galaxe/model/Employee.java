package com.training.pms.galaxe.model;

import lombok.Data;

@Data
public class Employee {

	int EmployeeId;
	String EmployeeName;
	int salary;
	int pf;
	int bonus;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [EmployeeId=" + EmployeeId + ", EmployeeName=" + EmployeeName + ", salary=" + salary + ", pf="
				+ pf + ", bonus=" + bonus + "]";
	}

	
}
