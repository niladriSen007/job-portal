package com.niladri.companyservice.service.impl;

import com.niladri.companyservice.entity.Company;
import com.niladri.companyservice.exceptions.CompanyAlreadyExistsByNameException;
import com.niladri.companyservice.exceptions.CompanyAlreadyExistsByRegistrationNumber;
import com.niladri.companyservice.exceptions.CompanyNotFoundException;
import com.niladri.companyservice.exceptions.InvalidRegistrationNumberException;
import com.niladri.companyservice.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

import com.niladri.domain.CompanyStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.niladri.companyservice.dto.request.CompanyPatchRequest;
import com.niladri.companyservice.dto.request.CompanyFilterRequest;
import com.niladri.companyservice.dto.request.CompanyRequest;
import com.niladri.companyservice.dto.response.CompanyResponse;
import com.niladri.companyservice.mapper.Mapper;
import com.niladri.companyservice.service.ICompanyService;
import com.niladri.companyservice.specification.CompanySpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest, Long ownerId) {
        // Check if a company with the same owner email already exists
        if (companyRepository.existsByOwnerEmail(companyRequest.getOwnerEmail())) {
            throw new RuntimeException("A company with the same owner email already exists.");
        }

        // Check if there is any company with the same name exists or not
        boolean isCompanyExistsByName = companyRepository.existsByName(companyRequest.getName());
        if (isCompanyExistsByName)
            throw new CompanyAlreadyExistsByNameException("A company with the same name already exists.");

        if (companyRequest.getRegistrationNumber() == null)
            throw new InvalidRegistrationNumberException("A company with the same registration number is required.");

        boolean existsByRegistrationNumber = companyRepository.existsByRegistrationNumber(companyRequest.getRegistrationNumber());
        if (existsByRegistrationNumber)
            throw new CompanyAlreadyExistsByRegistrationNumber("A company with the same registration number already exists.");

        // Map the CompanyRequest to a Company entity
        String slug = generateUniqueSlug(companyRequest.getName());
        companyRequest.setSlug(slug);
        Company companyEntity = Mapper.toCompanyEntity(companyRequest);

        companyEntity.setOwnerId(ownerId);
        companyEntity.setCompanyStatus(CompanyStatus.PENDING_VERIFICATION);

        // Save the company entity to the database
        Company savedCompany = companyRepository.save(companyEntity);

        // Map the saved entity to a CompanyResponse and return it
        return Mapper.toCompanyResponse(savedCompany);
    }

    private String generateUniqueSlug(@NotBlank(message = "Company name is required") String name) {
        return name.toLowerCase().concat(name.toUpperCase());
    }

    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Mapper::toCompanyResponse)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + companyId));
    }

    @Override
    public CompanyResponse getCompanyByOwnerEmail(String ownerEmail) {

        Optional<Company> byOwnerEmail = companyRepository.findByOwnerEmail(ownerEmail);

        if (byOwnerEmail.isEmpty())
            throw new CompanyNotFoundException("Company not found with ownerEmail: " + ownerEmail);

        return Mapper.toCompanyResponse(byOwnerEmail.get());
    }

    @Override
    public Page<CompanyResponse> getAllCompanies(
            CompanyFilterRequest companyFilterRequest, Pageable pageable) {
        return companyRepository.findAll(CompanySpecification.filter(companyFilterRequest), pageable)
                .map(Mapper::toCompanyResponse);
    }

    @Override
    public CompanyResponse patchCompany(Long companyId, CompanyPatchRequest patchRequest, String ownerEmail) {
        Company existingCompany = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with ID: " + companyId));

        if (!existingCompany.getOwnerEmail().equals(ownerEmail)) {
            throw new RuntimeException("Owner email does not match the existing company.");
        }

        Mapper.patchCompanyEntity(existingCompany, patchRequest);

        Company updatedCompany = companyRepository.save(existingCompany);
        return Mapper.toCompanyResponse(updatedCompany);
    }

    @Override
    public void deleteCompany(Long companyId, String ownerEmail) {
        Optional<Company> companyByOwnerEmail = companyRepository.findCompanyByOwnerEmail(ownerEmail);
        if (companyByOwnerEmail.isEmpty())
            throw new CompanyNotFoundException("Company not found with ownerEmail: " + ownerEmail);
        companyByOwnerEmail.get().setCompanyStatus(CompanyStatus.DELETED);
        companyByOwnerEmail.get().setIsDeleted(true);
        companyByOwnerEmail.get().setIsActive(false);
        companyRepository.save(companyByOwnerEmail.get());
    }

    @Override
    public CompanyResponse suspendCompany(Long companyId) {
        Optional<Company> companyById = companyRepository.findById(companyId);
        if (companyById.isEmpty())
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);

        companyById.get().setCompanyStatus(CompanyStatus.SUSPENDED);
        companyById.get().setIsSuspended(true);
        companyById.get().setIsActive(false);
        companyRepository.save(companyById.get());
        return Mapper.toCompanyResponse(companyById.get());
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) {
        Optional<Company> companyById = companyRepository.findById(companyId);
        if (companyById.isEmpty())
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);

        companyById.get().setCompanyStatus(CompanyStatus.ACTIVE);
        companyById.get().setIsVerified(true);
        companyById.get().setIsActive(true);
        companyRepository.save(companyById.get());
        return Mapper.toCompanyResponse(companyById.get());
    }

    @Override
    public CompanyResponse getCompanyEntityById(Long companyId) {
        Optional<Company> companyIyId = companyRepository.findById(companyId);
        if (companyIyId.isEmpty())
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);

        return Mapper.toCompanyResponse(companyIyId.get());
    }
    // Implement the methods defined in ICompanyService interface here

}
