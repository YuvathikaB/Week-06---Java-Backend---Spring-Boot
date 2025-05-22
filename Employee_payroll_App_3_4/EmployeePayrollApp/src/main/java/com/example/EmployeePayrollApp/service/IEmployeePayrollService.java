package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.model.EmployeePayrollData;
import java.util.List;


public interface IEmployeePayrollService {
	
	List<EmployeePayrollData> getAllEmployeePayrollData();
	EmployeePayrollData getEmployeePayrollDataById(long empId);
	EmployeePayrollData createEmployeePayrollData(EmployeeDTO employeeDTO);
    EmployeePayrollData updateEmployeePayrollData(long empId, EmployeeDTO employeeDTO);
    void deleteEmployeePayrollData(long empId);

}
