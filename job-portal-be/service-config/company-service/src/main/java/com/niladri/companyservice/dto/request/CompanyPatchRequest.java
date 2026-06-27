package com.niladri.companyservice.dto.request;

import com.niladri.companyservice.dto.response.SocialLinkResponse;
import com.niladri.domain.CompanySize;
import com.niladri.domain.CompanyType;
import com.niladri.domain.IndustryType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

/**
 * DTO for partial company updates (PATCH semantics).
 * All fields are optional — only non-null fields will be applied to the existing resource.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyPatchRequest {

    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private String slug;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    private String description;

    private String tagline;

    private String companyLogo;

    private String companyCoverImage;

    @Pattern(regexp = "^(https?://).*", message = "Company Website URL must be valid")
    private String website;

    @Min(value = 1800, message = "Founded year seems too old")
    @Max(value = 2100, message = "Founded year is invalid")
    private Integer foundedYear;

    private CompanySize companySize;

    private CompanyType companyType;

    private IndustryType industryType;

    private List<SocialLinkResponse> socialLinks;
}
