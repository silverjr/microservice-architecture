package com.departmentservice.service;

import com.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartments(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String departmentCode);
    DepartmentDto getDepartmentById(Long id);
    DepartmentDto updateDepartment(DepartmentDto departmentDto);
    void deleteDepartment(Long departmentId);
}
