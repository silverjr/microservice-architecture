package com.departmentservice.controller;

import com.departmentservice.dto.DepartmentDto;
import com.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "REST APIs for Department Resource"
)
@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Operation(
            summary = "Create Department",
            description = "Create Department REST API is used to save department in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATE"
    )

    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        return new  ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get All Departments",
            description = "Select All Departments REST API is used to select all departments in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/list")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(DepartmentDto  departmentDto){
        return new ResponseEntity<>(departmentService.getAllDepartments(departmentDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Get Department by Code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/code/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String  departmentCode){
        DepartmentDto department = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Department by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/id/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentDto department = departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Department by department Code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("/update/{code}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("code") String departmentCode, @RequestBody DepartmentDto departmentDto){
        departmentDto.setDepartmentCode(departmentCode);
        DepartmentDto department = departmentService.updateDepartment(departmentDto);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Department by departmen Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>("department DELETED Successfully", HttpStatus.OK);
    }

}
