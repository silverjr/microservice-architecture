package com.employeeservice.service;

import com.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "department-service")
public interface APIClient {
    //build get department res api
    @GetMapping("/department/code/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable("code") String  departmentCode);
}
