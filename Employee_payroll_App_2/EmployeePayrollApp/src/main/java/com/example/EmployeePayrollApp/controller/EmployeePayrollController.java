package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.dto.*;
import com.example.EmployeePayrollApp.model.EmployeePayrollData;
import com.example.EmployeePayrollApp.service.IEmployeePayrollService; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {
	
	@Autowired 
    private IEmployeePayrollService employeePayrollService;

    @GetMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getAllEmployeePayrollData() {
    	List<EmployeePayrollData> employeeList = employeePayrollService.getAllEmployeePayrollData();
    	ResponseDTO responseDTO = new ResponseDTO("Get Call Success", employeeList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") long empId) {
    	EmployeePayrollData employeeData = employeePayrollService.getEmployeePayrollDataById(empId);
        ResponseDTO responseDTO;
        if (employeeData != null) {
            responseDTO = new ResponseDTO("Get Call For ID Success", employeeData);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO = new ResponseDTO("Get Call For ID Failed", "Employee with ID " + empId + " not found.");
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(@RequestBody EmployeeDTO employeeDTO) {
    	EmployeePayrollData employeeData = employeePayrollService.createEmployeePayrollData(employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created Employee Payroll Data Successfully", employeeData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") long empId,
                                                                 @RequestBody EmployeeDTO employeeDTO) {
    	EmployeePayrollData employeeData = employeePayrollService.updateEmployeePayrollData(empId, employeeDTO);
        ResponseDTO responseDTO;
        if (employeeData != null) {
            responseDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", employeeData);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO = new ResponseDTO("Update Failed", "Employee with ID " + empId + " not found");
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") long empId) {
    	EmployeePayrollData employeeData = employeePayrollService.getEmployeePayrollDataById(empId); // Check if exists before deleting for proper response
        if (employeeData != null) {
            employeePayrollService.deleteEmployeePayrollData(empId);
            ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully", "Deleted ID: " + empId);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            ResponseDTO responseDTO = new ResponseDTO("Deletion Failed", "Employee with ID " + empId + " not found");
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }

}
