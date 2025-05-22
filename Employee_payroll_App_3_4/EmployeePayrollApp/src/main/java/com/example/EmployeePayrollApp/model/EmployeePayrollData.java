package com.example.EmployeePayrollApp.model;

import lombok.Data; 
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class EmployeePayrollData {
	private long employeeId;
    private String name;
    private long salary;
    
    
    public EmployeePayrollData(long employeeId, String name, long salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }
    
   

}
