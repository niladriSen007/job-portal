package com.niladri.companyservice.dto.response;

import com.niladri.companyservice.entity.SocialLinks;
import com.niladri.domain.CompanySize;
import com.niladri.domain.CompanyStatus;
import com.niladri.domain.CompanyType;
import com.niladri.domain.IndustryType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponse {

    private Long id;
    private String name;
    private String email;
    private String slug;
    private String phoneNumber;
    private String description;
    private String tagline;
    private String companyLogo;
    private String companyCoverImage;
    private String website;
    private Integer foundedYear;

    private CompanySize companySize;
    private CompanyType companyType;
    private IndustryType industryType;
    private CompanyStatus companyStatus;
    private Boolean isActive;
    private String registrationNumber;
    private Long ownerId;
    private String ownerEmail;

    private List<SocialLinkResponse> socialLinks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime suspendedAt;
    private LocalDateTime verifiedAt;
}
