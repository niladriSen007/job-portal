package com.niladri.companyservice.mapper;

import com.niladri.companyservice.dto.request.CompanyPatchRequest;
import com.niladri.companyservice.dto.response.CompanyResponse;
import com.niladri.companyservice.dto.request.CompanyRequest;
import com.niladri.companyservice.dto.response.SocialLinkResponse;
import com.niladri.companyservice.entity.Company;
import com.niladri.companyservice.entity.SocialLinks;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static CompanyResponse toCompanyResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .slug(company.getSlug())
                .website(company.getWebsite())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .companyStatus(company.getCompanyStatus())
                .isActive(company.getIsActive())
                .registrationNumber(company.getRegistrationNumber())
                .ownerId(company.getOwnerId())
                .ownerEmail(company.getOwnerEmail())
                .phoneNumber(company.getPhoneNumber())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .deletedAt(company.getDeletedAt())
                .socialLinks(company.getSocialLinks() == null || company.getSocialLinks().isEmpty()
                        ? new ArrayList<SocialLinkResponse>()
                        : company.getSocialLinks()
                        .stream().map(link -> SocialLinkResponse.builder()
                                .url(link.getUrl())
                                .socialPlatform(link.getSocialPlatform())
                                .build()
                        ).toList())
                .suspendedAt(company.getSuspendedAt())
                .verifiedAt(company.getVerifiedAt())
                .build();
    }

    public static Company toCompanyEntity(CompanyRequest companyRequest) {
        return Company.builder()
                .name(companyRequest.getName())
                .website(companyRequest.getWebsite())
                .foundedYear(companyRequest.getFoundedYear())
                .companySize(companyRequest.getCompanySize())
                .companyType(companyRequest.getCompanyType())
                .industryType(companyRequest.getIndustryType())
                .registrationNumber(companyRequest.getRegistrationNumber())
                .ownerEmail(companyRequest.getOwnerEmail())
                .phoneNumber(companyRequest.getPhoneNumber())
                .socialLinks(mapToSocialLinks(companyRequest.getSocialLinks()))
                .build();
    }

    private static List<SocialLinks> mapToSocialLinks(List<SocialLinkResponse> socialLinks) {
        if (socialLinks == null || socialLinks.isEmpty()) {
            return new ArrayList<SocialLinks>();
        }
        return socialLinks.stream().map(link -> SocialLinks.builder()
                .url(link.getUrl())
                .socialPlatform(link.getSocialPlatform()).build()).toList();
    }

//    public static Company updateCompanyEntity(Company existingCompany, CompanyRequest companyRequest) {
//           existingCompany.setName(companyRequest.getName());
//           existingCompany.setSlug(companyRequest.getSlug());
//           existingCompany.setWebsite(companyRequest.getWebsite());
//           existingCompany.setFoundedYear(companyRequest.getFoundedYear());
//           existingCompany.setCompanySize(companyRequest.getCompanySize());
//           existingCompany.setCompanyType(companyRequest.getCompanyType());
//           existingCompany.setIndustryType(companyRequest.getIndustryType());
//           existingCompany.setRegistrationNumber(companyRequest.getRegistrationNumber());
//           existingCompany.setOwnerId(companyRequest.getOwnerId());
//           existingCompany.setOwnerEmail(companyRequest.getOwnerEmail());
//           existingCompany.setPhoneNumber(companyRequest.getPhoneNumber());
//    }

    /**
     * Applies only the non-null fields from a PATCH request to an existing company entity.
     * Fields left null in the request are left unchanged on the entity.
     */
    public static void patchCompanyEntity(Company existingCompany, CompanyPatchRequest patchRequest) {
        if (patchRequest.getName() != null) existingCompany.setName(patchRequest.getName());
        if (patchRequest.getEmail() != null) existingCompany.setEmail(patchRequest.getEmail());
        if (patchRequest.getSlug() != null) existingCompany.setSlug(patchRequest.getSlug());
        if (patchRequest.getPhoneNumber() != null) existingCompany.setPhoneNumber(patchRequest.getPhoneNumber());
        if (patchRequest.getDescription() != null) existingCompany.setDescription(patchRequest.getDescription());
        if (patchRequest.getTagline() != null) existingCompany.setTagline(patchRequest.getTagline());
        if (patchRequest.getCompanyLogo() != null) existingCompany.setCompanyLogo(patchRequest.getCompanyLogo());
        if (patchRequest.getCompanyCoverImage() != null)
            existingCompany.setCompanyCoverImage(patchRequest.getCompanyCoverImage());
        if (patchRequest.getWebsite() != null) existingCompany.setWebsite(patchRequest.getWebsite());
        if (patchRequest.getFoundedYear() != null) existingCompany.setFoundedYear(patchRequest.getFoundedYear());
        if (patchRequest.getCompanySize() != null) existingCompany.setCompanySize(patchRequest.getCompanySize());
        if (patchRequest.getCompanyType() != null) existingCompany.setCompanyType(patchRequest.getCompanyType());
        if (patchRequest.getIndustryType() != null) existingCompany.setIndustryType(patchRequest.getIndustryType());
    }
}
