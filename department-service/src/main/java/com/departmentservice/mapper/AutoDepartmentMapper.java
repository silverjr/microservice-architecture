package com.departmentservice.mapper;

import com.departmentservice.dto.DepartmentDto;
import com.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoDepartmentMapper {
    AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class);
    DepartmentDto mapToDepartmentDto(Department department);
    Department mapToDepartment(DepartmentDto departmentDto);
}
