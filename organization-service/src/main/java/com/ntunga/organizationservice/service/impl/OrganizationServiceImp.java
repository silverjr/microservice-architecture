package com.ntunga.organizationservice.service.impl;

import com.ntunga.organizationservice.dto.OrganizationDto;
import com.ntunga.organizationservice.entity.Organization;
import com.ntunga.organizationservice.mapper.AutoOrganizationMapper;
import com.ntunga.organizationservice.repository.OrganizationRepository;
import com.ntunga.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationServiceImp implements OrganizationService {

    private OrganizationRepository organizationRepository;


    @Override
    public void createOrganization(OrganizationDto organizationDto) {
        Organization organization = AutoOrganizationMapper.MAPPER.mapToOrganization(organizationDto);
        Organization savedDepartment = organizationRepository.save(organization);
        AutoOrganizationMapper.MAPPER.mapToOrganizationDto(savedDepartment);
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map(AutoOrganizationMapper.MAPPER::mapToOrganizationDto).collect(Collectors.toList());
    }

    @Override
    public OrganizationDto getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id).get();
        return AutoOrganizationMapper.MAPPER.mapToOrganizationDto(organization);
    }

    @Override
    public void updateOrganization(OrganizationDto organizationDto) {
        Organization existingOrganization = organizationRepository.findById(organizationDto.getId()).get();
        existingOrganization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        existingOrganization.setOrganizationName(organizationDto.getOrganizationName());

        Organization updatedOrganization = organizationRepository.save(existingOrganization);
        organizationRepository.save(updatedOrganization);
        AutoOrganizationMapper.MAPPER.mapToOrganizationDto(existingOrganization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationRepository.findById(organizationId).orElseThrow(()->new RuntimeException("Error DELETING department"));
        organizationRepository.deleteById(organizationId);
    }
}
