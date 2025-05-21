package com.example.EmployeePayrollApp.model;

public class EmployeePayrollData {
	private long employeeId;
    private String name;
    private long salary;
    
    public EmployeePayrollData() {
    }
    
    public EmployeePayrollData(long employeeId, String name, long salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }
    
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeePayrollData{" +
               "employeeId=" + employeeId +
               ", name='" + name + '\'' +
               ", salary=" + salary +
               '}';
    }

}
