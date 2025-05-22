package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.exception.EmployeePayrollException;
import com.example.EmployeePayrollApp.model.*;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
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
                                 .filter(empData -> empData.getEmployeeId() == empId)
                                 .findFirst()
                                 .orElseThrow(() -> new EmployeePayrollException("Employee with ID " + empId + " does not exist!")); 
    }

    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeeDTO empPayrollDTO) {
        long newId = counter.incrementAndGet();
        EmployeePayrollData empData = new EmployeePayrollData(newId, empPayrollDTO.getName(), empPayrollDTO.getSalary());
        employeePayrollList.add(empData);
        log.debug("Employee Added: " + empData.toString()); 
        return empData;
    }
    
    @Override
    public EmployeePayrollData updateEmployeePayrollData(long empId, EmployeeDTO empPayrollDTO) {
        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId); 
        empData.setName(empPayrollDTO.getName());
        empData.setSalary(empPayrollDTO.getSalary());
        log.debug("Employee Updated: " + empData.toString());
        return empData;
    }
    
    @Override
    public void deleteEmployeePayrollData(long empId) {
        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId); 
        employeePayrollList.remove(empData);
        log.debug("Employee Deleted: " + empData.toString());
    }
    }

   

	


