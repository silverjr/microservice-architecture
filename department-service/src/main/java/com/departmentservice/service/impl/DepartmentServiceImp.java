package com.departmentservice.service.impl;

import com.departmentservice.dto.DepartmentDto;
import com.departmentservice.entity.Department;
import com.departmentservice.mapper.AutoDepartmentMapper;
import com.departmentservice.repository.DepartmentRepository;
import com.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImp implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments(DepartmentDto departmentDto) {
        List<Department> department = departmentRepository.findAll();
        return department.stream().map(AutoDepartmentMapper.MAPPER::mapToDepartmentDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).get();
        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {

        Department existingDepartment = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());
        existingDepartment.setDepartmentCode(departmentDto.getDepartmentCode());
        existingDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());
        existingDepartment.setDepartmentName(departmentDto.getDepartmentName());

        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(departmentRepository.save(existingDepartment));
    }

    @Override
    public void deleteDepartment(Long departmentId) {
            departmentRepository.findById(departmentId).orElseThrow(()->new RuntimeException("Error DELETING department"));
            departmentRepository.deleteById(departmentId);
    }
}
