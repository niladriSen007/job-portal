package com.niladri.companyservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.niladri.companyservice.dto.request.CompanyPatchRequest;
import com.niladri.companyservice.dto.request.CompanyFilterRequest;
import com.niladri.companyservice.dto.request.CompanyRequest;
import com.niladri.companyservice.dto.response.CompanyResponse;

public interface ICompanyService {
    CompanyResponse createCompany(CompanyRequest companyRequest, Long ownerId);

    CompanyResponse getCompanyById(Long companyId);

    CompanyResponse getCompanyByOwnerEmail(String ownerEmail);

    Page<CompanyResponse> getAllCompanies(
            CompanyFilterRequest companyFilterRequest, Pageable pageable);

    CompanyResponse patchCompany(Long companyId, CompanyPatchRequest patchRequest, String ownerEmail);

    void deleteCompany(Long companyId, String ownerEmail);

    CompanyResponse suspendCompany(Long companyId);

    CompanyResponse verifyCompany(Long companyId);

    CompanyResponse getCompanyEntityById(Long companyId);

}
