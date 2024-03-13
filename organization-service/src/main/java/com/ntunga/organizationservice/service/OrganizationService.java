package com.ntunga.organizationservice.service;

import com.ntunga.organizationservice.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {
    void createOrganization(OrganizationDto organizationDto);
    List<OrganizationDto> getAllOrganizations();
    OrganizationDto getOrganizationById(Long id);
    void updateOrganization(OrganizationDto organizationDto);
    void deleteOrganization(Long id);
}
