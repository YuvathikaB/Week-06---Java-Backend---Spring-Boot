package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.dto.*;
import com.example.EmployeePayrollApp.model.EmployeePayrollData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {
	private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    @GetMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getAllEmployeePayrollData() {
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", employeePayrollList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") long empId) {
        EmployeePayrollData employeeData = employeePayrollList.stream()
                .filter(emp -> emp.getEmployeeId() == empId)
                .findFirst()
                .orElse(null);
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
        EmployeePayrollData employeeData = new EmployeePayrollData(
                counter.incrementAndGet(),
                employeeDTO.getName(),
                employeeDTO.getSalary()
        );
        employeePayrollList.add(employeeData);
        ResponseDTO responseDTO = new ResponseDTO("Created Employee Payroll Data Successfully", employeeData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") long empId,
                                                                 @RequestBody EmployeeDTO employeeDTO) {
        EmployeePayrollData existingEmployee = employeePayrollList.stream()
                .filter(emp -> emp.getEmployeeId() == empId)
                .findFirst()
                .orElse(null);

        ResponseDTO responseDTO;
        if (existingEmployee != null) {
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setSalary(employeeDTO.getSalary());
            responseDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", existingEmployee);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO = new ResponseDTO("Update Failed", "Employee with ID " + empId + " not found");
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") long empId) {
        boolean removed = employeePayrollList.removeIf(emp -> emp.getEmployeeId() == empId);
        ResponseDTO responseDTO;
        if (removed) {
            responseDTO = new ResponseDTO("Deleted Successfully", "Deleted ID: " + empId);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO = new ResponseDTO("Deletion Failed", "Employee with ID " + empId + " not found");
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }

}
