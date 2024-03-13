package com.employeeservice.service.impl;

import com.employeeservice.dto.DepartmentDto;
import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.entity.Employee;
import com.employeeservice.mapper.AutoEmployeeMapper;
import com.employeeservice.repository.EmployeeRepository;
import com.employeeservice.service.APIClient;
import com.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private WebClient webClient;

    private APIClient openFeignClient;
    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employeeRepository.save(AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto)));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> {
                    String departmentCode = employee.getDepartmentCode();
                    DepartmentDto departmentDto = openFeignClient.getDepartmentByCode(departmentCode);

                    // Create a new EmployeeDto and set its properties
                    EmployeeDto employeeDto = new EmployeeDto();
                    employeeDto.setId(employee.getId());
                    employeeDto.setFirstName(employee.getFirstName());
                    employeeDto.setLastName(employee.getLastName());
                    employeeDto.setEmailAddress(employee.getEmailAddress());
                    employeeDto.setDepartment(departmentDto);

                    return employeeDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        DepartmentDto departmentDto = openFeignClient.getDepartmentByCode(employee.getDepartmentCode());
        employeeDto.setDepartment(departmentDto);
        return employeeDto;
    }

    /** Function to fetch department information from department service; */
    private DepartmentDto fetchDepartmentDto(String departmentCode) {
        return webClient.get()
                .uri("http://localhost:8080/department/code/" + departmentCode)
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
    }


    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(employeeDto.getId()).get();
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmailAddress(employeeDto.getEmailAddress());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        employeeRepository.save(updatedEmployee);

        AutoEmployeeMapper.MAPPER.mapToEmployeeDto(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
