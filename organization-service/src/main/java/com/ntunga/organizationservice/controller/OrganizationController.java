package com.employeeservice.controller;

import com.employeeservice.dto.DepartmentDto;
import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "REST APIs for Employee Resource"
)
@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    //Create Employee
    @Operation(
            summary = "Create Employee REST API"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATE"
    )
    @PostMapping("/save")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok("Employee SAVED Successfully");
    }

    //get all Employee
    @Operation(
            summary = "Get all Employee REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDtoList);
    }

    //get Employee by id
    @Operation(
            summary = "Get all Employee REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @GetMapping("/id")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathParam("id") Long employeeId){
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    //Update Employee by id
    @Operation(
            summary = "Update Employee REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @PutMapping("/q")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathParam("id") Long employeeId, @RequestBody EmployeeDto employeeDto){
        employeeDto.setId(employeeId);
        employeeService.updateEmployee(employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    //Delete Employee by id
    @Operation(
            summary = "Delete Employee REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@PathParam("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee DELETED Successfully");
    }

}
