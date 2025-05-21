package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.model.*;
import org.springframework.stereotype.Service; 
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{
	private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
    private AtomicLong counter = new AtomicLong(); 

    @Override
    public List<EmployeePayrollData> getAllEmployeePayrollData() {
        return employeePayrollList;
    }

    @Override
    public EmployeePayrollData getEmployeePayrollDataById(long empId) {
        return employeePayrollList.stream()
                .filter(emp -> emp.getEmployeeId() == empId)
                .findFirst()
                .orElse(null); 
    }

    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeeDTO employeeDTO) {
        EmployeePayrollData employeeData = new EmployeePayrollData(
                counter.incrementAndGet(),
                employeeDTO.getName(),
                employeeDTO.getSalary()
        );
        employeePayrollList.add(employeeData);
        return employeeData;
    }

    @Override
    public EmployeePayrollData updateEmployeePayrollData(long empId, EmployeeDTO employeeDTO) {
        EmployeePayrollData existingEmployee = employeePayrollList.stream()
                .filter(emp -> emp.getEmployeeId() == empId)
                .findFirst()
                .orElse(null);

        if (existingEmployee != null) {
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setSalary(employeeDTO.getSalary());
            return existingEmployee;
        }
        return null; 
    }

    @Override
    public void deleteEmployeePayrollData(long empId) {
        employeePayrollList.removeIf(emp -> emp.getEmployeeId() == empId);
    }
	

}
