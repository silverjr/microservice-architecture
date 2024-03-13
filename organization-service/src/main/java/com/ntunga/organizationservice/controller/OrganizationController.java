package com.ntunga.organizationservice.controller;


import com.ntunga.organizationservice.dto.OrganizationDto;
import com.ntunga.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "REST APIs for Organization Resource"
)
@RestController
@AllArgsConstructor
@RequestMapping("/organization")
public class OrganizationController {
    private OrganizationService organizationService;

    //Create Organization
    @Operation(
            summary = "Create Organization REST API"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATE"
    )
    @PostMapping("/save")
    public ResponseEntity<String> createOrganization(@RequestBody OrganizationDto organizationDto){
        organizationService.createOrganization(organizationDto);
        return ResponseEntity.ok("Organization SAVED Successfully");
    }

    //get all Organization
    @Operation(
            summary = "Get all Organization REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @GetMapping("/list")
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations(){
        List<OrganizationDto> organizationDtoList = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizationDtoList);
    }

    //get Organization by id
    @Operation(
            summary = "Get all Organization REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @GetMapping("/id")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathParam("id") Long organizationId){
        return ResponseEntity.ok(organizationService.getOrganizationById(organizationId));
    }

    //Update Organization by id
    @Operation(
            summary = "Update Organization REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @PutMapping("/q")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathParam("id") Long organizationId, @RequestBody OrganizationDto organizationDto){
        organizationDto.setId(organizationId);
        organizationService.updateOrganization(organizationDto);
        return ResponseEntity.ok(organizationDto);
    }

    //Delete Organization by id
    @Operation(
            summary = "Delete Organization REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOgarnization(@PathParam("id") Long organizationId){
        organizationService.deleteOrganization(organizationId);
        return ResponseEntity.ok("Organization DELETED Successfully");
    }

}
