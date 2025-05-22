package com.example.EmployeePayrollApp.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class EmployeeDTO {
	
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
	public String name;
	
	@NotNull(message = "Salary cannot be null")
    public long salary;


}
