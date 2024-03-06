package com.employeeservice.service;

import com.employeeservice.dto.DepartmentDto;
import com.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    void updateEmployee(EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
