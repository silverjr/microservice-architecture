package com.employeeservice.mapper;

import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoEmployeeMapper {
    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);
    EmployeeDto mapToEmployeeDto(Employee employee);
    Employee mapToEmployee(EmployeeDto employeeDto);
}
